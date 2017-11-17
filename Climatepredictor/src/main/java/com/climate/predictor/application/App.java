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

package com.climate.predictor.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.climate.predictor.algorithm.Algorithm;
import com.climate.predictor.algorithm.AlgorithmFactory;
import com.climate.predictor.constants.AppConstants;
import com.climate.predictor.constants.AppMessages;
import com.climate.predictor.datacrawler.HistoryLoader;
import com.climate.predictor.datacrawler.LocationLoader;
import com.climate.predictor.exception.ClimateException;
import com.climate.predictor.model.CSVDataModel;
import com.climate.predictor.model.HistoryDataModel;
import com.climate.predictor.model.LocationModel;
import com.climate.predictor.utils.ArrayUtil;
import com.climate.predictor.utils.CommonUtil;
import com.climate.predictor.utils.ConsoleInputUtil;
import com.climate.predictor.utils.DateUtil;
import com.climate.predictor.utils.FileUtil;

/**
 * The Class App is entry point of the application.
 * This class will prompt user to enter a future date in the form of "yyyy-MM-dd HH".
 * Then two months historic (previous years) data will collected and predictions
 * are done with the help of Algorithm.
 * 
 * @author Sujith M
 * 
 */
public class App {

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(App.class);

	/**
	 * The main method.
	 *
	 * @param args
	 *            <No arguments needed>
	 *            
	 *            
	 */
	public static void main(String[] args) {
		log.info(AppMessages.WELCOME_MESSAGE);

		boolean status = false;

		/**
		 * logic
		 */
		try {

			List<HistoryDataModel> historyModelList = null;
			App appObj = new App();
			
			// Get Date input from User
			Date predictionDate = appObj.getUserInput();
			if (!CommonUtil.isNull(predictionDate)) {
				
				// Check if hour is between 9 pm and 9am
				// If (9am => hour <= 9pm), Will consider day temperature values from historic data(at 3pm)
				// else will consider night temperature (at 9am)
				String dayOrNightString = DateUtil.getChoiceOfTime(predictionDate);
				
				//Call API and get data
				historyModelList = appObj.fetchData(dayOrNightString, predictionDate);
			}

			if (ArrayUtil.isValidList(historyModelList)) {
				
				//forecast the value
				List<LocationModel> finalOutputList = appObj.forecastClimate(historyModelList, predictionDate);

				if (ArrayUtil.isValidList(finalOutputList)) {
					// write output to output.txt for executing directory
					status = FileUtil.writeOutput(finalOutputList);
				}
			}

		} catch (ClimateException e) {
			log.error(e.getMessage());
		}

		// Print completed status (Fail/Success)of current execution
		if (status) {
			log.info(AppMessages.FINISHED_MESSAGE);
		} else {
			log.error(AppMessages.FAILED_MESSAGE);
		}
	}

	/**
	 * User Input Method for date.
	 *
	 * @return date :- User Input date
	 */
	private Date getUserInput() {
		Date predictionDate = ConsoleInputUtil.getUserInputDate();
		if (predictionDate != null) {
			return predictionDate;
		} else {
			log.error(AppMessages.INVALID_DATE_MESSAGE);
			return null;
		}
	}

	/**
	 * Fetch data by calling API.
	 *
	 * @param dayOrNightString :- String which decides which data to be taken for prediction (9am/3pm)
	 * @param predictionDate :- Date thats going to be predicted
	 * 
	 * @return list :- historic Data
	 * @throws ClimateException
	 */
	private List<HistoryDataModel> fetchData(String dayOrNightString, Date predictionDate) throws ClimateException {
		FileUtil fileUtilObj = new FileUtil();
		List<HistoryDataModel> historyModelList = new ArrayList<HistoryDataModel>();
		
		//Read input file from the resource folder (inputTxt.txt)
		List<CSVDataModel> dataFromCSV = fileUtilObj.readInputFile(AppConstants.inputFileName);

		if (ArrayUtil.isValidList(dataFromCSV)) {
			
			// Call google API for geoCode, elevation and timezone
			List<LocationModel> locationList = new LocationLoader().getDataUsingAPI(dataFromCSV);
			if (ArrayUtil.isValidList(locationList)) {
				
				//Get historic weather data using API
				historyModelList = new HistoryLoader().getHistoryData(locationList, dayOrNightString, predictionDate);
			}
		}
		return historyModelList;
	}

	/**
	 * Predict values using algorithm.
	 *
	 * @param historyModelList :- history data 
	 * @param predictionDate :- Date for prediction
	 * @return list :- Output list with all the data calculated
	 */
	private List<LocationModel> forecastClimate(List<HistoryDataModel> historyModelList, Date predictionDate) {
		List<LocationModel> finalOutputList = new ArrayList<LocationModel>();

		// Specifying Algotithm
		AlgorithmFactory factoryObj = new AlgorithmFactory();
		Algorithm algorithmObj = factoryObj.getAlgorithm(AppConstants.timeSeriesAlgorithm);
		for (HistoryDataModel currentObj : historyModelList) {
			
			//predict temperature
			double temperaturePredictions[] = algorithmObj
					.predictValue(currentObj.getLastYearData().getTemperatureArray(), AppConstants.forecastSize);
			
			//predict humidity
			double humidityPredictions[] = algorithmObj.predictValue(currentObj.getLastYearData().getHumidityArray(),
					AppConstants.forecastSize);
			
			//predict pressure
			double pressurePredictions[] = algorithmObj.predictValue(currentObj.getLastYearData().getPressureArray(),
					AppConstants.forecastSize);

			LocationModel locationObj = currentObj.getLocationDataModel();
			locationObj.setDateString(AppConstants.outputDateformat.format(predictionDate));
			locationObj.setTemperature(ArrayUtil.getNearestMeasurement(temperaturePredictions, locationObj.getLastYearTemp()));
			locationObj.setPressure(ArrayUtil.getNearestMeasurement(pressurePredictions, locationObj.getLastYearPressure()));
			locationObj.setHumidity(ArrayUtil.getNearestMeasurement(humidityPredictions, locationObj.getLastYearHumidity()));
			String condition = algorithmObj.getcondition(locationObj.getTemperature(), locationObj.getPressure());
			if (!CommonUtil.isNull(condition)) {
				locationObj.setCondition(condition);
			} else {
				return new ArrayList<LocationModel>();
			}

			finalOutputList.add(locationObj);
		}

		return finalOutputList;
	}

}
