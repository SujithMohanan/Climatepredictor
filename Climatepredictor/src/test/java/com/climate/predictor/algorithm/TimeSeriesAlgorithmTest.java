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
package com.climate.predictor.algorithm;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.climate.predictor.constants.AppConstants;

/**
 * The Class TimeSeriesAlgorithmTest.
 * 
 * @author Sujith M
 * 
 */
@RunWith(Parameterized.class)
public class TimeSeriesAlgorithmTest {

	/** The time series algorithm obj. */
	private static TimeSeriesAlgorithm timeSeriesAlgorithmObj;

	/** The input 1. */
	private double input1;
	
	/** The input 2. */
	private double input2;
	
	/** The output. */
	private String output;

	/**
	 * Before class.
	 */
	@BeforeClass
	public static void beforeClass() {
		timeSeriesAlgorithmObj = new TimeSeriesAlgorithm();
	}

	/**
	 * After class.
	 */
	@AfterClass
	public static void afterClass() {
		timeSeriesAlgorithmObj = null;
	}

	/**
	 * Instantiates a new time series algorithm test.
	 *
	 * @param input1 the input 1
	 * @param input2 the input 2
	 * @param output the output
	 */
	public TimeSeriesAlgorithmTest(double input1, double input2, String output) {
		this.input1 = input1;
		this.input2 = input2;
		this.output = output;

	}

	/**
	 * Parameters.
	 *
	 * @return the collection
	 */
	@Parameters
	public static Collection<Object[]> parameters() {
		return Arrays
				.asList(new Object[][] { { 10, 75, AppConstants.SNOW_STRING }, { 10, 35, AppConstants.RAIN_STRING },
						{ 25, 75, AppConstants.NORMAL_STRING }, { 25, 35, AppConstants.NORMAL_STRING },
						{ 45, 75, AppConstants.SUNNY_STRING }, { 45, 35, AppConstants.SUNNY_STRING } });
	}

	/**
	 * Gets the condition test.
	 *
	 * @return the condition test
	 */
	@Test
	public void getconditionTest() {
		assertEquals(output, timeSeriesAlgorithmObj.getcondition(input1, input2));
	}

}
