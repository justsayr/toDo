package com.toDo.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document
@Data
public class Task implements Serializable {

	@Id
	String id;
	String task; 
	Date dueDate; 
	Date createdDate;
	String status;
	boolean edit;
}
