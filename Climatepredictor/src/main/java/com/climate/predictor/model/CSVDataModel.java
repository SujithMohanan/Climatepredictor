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
 * The Class CSVDataModel.
 * 
 * @author Sujith M
 * 
 */
public class CSVDataModel {

	/** The location name. */
	private String locationName;

	/** The iata code. */
	private String iataCode;

	/** The location address. */
	private String locationAddress;

	/**
	 * Instantiates a new CSV data model.
	 */
	public CSVDataModel() {
		super();
	}

	/**
	 * Instantiates a new CSV data model.
	 *
	 * @param locationName
	 *            the location name
	 * @param iataCode
	 *            the iata code
	 * @param locationAddress
	 *            the location address
	 */
	public CSVDataModel(String locationName, String iataCode, String locationAddress) {
		super();
		this.locationName = locationName;
		this.iataCode = iataCode;
		this.locationAddress = locationAddress;
	}

	/**
	 * Gets the location name.
	 *
	 * @return the location name
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * Sets the location name.
	 *
	 * @param locationName
	 *            the new location name
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * Gets the iata code.
	 *
	 * @return the iata code
	 */
	public String getIataCode() {
		return iataCode;
	}

	/**
	 * Sets the iata code.
	 *
	 * @param iataCode
	 *            the new iata code
	 */
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	/**
	 * Gets the location address.
	 *
	 * @return the location address
	 */
	public String getLocationAddress() {
		return locationAddress;
	}

	/**
	 * Sets the location address.
	 *
	 * @param locationAddress
	 *            the new location address
	 */
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	/**
	 * Gets CSVDataModel to String.
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return "CSVDataModel [locationName=" + locationName + ", iataCode=" + iataCode + ", locationAddress="
				+ locationAddress + "]";
	}

}
