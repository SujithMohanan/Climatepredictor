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

import java.util.List;

/**
 * The Class ArrayUtil.
 * 
 * @author Sujith M
 * 
 */
public class ArrayUtil {

	/**
	 * Gets the nearest element in the array compared to the previousValue argument.
	 *
	 * @param inputArray :- inputArray
	 * @param previousValue :- value to compare
	 * @return the nearest
	 */
	public static double getNearestMeasurement(final double[] inputArray, final double previousValue) {
		
		double nearest = inputArray[0];
		double difference = Math.abs(previousValue - nearest);
		for (double val : inputArray) {
			if (Math.abs(previousValue - val) < difference) {
				difference = Math.abs(previousValue - val);
				nearest = val;
			}
		}
		return nearest;
	}
	
	/**
	 * Checks if list is valid.
	 *
	 * @param <T> the generic type
	 * @param list :- list
	 * @return true, if is valid list
	 */
	public static <T> boolean isValidList(List<T> list) {
		if(list != null) {
			if(list.size()>0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
