package com.example.demo.service;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.controller.AdditionServiceController;

@Service
public class AdditionServiceImpl implements AdditionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdditionServiceController.class);

	/**
	 * @Genrate 3 Random Number
	 * @put in Linked HashMap
	 * @return Map
	 */

	@Override
	public LinkedHashMap<String, Integer> getRandomNumberMap() {
		LOGGER.info("getRandomNumber Method of Service started ");
		int max = 50;
		int min = 1;
		int range = max - min + 1;
		int random_int1 = (int) Math.floor(Math.random() * range + min);
		int random_int2 = (int) Math.floor(Math.random() * range + min);
		int random_int3 = (int) Math.floor(Math.random() * range + min);
		int sum = random_int1 + random_int2 + random_int3;
		String key = random_int1 + "," + random_int2 + "," + random_int3;
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put(key, sum);
		LOGGER.info("getRandomNumber Method of Service Ended");
		return map;
	}

	/**
	 * @Extract Integer Part from String
	 * @return Integer value in a String
	 */
	@Override
	public String extractNumbers(String result) {
		String returnStr = result.replaceAll("[^\\d]", " ");
		returnStr = returnStr.trim();
		returnStr = returnStr.replaceAll(" +", ",");
		if (returnStr.equals(""))
			return "-1";
		return returnStr;
	}
}
