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
/*
 * 
 */
package com.climate.predictor.algorithm;

import com.climate.predictor.constants.AppConstants;
import com.climate.predictor.utils.CommonUtil;
import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;

/**
 * The Class that describes TimeSeriesAlgorithm.
 * This class uses ARIMA algorithm for predictions.
 * 
 * @author Sujith M
 * 
 */
public class TimeSeriesAlgorithm implements Algorithm {

	/**
	 * Method that must be implemented for predicting series value(s).
	 *
	 * @param inputData -: double Array of Historic data
	 * @param forecastSize -: int value for prediction output array size
	 * @return the double[]
	 */
	public double[] predictValue(final double[] inputData, final int forecastSize) {
		ArimaParams arimaObj = new ArimaParams(1, 1, 2, 1, 1, 0, 0);
		ForecastResult predictionByArima = Arima.forecast_arima(inputData, forecastSize, arimaObj);
		double[] values = predictionByArima.getForecast();
		return values;
	}

	/**
	 * Gets the Weather condition based on temperature and humidity.
	 *
	 * @param temperature -: double value temperature
	 * @param humidity -: double value humidity
	 * @return the condition (SNOW/RAIN/SUNNY/NORMAL)
	 */
	public String getcondition(final double temperature, final double humidity) {
		if(!CommonUtil.isNull(temperature) && !CommonUtil.isNull(humidity)){
			String temperatureCondition = AppConstants.temperatureNormal;
			if (temperature < 20) {
				temperatureCondition = AppConstants.temperatureCold;
			} else if (temperature > 15 && temperature < 30) {
				temperatureCondition = AppConstants.temperatureNormal;
			} else {
				temperatureCondition = AppConstants.temperatureHot;
			}

			if ((temperatureCondition == AppConstants.temperatureCold) && humidity > 70) {
				return AppConstants.SNOW_STRING;
			} else if ((temperatureCondition == AppConstants.temperatureCold) && humidity < 70) {
				return AppConstants.RAIN_STRING;
			} else if ((temperatureCondition == AppConstants.temperatureNormal) && humidity > 70) {
				return AppConstants.NORMAL_STRING;
			} else if ((temperatureCondition == AppConstants.temperatureNormal) && humidity < 70) {
				return AppConstants.NORMAL_STRING;
			} else if ((temperatureCondition == AppConstants.temperatureHot) && humidity > 70) {
				return AppConstants.SUNNY_STRING;
			} else {
				return AppConstants.SUNNY_STRING;
			}
		} else {
			return null;
		}
		
	}

}
