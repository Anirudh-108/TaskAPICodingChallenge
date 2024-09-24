package com.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.exception.InvalidIdException;
import com.task.model.Task;
import com.task.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Task addTask(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Task getTaskById(int taskId) throws InvalidIdException {
		Optional<Task> optional = taskRepository.findById(taskId);
		if (optional.isEmpty()) {
			throw new InvalidIdException("Given Task ID is Invalid...");
		}
		return optional.get();
	}

}
