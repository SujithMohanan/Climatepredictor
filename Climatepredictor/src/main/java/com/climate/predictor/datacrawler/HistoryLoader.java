/*******************************************************************************
 * Copyright (C) 2017 Sujith M. 
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	"http://www.apache.org/licenses/LICENSE-2.0"
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * 	Sujith M
 *******************************************************************************/

package com.climate.predictor.datacrawler;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.climate.predictor.constants.AppConstants;
import com.climate.predictor.exception.ClimateException;
import com.climate.predictor.model.HistoryDataModel;
import com.climate.predictor.model.LastYearDataModel;
import com.climate.predictor.model.LocationModel;
import com.climate.predictor.utils.APIUtil;
import com.climate.predictor.utils.DateUtil;
import com.climate.predictor.utils.QueueUtil;

/**
 * The Class for getting history data about weather.
 * 
 * Data is collected from "http://www.bom.gov.au/climate/dwo/"
 * 
 * @author Sujith M
 * 
 */
public class HistoryLoader {

	/**
	 * Collect the historic data for all locations specified in the
	 * "inputTxt.txt".
	 *
	 * @param locationList
	 *            :- list of all cities and its informations
	 * @param choice
	 *            :- Day or night classification
	 * @param predictionDate
	 *            :-Date which is going to be predicted
	 * @return the history data
	 * @throws ClimateException
	 */
	public List<HistoryDataModel> getHistoryData(final List<LocationModel> locationList, final String choice,
			final Date predictionDate) throws ClimateException {

		List<HistoryDataModel> historyDataModelList = new ArrayList<HistoryDataModel>();
		for (LocationModel currentObj : locationList) {

			List<String> dateList = DateUtil.getInputTimeFrame(predictionDate);
			String lastYearDate = DateUtil.getLastYearDate(predictionDate);

			HistoryDataModel historyDataModelObj = new HistoryDataModel();
			historyDataModelObj.setLastYearData(callHistoryAPI(dateList, currentObj, choice, lastYearDate));
			historyDataModelObj.setLocationDataModel(currentObj);
			historyDataModelList.add(historyDataModelObj);
		}

		return historyDataModelList;
	}

	/**
	 * Method to call history API.
	 *
	 * @param dateList
	 *            :- Needed months
	 * @param currentObj
	 *            :- city which is being collected
	 * @param choice
	 *            :- Day or night classification
	 * @param lastYearDate
	 *            :- last year date
	 * @return LastYearDataModel :- which have all the info of history
	 * @throws ClimateException
	 *             the climate exception
	 */
	private LastYearDataModel callHistoryAPI(final List<String> dateList, final LocationModel currentObj, String choice,
			String lastYearDate) throws ClimateException {

		// Queues for storing last 10 days values
		// used if any data point is null
		LinkedList<Double> temperatureQueue = QueueUtil.createQueue();
		LinkedList<Double> pressureQueue = QueueUtil.createQueue();
		LinkedList<Double> humidityQueue = QueueUtil.createQueue();

		List<Double> temperatureList = new ArrayList<Double>();
		List<Double> pressureList = new ArrayList<Double>();
		List<Double> humidityList = new ArrayList<Double>();

		for (String dateString : dateList) {

			// calling API
			String url = AppConstants.bomHistoryURL + dateString + AppConstants.textPath
					+ currentObj.getCsvData().getIataCode() + AppConstants.dot + dateString + AppConstants.fileType;
			String historicData = APIUtil.getHistoricData(url);

			String lines[] = historicData.split(AppConstants.lineSplitter, -1);

			// to skip header informations
			boolean flag = false;
			for (String currentLine : lines) {
				if (!currentLine.isEmpty()) {
					if (flag) {

						// spitting line with ","
						String[] lineSplits = currentLine.split(AppConstants.comma, -1);

						if (choice.equals(AppConstants.NIGHT_STRING)) {
							double temperature = parseDoubleData(temperatureQueue, lineSplits[10]);
							temperatureQueue.add(temperature);
							temperatureList.add(temperature);

							double humidity = parseDoubleData(humidityQueue, lineSplits[11]);
							humidityQueue.add(humidity);
							humidityList.add(humidity);

							double pressure = parseDoubleData(pressureQueue, lineSplits[15]);
							pressureQueue.add(pressure);
							pressureList.add(pressure);

							if (lastYearDate.equals(lineSplits[1])) {
								currentObj.setLastYearTemp(temperature);
								currentObj.setLastYearPressure(pressure);
								currentObj.setLastYearHumidity(humidity);
							}
						} else if (choice.equals(AppConstants.DAY_STRING)) {

							double temperature = parseDoubleData(temperatureQueue, lineSplits[16]);
							temperatureQueue.add(temperature);
							temperatureList.add(temperature);

							double humidity = parseDoubleData(humidityQueue, lineSplits[17]);
							humidityQueue.add(humidity);
							humidityList.add(humidity);

							double pressure = parseDoubleData(pressureQueue, lineSplits[21]);
							pressureQueue.add(pressure);
							pressureList.add(pressure);
							if (lastYearDate.equals(lineSplits[1])) {
								currentObj.setLastYearTemp(temperature);
								currentObj.setLastYearPressure(pressure);
								currentObj.setLastYearHumidity(humidity);
							}
						}
					}
					if (currentLine.equals(AppConstants.ecsapeString)) {
						flag = true;
					}
				}

			}
		}

		LastYearDataModel lastYearData = new LastYearDataModel();
		lastYearData.setTemperatureArray(
				ArrayUtils.toPrimitive(temperatureList.toArray(new Double[temperatureList.size()])));
		lastYearData.setPressureArray(ArrayUtils.toPrimitive(humidityList.toArray(new Double[humidityList.size()])));
		lastYearData.setHumidityArray(ArrayUtils.toPrimitive(pressureList.toArray(new Double[pressureList.size()])));

		return lastYearData;
	}

	/**
	 * Parses double value.
	 * 
	 * @param currentQueue
	 *            :- queue
	 * @param data
	 *            :- data to be parsed to double
	 * @return the double
	 */
	private double parseDoubleData(final LinkedList<Double> currentQueue, final String data) {
		double output = 0;
		try {
			output = Double.parseDouble(data);
		} catch (NumberFormatException e) {
			return getAverage(currentQueue);
		}
		return output;
	}

	/**
	 * Gets the average of values in current queue object.
	 *
	 * @param currentQueue
	 *            :- queue
	 * @return the average
	 */
	private double getAverage(final LinkedList<Double> currentQueue) {
		double avg = 0;
		if (!currentQueue.isEmpty()) {
			for (double val : currentQueue) {
				avg += val;
			}
			return avg / currentQueue.size();
		}
		return avg;
	}

}
