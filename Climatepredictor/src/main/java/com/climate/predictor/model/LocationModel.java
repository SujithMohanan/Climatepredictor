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

import com.climate.predictor.constants.AppConstants;

/**
 * The Class LocationModel.
 * 
 * @author Sujith M
 * 
 */
public class LocationModel {

	/** The csv data. */
	private CSVDataModel csvData;

	/** The coordinate. */
	private Coordinates coordinate;

	/** The elevation. */
	private Double elevation;

	/** The time zone. */
	private String timeZone;

	/** The temperature. */
	private double temperature;

	/** The pressure. */
	private double pressure;

	/** The humidity. */
	private double humidity;

	/** The condition. */
	private String condition;

	/** The date string. */
	private String dateString;
	
	/** The last year date. */
	private String lastYearDate;
	
	/** The last year temp. */
	private double lastYearTemp;
	
	/** The last year pressure. */
	private double lastYearPressure;
	
	/** The last year humidity. */
	private double lastYearHumidity;

	/**
	 * Instantiates a new location model.
	 */
	public LocationModel() {
		super();
	}

	/**
	 * Gets the csv data.
	 *
	 * @return the csv data
	 */
	public CSVDataModel getCsvData() {
		return csvData;
	}

	/**
	 * Sets the csv data.
	 *
	 * @param csvData
	 *            the new csv data
	 */
	public void setCsvData(CSVDataModel csvData) {
		this.csvData = csvData;
	}

	/**
	 * Gets the coordinate.
	 *
	 * @return the coordinate
	 */
	public Coordinates getCoordinate() {
		return coordinate;
	}

	/**
	 * Sets the coordinate.
	 *
	 * @param coordinate
	 *            the new coordinate
	 */
	public void setCoordinate(Coordinates coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Gets the elevation.
	 *
	 * @return the elevation
	 */
	public Double getElevation() {
		return elevation;
	}

	/**
	 * Sets the elevation.
	 *
	 * @param elevation
	 *            the new elevation
	 */
	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	/**
	 * Gets the time zone.
	 *
	 * @return the time zone
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * Sets the time zone.
	 *
	 * @param timeZone
	 *            the new time zone
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * Gets the temperature.
	 *
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * Sets the temperature.
	 *
	 * @param temperature
	 *            the new temperature
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * Gets the pressure.
	 *
	 * @return the pressure
	 */
	public double getPressure() {
		return pressure;
	}

	/**
	 * Sets the pressure.
	 *
	 * @param pressure
	 *            the new pressure
	 */
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	/**
	 * Gets the humidity.
	 *
	 * @return the humidity
	 */
	public double getHumidity() {
		return humidity;
	}

	/**
	 * Sets the humidity.
	 *
	 * @param humidity
	 *            the new humidity
	 */
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	/**
	 * Gets the condition.
	 *
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * Sets the condition.
	 *
	 * @param condition
	 *            the new condition
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * Gets the date string.
	 *
	 * @return the date string
	 */
	public String getDateString() {
		return dateString;
	}

	/**
	 * Sets the date string.
	 *
	 * @param dateString the new date string
	 */
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
	

	/**
	 * Gets the last year date.
	 *
	 * @return the last year date
	 */
	public String getLastYearDate() {
		return lastYearDate;
	}

	/**
	 * Sets the last year date.
	 *
	 * @param lastYearDate the new last year date
	 */
	public void setLastYearDate(String lastYearDate) {
		this.lastYearDate = lastYearDate;
	}

	/**
	 * Gets the last year temp.
	 *
	 * @return the last year temp
	 */
	public double getLastYearTemp() {
		return lastYearTemp;
	}

	/**
	 * Sets the last year temp.
	 *
	 * @param lastYearTemp the new last year temp
	 */
	public void setLastYearTemp(double lastYearTemp) {
		this.lastYearTemp = lastYearTemp;
	}

	/**
	 * Gets the last year pressure.
	 *
	 * @return the last year pressure
	 */
	public double getLastYearPressure() {
		return lastYearPressure;
	}

	/**
	 * Sets the last year pressure.
	 *
	 * @param lastYearPressure the new last year pressure
	 */
	public void setLastYearPressure(double lastYearPressure) {
		this.lastYearPressure = lastYearPressure;
	}

	/**
	 * Gets the last year humidity.
	 *
	 * @return the last year humidity
	 */
	public double getLastYearHumidity() {
		return lastYearHumidity;
	}

	/**
	 * Sets the last year humidity.
	 *
	 * @param lastYearHumidity the new last year humidity
	 */
	public void setLastYearHumidity(double lastYearHumidity) {
		this.lastYearHumidity = lastYearHumidity;
	}

	/**
	 * Gets the output string.
	 *
	 * @return the output string
	 */
	public String getOutputString() {
		return this.csvData.getLocationName() + AppConstants.pipeSymbol
				+ roundToTwoPoints(this.coordinate.getLatitude()) + AppConstants.comma
				+ roundToTwoPoints(this.coordinate.getLongitude()) + AppConstants.comma
				+ roundToTwoPoints(this.elevation) + AppConstants.pipeSymbol + this.dateString
				+ AppConstants.pipeSymbol + this.condition + AppConstants.pipeSymbol
				+ formatSign(roundToTwoPoints(this.temperature)) + AppConstants.pipeSymbol
				+ roundToTwoPoints(this.pressure) + AppConstants.pipeSymbol + roundToTwoPoints(this.humidity);
	}

	/**
	 * Format sign.
	 *
	 * @param value the value
	 * @return the string
	 */
	private String formatSign(double value) {
		return (value > 0 ? "+" : "") + value;
	}

	/**
	 * Round to two points.
	 *
	 * @param value the value
	 * @return the double
	 */
	private double roundToTwoPoints(double value) {
		return (double) Math.round(value * 100) / 100;
	}

}
