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
package com.climate.predictor.exception;

import org.junit.Test;

import com.climate.predictor.utils.APIUtil;

/**
 * The Class ClimateExceptionTest.
 * 
 * @author Sujith M
 * 
 */
public class ClimateExceptionTest {

	/**
	 * My test.
	 *
	 * @throws ClimateException the climate exception
	 */
	@Test(expected = ClimateException.class)
	public void myTest() throws ClimateException {
		APIUtil.getGeoCode(null);
	}
}
