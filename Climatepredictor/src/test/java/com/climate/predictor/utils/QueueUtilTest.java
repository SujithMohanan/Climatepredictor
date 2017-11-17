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
import static org.junit.Assert.assertNotEquals;

import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.climate.predictor.constants.AppConstants;

/**
 * The Class QueueUtilTest.
 * 
 * @author Sujith M
 * 
 */
public class QueueUtilTest {

	/** The test queue. */
	private static LinkedList<Double> testQueue = QueueUtil.createQueue();

	/**
	 * Before class.
	 */
	@BeforeClass
	public static void beforeClass() {
		for (int i = 0; i < 20; i++) {
			testQueue.add((double) i);
		}
	}

	/**
	 * After class.
	 */
	@AfterClass
	public static void afterClass() {
		testQueue = null;
	}

	/**
	 * Queue size fixed success test.
	 */
	@Test
	public void queueSizeFixedSuccessTest() {
		assertEquals(AppConstants.queueSize, testQueue.size());
	}

	/**
	 * Queue size fixed fail test.
	 */
	@Test
	public void queueSizeFixedFailTest() {
		assertNotEquals(AppConstants.queueSize - 1, testQueue.size());
	}

}
