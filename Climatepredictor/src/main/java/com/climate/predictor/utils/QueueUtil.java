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

import java.util.LinkedList;

import com.climate.predictor.constants.AppConstants;

/**
 * The Class QueueUtil.
 * 
 * @author Sujith M
 * 
 */
public class QueueUtil {

	/**
	 * Creates the queue with size 10.
	 *
	 * @return the linked list
	 */
	public static LinkedList<Double> createQueue() {
		LinkedList<Double> queue = new LinkedList<Double>() {

			private static final long serialVersionUID = 1L;

			public boolean add(Double object) {
				boolean result;
				if (this.size() < AppConstants.queueSize)
					result = super.add(object);
				else {
					super.removeFirst();
					result = super.add(object);
				}
				return result;
			}
		};

		return queue;
	}
}
