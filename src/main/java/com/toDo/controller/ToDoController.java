package com.toDo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.toDo.domain.Task;
import com.toDo.service.ToDoService;

@RestController
public class ToDoController {

	@Autowired
	private ToDoService service;
	
	@RequestMapping(value="/saveTask", method = RequestMethod.POST)
	public void saveTask(@RequestBody Task task){
		service.saveTask(task);
		
	}
	
	@RequestMapping(value="/getTask/{id}", method= RequestMethod.GET )
	public Task getTaskById(@PathVariable("id") String id){
		Task task = service.getTAskById(id);
		return task;
	}

	/*@RequestMapping(value="/saveTask", method = RequestMethod.POST)
	public String saveTaskModel( Model model){
		Task task;
	//	model.addAttribute("aliasList", task );
		return "index";
	}*/
	
	@RequestMapping(value="/getTasks", method = RequestMethod.GET)
	public List<Task> getTasks () throws IOException{
		List<Task> tasks = service.getTasks();
		//service.writeToFile();
		return tasks;
	}
	
	/*@RequestMapping(value="/getTask", method = RequestMethod.GET)
	public Task getTask () throws IOException{
		Task tasks = service.getTask();
		
		return tasks;
	}*/
	
	@RequestMapping(value="/deleteTask", method = RequestMethod.DELETE)
	//@ResponseStatus(HttpStatus.OK)
	public void deleteTask(@RequestParam String id){
		service.deleteTask(id);
		
	}
	
}
