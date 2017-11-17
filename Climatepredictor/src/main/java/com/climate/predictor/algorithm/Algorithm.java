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

package com.climate.predictor.algorithm;

/**
 * Generic representation of algorithm interface.
 * 
 * @author Sujith M
 * 
 */
public interface Algorithm {

	/**
	 * Method that must be implemented for predicting series value(s).
	 *
	 * @param inputData -: double Array of Historic data
	 * @param forecastSize -: int value for prediction output array size
	 * @return the double[]
	 */
	public double[] predictValue(final double[] inputData, final int forecastSize);
	
	/**
	 * Gets the Weather condition based on temperature and humidity.
	 *
	 * @param temperature -: double value temperature
	 * @param humidity -: double value humidity
	 * @return the condition (SNOW/RAIN/SUNNY/NORMAL)
	 */
	public String getcondition(double temperature, double humidity);
	
}
