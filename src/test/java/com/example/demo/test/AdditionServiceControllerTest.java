package com.example.demo.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.ResponseEntity;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.demo.controller.AdditionServiceController;
import com.example.demo.model.QuestionRequest;
import com.example.demo.model.User;
import com.example.demo.service.AdditionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AdditionServiceControllerTest {
	@Mock
	private AdditionServiceImpl additionServiceImpl;
	@InjectMocks
	private AdditionServiceController additionServiceController;

	@Test
	public void sendQuestion() throws Exception {
		User user = new User();
		user.setName("Prabhanshu");
		ResponseEntity<String> responseEntity = additionServiceController.sendQuestion(user);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

	}

	@Test
	public void verifyQuestionAndResult() throws Exception {
		QuestionRequest questionRequest = new QuestionRequest();
		questionRequest
				.setResult("Great. The original question was “Please sum the numbers 9,5,3 and the answer is 15.");
		String result = "9,5,3,15";
		when(additionServiceImpl.extractNumbers(questionRequest.getResult())).thenReturn(result);
		ResponseEntity<String> responseEntity = additionServiceController.verifyQuestionAndResult(questionRequest);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);

	}

}
