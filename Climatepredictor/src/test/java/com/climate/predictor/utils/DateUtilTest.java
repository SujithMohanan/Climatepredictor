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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.climate.predictor.constants.AppConstants;

/**
 * The Class DateUtilTest.
 * 
 * @author Sujith M
 * 
 */
public class DateUtilTest {


	/** The cal. */
	private Calendar cal;


	/**
	 * Before each test.
	 */
	@Before
	public void beforeEachTest() {
		cal = Calendar.getInstance();
	}

	/**
	 * After each test.
	 */
	@After
	public void afterEachTest() {
		cal = null;
	}

	/**
	 * Check date invald.
	 */
	@Test
	public void checkDateInvald() {

		assertEquals(null, DateUtil.checkDate("Sujith"));
		cal.add(Calendar.HOUR, -10);
		Date date = cal.getTime();
		assertEquals(null, DateUtil.checkDate(AppConstants.inputDateformat.format(date)));
	}

	/**
	 * Future date test.
	 */
	@Test
	public void futureDateTest() {

		cal.add(Calendar.HOUR, 9);
		assertTrue(DateUtil.isNotFutureDate(cal.getTime()));
		cal.add(Calendar.HOUR, -10);
		assertFalse(DateUtil.isNotFutureDate(cal.getTime()));
	}

	/**
	 * Gets the month test.
	 *
	 * @return the month test
	 */
	@Test
	public void getMonthTest() {

		assertEquals(cal.get(Calendar.MONTH), DateUtil.getMonth(new Date()));
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.MONTH, 2);
		assertNotSame(cal.get(Calendar.MONTH), DateUtil.getMonth(cal2.getTime()));
		assertEquals(-1, DateUtil.getMonth(null));
	}

	/**
	 * Gets the choice of time test.
	 *
	 * @return the choice of time test
	 */
	@Test
	public void getChoiceOfTimeTest() {

		cal.set(Calendar.HOUR_OF_DAY, 3);
		assertEquals(AppConstants.NIGHT_STRING, DateUtil.getChoiceOfTime(cal.getTime()));
		assertNotEquals(AppConstants.DAY_STRING, DateUtil.getChoiceOfTime(cal.getTime()));
		cal.set(Calendar.HOUR_OF_DAY, 17);
		assertEquals(AppConstants.DAY_STRING, DateUtil.getChoiceOfTime(cal.getTime()));
		assertNotEquals(AppConstants.NIGHT_STRING, DateUtil.getChoiceOfTime(cal.getTime()));
	}

}
