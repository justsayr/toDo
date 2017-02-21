package com.toDo.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toDo.domain.Task;
import com.toDo.domain.Tasks;
import com.toDo.repository.IToDoRepository;

@Service
public class ToDoService {
	
	@Autowired
	private IToDoRepository repo;

	public void saveTask(Task task) {
		repo.save(task);
	}

	public List<Task> getTasks() {
		
		List<Task> tasks= (List<Task>) repo.findAll();
		return tasks;
	}

	/*public void writeToFile() throws IOException{
		List<Task> listOfTask= getTasks();
		
		FileOutputStream file = null;
		ObjectOutputStream out = null;
		
		try{
			file = new FileOutputStream("D:\\ram.txt");
			out = new ObjectOutputStream(file);
			for(Task task : listOfTask){
				out.writeObject(task);
			}
			
		}catch(IOException  e ){
			
		} finally{
			file.close();
		}
	}*/

	public Task getTask() {
		List<Task> tasks= (List<Task>) repo.findAll();
		for(Task task : tasks){
			return task ;
		}
		return null;
	}

	public void deleteTask(String id) {
		repo.delete(id);
		
	}

	public Task getTAskById(String id) {
		
		return repo.findById(id);
	}
	
}
