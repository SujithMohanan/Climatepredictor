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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.climate.predictor.constants.AppConstants;
import com.climate.predictor.constants.AppMessages;

/**
 * The Class DateUtil.
 * For date common validations
 * 
 * @author Sujith M
 * 
 */
public class DateUtil {

	/** The log. */
	private static final Logger log = Logger.getLogger(DateUtil.class);

	/**
	 * Prompt entry.
	 *
	 * @param message : Message to be displayed with System.in
	 */
	public static void promptEntry(final String message) {
		log.info(AppMessages.DATE_FORMAT_MESSAGE + AppConstants.inputDatePattern);
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		log.info(message + AppConstants.inputDateformat.format(date));
	}

	/**
	 * Check date.
	 *
	 * @param dateString
	 * @return the date
	 */
	public static Date checkDate(final String dateString) {
		Date date = null;
		if(!CommonUtil.isNull(dateString)) {			
			AppConstants.inputDateformat.setLenient(false);
			try {
				date = AppConstants.inputDateformat.parse(dateString);
				if (!isNotFutureDate(date)) {
					promptEntry(AppMessages.FUTURE_DATE_MESSAGE);
					date = null;
				}
			} catch (ParseException e) {
				return null;
			}
		}

		return date;
	}

	/**
	 * Checks if date is not a future date.
	 *
	 * @param date
	 * @return true, if is future date
	 */
	public static boolean isNotFutureDate(final Date date) {
		if(!CommonUtil.isNull(date)){			
			if (date.after(new Date())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the month of entered date.
	 *
	 * @param date
	 * @return month
	 */
	public static int getMonth(final Date date) {
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(Calendar.MONTH);
		} else {
			return -1;
		}
	}
	
	/**
	 * Gets only date.
	 *
	 * @param date :- java.util.date
	 * @return (int) date
	 */
	public static int getDate(Date date) {
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(Calendar.DATE);
		} else {
			return -1;
		}
	}

	/**
	 * Gets the choice of time.
	 *
	 * @param date
	 * @return Choice (DAY/NIGHT)
	 */
	public static String getChoiceOfTime(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		// Check if hour is between 9 pm and 9am
		if (hour <= 21 && hour >= 9) 
		{
			return AppConstants.DAY_STRING;
		} else {
			return AppConstants.NIGHT_STRING;
		}
	}

	/**
	 * Gets 2 months of previous year.
	 *
	 * @param predictionDate
	 * @return List<TimeFrame>
	 */
	public static List<String> getInputTimeFrame(final Date predictionDate) {
		int predictionMonth = getMonth(predictionDate);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(AppConstants.bomDateFormat);
		int currentMonth = c.get(Calendar.MONTH);
		List<String> timeFrameList = new ArrayList<String>();

		c.set(Calendar.MONTH, predictionMonth);
		if (predictionMonth >= currentMonth) {
			c.add(Calendar.YEAR, -1);
		}

		timeFrameList.add(format.format(c.getTime()));
		c.add(Calendar.MONTH, -1);
		timeFrameList.add(format.format(c.getTime()));
		Collections.sort(timeFrameList);
		return timeFrameList;
	}
	
	/**
	 * Gets the last year date.
	 *
	 * @param predictionDate
	 * @return 
	 */
	public static String getLastYearDate(final Date predictionDate) {
		
		
		int predictionMonth = getMonth(predictionDate);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(AppConstants.historyDataDateFormat);
		int currentMonth = c.get(Calendar.MONTH);
		c.set(Calendar.MONTH, predictionMonth);
		c.set(Calendar.DATE, getDate(predictionDate));
		if (predictionMonth >= currentMonth) {
			c.add(Calendar.YEAR, -1);
		}
		String date = format.format(c.getTime());
		return date;
	}
	
	
}
