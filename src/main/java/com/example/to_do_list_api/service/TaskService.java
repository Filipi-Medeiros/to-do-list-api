package com.example.to_do_list_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.to_do_list_api.model.Task;
import com.example.to_do_list_api.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public List<Task> findAllTasks() {
		return taskRepository.findAll();
	}

	public Optional<Task> findTaskById(Long id) {
		return taskRepository.findById(id);
	}

	public Task insertTask(Task task) {
		return taskRepository.save(task);
	}

	public Task updateTask(Long id, Task obj) {
		return taskRepository.findById(id).map(task -> {
			task.setTitle(obj.getTitle());
			task.setDescription(obj.getDescription());
			task.setCompleted(obj.getCompleted());
			return taskRepository.save(task);
		}).orElseThrow(() -> new RuntimeException("Task not found"));
	}
	
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
}
