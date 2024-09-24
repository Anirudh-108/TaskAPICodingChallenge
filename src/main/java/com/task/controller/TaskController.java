package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.MessageDto;
import com.task.exception.InvalidIdException;
import com.task.model.Task;
import com.task.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/add")
	public Task addTask(@RequestBody Task task) {
		return taskService.addTask(task);
	}

	@GetMapping("/all")
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}

	@GetMapping("/one/{taskId}")
	public ResponseEntity<?> getTaskById(@PathVariable int taskId, MessageDto message) {
		try {
			Task task = taskService.getTaskById(taskId);
			return ResponseEntity.ok(task);
		} catch (InvalidIdException e) {
			message.setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(message);
		}
	}

	@PutMapping("/update/{taskId}")
	public ResponseEntity<?> updateTaskById(@PathVariable int taskId, @RequestBody Task task, MessageDto message) {
		try {
			task = taskService.updateTaskById(taskId, task);
			return ResponseEntity.ok(task);
		} catch (InvalidIdException e) {
			message.setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(message);
		}
	}

	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<?> deleteTaskById(@PathVariable int taskId, MessageDto message) {
		try {
			taskService.deleteTaskById(taskId);
			message.setMessage("Task deleted successfully");
			return ResponseEntity.ok(message);
		} catch (InvalidIdException e) {
			message.setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(message);
		}
	}
}
