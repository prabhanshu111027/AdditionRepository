package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.QuestionRequest;
import com.example.demo.model.QuestionResponse;
import com.example.demo.model.User;
import com.example.demo.service.AdditionServiceImpl;

@RestController
public class AdditionServiceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdditionServiceController.class);

	@Autowired
	private AdditionServiceImpl additionServiceImpl;
	LinkedHashMap<String, Integer> questionMap = new LinkedHashMap<String, Integer>();

	/**
	 * @GetMapping
	 * @param user
	 * @return
	 */
	@GetMapping("/question")
	public ResponseEntity<String> sendQuestion(@RequestBody User user) {
		LOGGER.info("sendQuestion Method of controller started ");
		try {
			String name = user.getName();
			questionMap = additionServiceImpl.getRandomNumberMap();
			Set<String> key = questionMap.keySet();
			String questionStr = "Hello " + name + "; Please sum the numbers  " + key;

			QuestionResponse obj = new QuestionResponse();
			obj.setQuestion(questionStr);
			LOGGER.info(questionStr);
			LOGGER.info("sendQuestion Method of controller Ended ");
			return new ResponseEntity<>(questionStr, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @GetMapping
	 * @param QuestionRequest
	 * @return
	 */
	@GetMapping("/answer")
	public ResponseEntity<String> verifyQuestionAndResult(@RequestBody QuestionRequest questionRequest) {
		LOGGER.info("verifyQuestionAndResult Method of controller started ");
		try {
			String result = questionRequest.getResult();
			result = additionServiceImpl.extractNumbers(result);
			String[] elements = result.split(",");
			String resultkey = elements[0] + "," + elements[1] + "," + elements[2];
			int resultSum = Integer.parseInt(elements[3]);
			
			if (questionMap.containsKey(resultkey) && questionMap.get(resultkey) == resultSum) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
