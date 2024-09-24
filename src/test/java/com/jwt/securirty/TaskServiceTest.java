package com.jwt.securirty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.task.exception.InvalidIdException;
import com.task.model.Task;
import com.task.service.TaskService;

@SpringBootTest
public class TaskServiceTest {

	@Autowired
	private TaskService taskService;

	@Test
	public void getAllTasksTest() {
		List<Task> actualList = taskService.getAllTasks();

		assertNotNull(actualList);

		int expectedTasks = 3;
		int actualTasks = actualList.size();

		assertEquals(expectedTasks, actualTasks);
	}

	@Test
	public void getTaskByIdTest() throws InvalidIdException {
		Task task = taskService.getTaskById(1);// Correct Task Id

		assertNotNull(task);

		int expectedId = 1;
		int actualId = task.getId();

		assertEquals(expectedId, actualId);
	}

	@Test
	public void getTaskByIdWithExceptionTest() {
		String errorMsg = "";
		try {
			taskService.getTaskById(5);// Incorrect Task Id
		} catch (InvalidIdException e) {
			errorMsg = e.getMessage();
		}

		String expectedMsg = "Given Task ID is Invalid...";

		assertEquals(expectedMsg, errorMsg);

	}

}
