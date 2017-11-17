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
package com.climate.predictor.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.climate.predictor.exception.ClimateException;
import com.climate.predictor.model.Coordinates;

/**
 * The Class APIUtilTest.
 * 
 * @author Sujith M
 * 
 */
public class APIUtilTest {


	/**
	 * Gets the time zone test.
	 *
	 * @return the time zone test
	 * @throws ClimateException the climate exception
	 */
	@Test
	public void getTimeZoneTest() throws ClimateException {
		Coordinates coordinatesObj = new Coordinates(27.1750151, 78.0421552);
		String expected = "Asia/Calcutta";
		String actual = APIUtil.getTimeZone(coordinatesObj.getLatitude(), coordinatesObj.getLongitude());
		assertEquals(expected, actual);
	}

	/**
	 * Encode test.
	 */
	@Test
	public void encodeTest() {
		String baseURL = "https://www.google.com/";
		String parameters = "TajMahal/";
		String key = "India";
		String expected = "https://www.google.com/TajMahal/&output=json&key=India";
		String actual = APIUtil.encode(baseURL, parameters, key);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

}
