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
 * The Class HistoryDataModel.
 * 
 * @author Sujith M
 * 
 */
public class HistoryDataModel {

	/**
	 * Gets the last year data.
	 *
	 * @return the last year data
	 */
	public LastYearDataModel getLastYearData() {
		return lastYearData;
	}

	/**
	 * Sets the last year data.
	 *
	 * @param lastYearData the new last year data
	 */
	public void setLastYearData(LastYearDataModel lastYearData) {
		this.lastYearData = lastYearData;
	}

	/**
	 * Gets the location data model.
	 *
	 * @return the location data model
	 */
	public LocationModel getLocationDataModel() {
		return locationDataModel;
	}

	/**
	 * Sets the location data model.
	 *
	 * @param locationDataModel the new location data model
	 */
	public void setLocationDataModel(LocationModel locationDataModel) {
		this.locationDataModel = locationDataModel;
	}

	/** The last year data. */
	private LastYearDataModel lastYearData;

	/** The location data model. */
	private LocationModel locationDataModel;


}
