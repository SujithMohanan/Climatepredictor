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

package com.climate.predictor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.climate.predictor.constants.AppConstants;
import com.climate.predictor.constants.AppMessages;
import com.climate.predictor.exception.ClimateException;
import com.climate.predictor.model.Coordinates;
import com.google.gson.JsonObject;

/**
 * The Class APIUtil.
 * 
 * @author Sujith M
 * 
 */
public class APIUtil {

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(ConsoleInputUtil.class);

	/**
	 * Calls Google's geoCode API.
	 *
	 * @param address :- Address
	 * @return Coordinate<latitude,longitude>
	 * @throws ClimateException
	 */
	public static Coordinates getGeoCode(final String address) throws ClimateException {
		try {
			String urlToHit = encode(AppConstants.geoCodeAPIURL, urlEncode(address), AppConstants.geoCodeAPIKey);
			InputStream is = hitURL(urlToHit);
			if (is != null) {
				String resultString = getStringFromInputStream(is);

				JsonObject geoObj = AppConstants.parser.parse(resultString).getAsJsonObject();
				JsonObject locObj = geoObj.getAsJsonArray(AppConstants.json_results).get(0).getAsJsonObject()
						.getAsJsonObject(AppConstants.json_geometry).getAsJsonObject(AppConstants.json_location);
				Double lat = locObj.get(AppConstants.json_lat).getAsDouble();
				Double lng = locObj.get(AppConstants.json_lng).getAsDouble();
				log.info(AppMessages.API_CALL_URL + urlToHit);
				return new Coordinates(lat, lng);
			}
		} catch (IOException e) {
			throw new ClimateException(e.getMessage());
		}
		return null;
	}

	/**
	 * Calls Google's elevation API.
	 *
	 * @param latitude
	 * @param longitude
	 * @return elevation
	 * @throws ClimateException
	 */
	public static Double getElevation(final Double latitude, final Double longitude) throws ClimateException {

		try {
			String latLLongString = latitude + AppConstants.comma + longitude;
			String urlToHit = encode(AppConstants.elevationAPIURL, urlEncode(latLLongString),
					AppConstants.elevationAPIKey);
			InputStream is = hitURL(urlToHit);
			if (is != null) {
				String resultString = getStringFromInputStream(is);
				JsonObject eleObj = AppConstants.parser.parse(resultString).getAsJsonObject();
				Double elevation = eleObj.getAsJsonArray(AppConstants.json_results).get(0).getAsJsonObject()
						.get(AppConstants.json_elevation).getAsDouble();
				log.info(AppMessages.API_CALL_URL + urlToHit);
				return elevation;
			}
		} catch (IOException e) {
			throw new ClimateException(e.getMessage());
		}
		return null;
	}

	/**
	 * Gets the time zone.
	 *
	 * @param latitude
	 * @param longitude
	 * @return TimeZone
	 * @throws ClimateException
	 */
	public static String getTimeZone(final Double latitude, final Double longitude) throws ClimateException {

		try {

			String latLLongString = urlEncode(latitude + AppConstants.comma + longitude) + AppConstants.andTimeStamp
					+ urlEncode(Long.toString((Calendar.getInstance().getTimeInMillis() / 1000)));
			String urlToHit = encode(AppConstants.timeZoneAPIURL, latLLongString, AppConstants.timeZoneAPIKey);
			InputStream is = hitURL(urlToHit);
			if (is != null) {
				String resultString = getStringFromInputStream(is);
				JsonObject timeZoneObj = AppConstants.parser.parse(resultString).getAsJsonObject();
				String timezone = timeZoneObj.get(AppConstants.json_timeZoneId).getAsString();
				log.info(AppMessages.API_CALL_URL + urlToHit);
				return timezone;
			}
		} catch (IOException e) {
			throw new ClimateException(e.getMessage());
		}
		return null;
	}

	/**
	 * Calls historic data API.
	 * http://www.bom.gov.au/climate/dwo/
	 *
	 * @param Url :- URL to hit
	 * @return the historic data
	 * @throws ClimateException
	 *             the climate exception
	 */
	public static String getHistoricData(final String Url) throws ClimateException {
		try {
			InputStream is = hitURL(Url);
			log.info(AppMessages.API_CALL_URL + Url);
			if (is != null) {
				String resultString = getStringFromInputStream(is);
				return resultString;
			}
		} catch (IOException e) {
			throw new ClimateException(e.getMessage());
		}
		return null;
	}

	/**
	 * Url encode.
	 *
	 * @param value
	 * @return the string
	 * @throws ClimateException
	 */
	private static String urlEncode(final String value) throws ClimateException {
		try {
			String encodedString = URLEncoder.encode(value, AppConstants.unicode);
			return encodedString;
		} catch (UnsupportedEncodingException | NullPointerException e) {
			throw new ClimateException(e.getMessage());
		}
	}

	/**
	 * Encode.
	 *
	 * @param baseURL
	 * @param parameters
	 * @param key
	 * @return the string
	 */
	public static String encode(final String baseURL, final String parameters, final String key) {
		String s = baseURL + parameters + AppConstants.andOutput + AppConstants.outputType + AppConstants.andKey + key;
		return s;
	}

	/**
	 * Hit URL.
	 *
	 * @param address
	 * @return inputstream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static InputStream hitURL(final String address) throws IOException {
		URL url = new URL(address);
		return url.openStream();
	}

	/**
	 * Read inputstream, create string and return that string.
	 *
	 * @param is :- inputStream
	 * @return the string from input stream
	 * @throws ClimateException
	 *             the climate exception
	 */
	private static String getStringFromInputStream(final InputStream is) throws ClimateException {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(AppConstants.newLine);
			}

		} catch (IOException e) {
			throw new ClimateException(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new ClimateException(e.getMessage());
				}
			}
		}

		return sb.toString();

	}
}
