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

package com.climate.predictor.constants;

import java.text.SimpleDateFormat;

import com.google.gson.JsonParser;

/**
 * The Class AppConstants.
 * 
 * @author Sujith M
 * 
 */
public class AppConstants {

	/** The Constant inputDatePattern. */
	public static final String inputDatePattern = "yyyy-MM-dd HH";

	/** The Constant inputDateformat. */
	public static final SimpleDateFormat inputDateformat = new SimpleDateFormat(AppConstants.inputDatePattern);

	/** The Constant outputDatePattern. */
	public static final String outputDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	/** The Constant outputDateformat. */
	public static final SimpleDateFormat outputDateformat = new SimpleDateFormat(AppConstants.outputDatePattern);

	/** The Constant sectionSplitter. */
	public static final String sectionSplitter = "\\§";

	/** The Constant forecastSize. */
	public static final int forecastSize = 10;

	/** The Constant comma. */
	public static final String comma = ",";

	/** The Constant DAY_STRING. */
	public static final String DAY_STRING = "DAY";

	/** The Constant NIGHT_STRING. */
	public static final String NIGHT_STRING = "NIGHT";

	/** The Constant temperatureCold. */
	public static final String temperatureCold = "Cold";

	/** The Constant temperatureNormal. */
	public static final String temperatureNormal = "Normal";

	/** The Constant temperatureHot. */
	public static final String temperatureHot = "Hot";

	/** The Constant SNOW_STRING. */
	public static final String SNOW_STRING = "Show";

	/** The Constant RAIN_STRING. */
	public static final String RAIN_STRING = "Rain";

	/** The Constant SUNNY_STRING. */
	public static final String SUNNY_STRING = "Sunny";

	/** The Constant NORMAL_STRING. */
	public static final String NORMAL_STRING = "Normal";

	/** The Constant inputFileName. */
	public static final String inputFileName = "inputData.csv";

	/** The Constant bomHistoryURL. */
	public static final String bomHistoryURL = "http://www.bom.gov.au/climate/dwo/";

	/** The Constant geoCodeAPIURL. */
	public static final String geoCodeAPIURL = "https://maps.googleapis.com/maps/api/geocode/json?address=";

	/** The Constant elevationAPIURL. */
	public static final String elevationAPIURL = "https://maps.googleapis.com/maps/api/elevation/json?locations=";

	/** The Constant timeZoneAPIURL. */
	public static final String timeZoneAPIURL = "https://maps.googleapis.com/maps/api/timezone/json?location=";

	/** The Constant geoCodeAPIKey. */
	public static final String geoCodeAPIKey = "AIzaSyBfdF6fddPy-mLOdM8JMuk2ZfcoHM8HQrU";

	/** The Constant elevationAPIKey. */
	public static final String elevationAPIKey = "AIzaSyBSlvWQGW3UY2l0XhaPZMAYpgRGrd_Ek7M";

	/** The Constant timeZoneAPIKey. */
	public static final String timeZoneAPIKey = "AIzaSyDC7UbI45wrOJ4S0yqq8rCVZ3Uj258axPA";

	/** The Constant andOutput. */
	public static final String andOutput = "&output=";

	/** The Constant andKey. */
	public static final String andKey = "&key=";

	/** The Constant andTimeStamp. */
	public static final String andTimeStamp = "&timestamp=";

	/** The Constant textPath. */
	public static final String textPath = "/text/";

	/** The Constant dot. */
	public static final String dot = ".";

	/** The Constant fileType. */
	public static final String fileType = ".csv";

	/** The Constant parser. */
	public static final JsonParser parser = new JsonParser();

	/** The Constant unicode. */
	public static final String unicode = "UTF-8";

	/** The Constant outputType. */
	public static final String outputType = "json";

	/** The Constant json_results. */
	public static final String json_results = "results";

	/** The Constant json_geometry. */
	public static final String json_geometry = "geometry";

	/** The Constant json_location. */
	public static final String json_location = "location";

	/** The Constant json_lat. */
	public static final String json_lat = "lat";

	/** The Constant json_lng. */
	public static final String json_lng = "lng";

	/** The Constant json_elevation. */
	public static final String json_elevation = "elevation";

	/** The Constant json_timeZoneId. */
	public static final String json_timeZoneId = "timeZoneId";

	/** The Constant bomDateFormat. */
	public static final String bomDateFormat = "yyyyMM";

	/** The Constant historyDataDateFormat. */
	public static final String historyDataDateFormat = "yyyy-MM-d";

	/** The Constant ecsapeString. */
	public static final String ecsapeString = ",\"Date\",\"Minimum temperature (\uFFFDC)\",\"Maximum temperature (\uFFFDC)\",\"Rainfall (mm)\",\"Evaporation (mm)\",\"Sunshine (hours)\",\"Direction of maximum wind gust \",\"Speed of maximum wind gust (km/h)\",\"Time of maximum wind gust\",\"9am Temperature (\uFFFDC)\",\"9am relative humidity (%)\",\"9am cloud amount (oktas)\",\"9am wind direction\",\"9am wind speed (km/h)\",\"9am MSL pressure (hPa)\",\"3pm Temperature (\uFFFDC)\",\"3pm relative humidity (%)\",\"3pm cloud amount (oktas)\",\"3pm wind direction\",\"3pm wind speed (km/h)\",\"3pm MSL pressure (hPa)\"";

	/** The Constant lineSplitter. */
	public static final String lineSplitter = "\\r?\\n";

	/** The Constant newLine. */
	public static final String newLine = "\n";

	/** The Constant queueSize. */
	public static final int queueSize = 10;

	/** The Constant pipeSymbol. */
	public static final String pipeSymbol = "|";

	/** The Constant outputFile. */
	public static final String outputFile = "output.txt";
	
	/** The Constant timeSeriesAlgorithm. */
	public static final String timeSeriesAlgorithm = "TimeSeriesAlgorithm";

}
