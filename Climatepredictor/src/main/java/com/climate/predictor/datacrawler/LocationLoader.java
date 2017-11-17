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
import java.util.List;

import org.apache.log4j.Logger;

import com.climate.predictor.constants.AppMessages;
import com.climate.predictor.exception.ClimateException;
import com.climate.predictor.model.CSVDataModel;
import com.climate.predictor.model.Coordinates;
import com.climate.predictor.model.LocationModel;
import com.climate.predictor.utils.APIUtil;
import com.climate.predictor.utils.CommonUtil;

/**
 * The Class to collect location specific informations.
 * Uses google API's
 * 
 * @author Sujith M
 * 
 */
public class LocationLoader {
	
	/** The Constant log. */
	private static final Logger log = Logger.getLogger(LocationLoader.class);
	
	/**
	 * Gets the data using API.
	 *
	 * @param dataFromCSV :- data from "inputTxt"
	 * @return Location details
	 * @throws ClimateException the climate exception
	 */
	public List<LocationModel> getDataUsingAPI(final List<CSVDataModel> dataFromCSV) throws ClimateException {
		List<LocationModel> locationList = new ArrayList<LocationModel>();
		
		for(CSVDataModel currentObj : dataFromCSV) {
			if(!CommonUtil.isNull(currentObj)) {
				// Calling geoCode API
				Coordinates coordinatesObj = APIUtil.getGeoCode(currentObj.getLocationAddress());
				if(coordinatesObj != null) {
					
					// Calling elevation API
					Double elevation = APIUtil.getElevation(coordinatesObj.getLatitude(),
							coordinatesObj.getLongitude());
					
					// Calling Timezone
					String timezone = APIUtil.getTimeZone(coordinatesObj.getLatitude(),
							coordinatesObj.getLongitude());
					if (elevation != null && timezone != null) {
						LocationModel locationModelObj = new LocationModel();
						locationModelObj.setCoordinate(coordinatesObj);
						locationModelObj.setElevation(elevation);
						locationModelObj.setCsvData(currentObj);
						locationModelObj.setTimeZone(timezone);
						locationList.add(locationModelObj);
					} else {
						log.warn(AppMessages.API_CALL_FAILTURE);
					}
				} else {
					log.warn(AppMessages.API_CALL_FAILTURE);
				}
			}
		}
		return locationList;
	}
}
