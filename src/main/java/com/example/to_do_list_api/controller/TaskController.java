package com.example.to_do_list_api.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.to_do_list_api.model.Task;
import com.example.to_do_list_api.service.TaskService;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping
	public ResponseEntity<List<Task>> findAll() {
		List<Task> list = taskService.findAllTasks();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> findById(@PathVariable Long id) {
		Optional<Task> task = taskService.findTaskById(id);
		return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public Task insert(@RequestBody Task task) {
		return taskService.insertTask(task);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task obj) {
		try {
			Task updatedTask = taskService.updateTask(id, obj);
			return ResponseEntity.ok(updatedTask);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Task> delete(@PathVariable Long id) {
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
}
