package com.toDo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.toDo.domain.Task;

@Repository
public interface IToDoRepository extends CrudRepository<Task, String> {

	Task findById(String id);

}
