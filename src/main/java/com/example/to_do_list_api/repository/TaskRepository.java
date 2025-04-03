package com.example.to_do_list_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.to_do_list_api.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
