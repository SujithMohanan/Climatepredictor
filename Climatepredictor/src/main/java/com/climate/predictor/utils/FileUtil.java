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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.climate.predictor.constants.AppConstants;
import com.climate.predictor.constants.AppMessages;
import com.climate.predictor.exception.ClimateException;
import com.climate.predictor.model.CSVDataModel;
import com.climate.predictor.model.LocationModel;

/**
 * The Class FileUtil.
 * 
 * @author Sujith M
 * 
 */
public class FileUtil {

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(FileUtil.class);

	/**
	 * Read input file.
	 * Split with "§"
	 * Create CSVDataModel objects
	 *
	 * @param fileName
	 * @return the list
	 * @throws ClimateException
	 */
	public List<CSVDataModel> readInputFile(final String fileName) throws ClimateException {
		List<CSVDataModel> dataFromCSV = new ArrayList<CSVDataModel>();
		ClassLoader classLoader = getClass().getClassLoader();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(fileName)))) {
			String currentLine;
			while ((currentLine = in.readLine()) != null) {
				String[] splitArray = currentLine.split(AppConstants.sectionSplitter, -1);
				if (splitArray.length == 3) {
					String locationName = splitArray[0].trim();
					String iataCode = splitArray[1].trim();
					String locationAddress = splitArray[2].trim();
					dataFromCSV.add(new CSVDataModel(locationName, iataCode, locationAddress));
				}
			}
		} catch (IOException e) {
			throw new ClimateException(e.getMessage());
		} catch (Exception e) {
			throw new ClimateException(e.getMessage());
		}
		log.info(AppMessages.CSV_READ_SUCCESSFULL);
		return dataFromCSV;
	}

	/**
	 * Write output to output.txt.
	 *
	 * @param finalOutputList
	 * @return true, if successful
	 * @throws ClimateException 
	 */
	public static boolean writeOutput(final List<LocationModel> finalOutputList) throws ClimateException {
		boolean status = false;
		File file = new File(AppConstants.outputFile);
		file.delete();
		if(!file.exists()) {
			try {
				file.createNewFile();
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

					for(LocationModel currentObj : finalOutputList) {
						String outputString = currentObj.getOutputString()+AppConstants.newLine;
						bw.write(outputString);
						log.info(outputString);
					}

				status = true;

			} catch (IOException e) {

				throw new ClimateException(e.getMessage());

			}
			} catch (IOException e) {
				new ClimateException(e.getMessage());
			}
		}
		return status;
	}
	
}
