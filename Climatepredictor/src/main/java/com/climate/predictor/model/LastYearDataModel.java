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

package com.climate.predictor.model;

/**
 * The Class LastYearDataModel.
 * 
 * @author Sujith M
 * 
 */
public class LastYearDataModel {

	/** The temperature array. */
	private double[] temperatureArray;
	
	/** The humidity array. */
	private double[] humidityArray;
	
	/** The pressure array. */
	private double[] pressureArray;
	
	/**
	 * Gets the temperature array.
	 *
	 * @return the temperature array
	 */
	public double[] getTemperatureArray() {
		return temperatureArray;
	}
	
	/**
	 * Sets the temperature array.
	 *
	 * @param temperatureArray the new temperature array
	 */
	public void setTemperatureArray(double[] temperatureArray) {
		this.temperatureArray = temperatureArray;
	}
	
	/**
	 * Gets the humidity array.
	 *
	 * @return the humidity array
	 */
	public double[] getHumidityArray() {
		return humidityArray;
	}
	
	/**
	 * Sets the humidity array.
	 *
	 * @param humidityArray the new humidity array
	 */
	public void setHumidityArray(double[] humidityArray) {
		this.humidityArray = humidityArray;
	}
	
	/**
	 * Gets the pressure array.
	 *
	 * @return the pressure array
	 */
	public double[] getPressureArray() {
		return pressureArray;
	}
	
	/**
	 * Sets the pressure array.
	 *
	 * @param pressureArray the new pressure array
	 */
	public void setPressureArray(double[] pressureArray) {
		this.pressureArray = pressureArray;
	}
}
