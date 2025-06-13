package com.jsp.boot_crud.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.jsp.boot_crud.entity.Student;
import com.jsp.boot_crud.repository.StudentRepository;

@Service
public class CrudService {
	
	@Autowired
	StudentRepository repository;
	
	public String add(Student student,Model model)
	{
		repository.save(student);
		model.addAttribute("message", "Record Added Sucessfuly");
		return "home.html";
	}

	public String fetch(Model model,String search) {
		
		HashSet<Student> students = new HashSet<Student>();
		try {
			long num = Long.parseLong(search);
			students.addAll(repository.findByMobile(num));
		} catch(NumberFormatException e) {
			students.addAll(repository.findByNameLike("%" + search + "%"));
			students.addAll(repository.findByEmailLike("%" + search + "%"));
		}
		
		
		if(students.isEmpty())
		{
			model.addAttribute("message", "No Records Found");
			return "home.html";
		} else {
			model.addAttribute("students", students);
			return "fetch.html";
		}
	}

	public String delete(int id, Model model) {
		repository.deleteById(id);
		model.addAttribute("message", "Deleted Successfuly");
		return "home.html";
	}

	public String edit(int id, Model model) {
		Student student = repository.findById(id).orElseThrow();
		model.addAttribute("student", student);
		return "edit.html";
	}

	public String updateProduct(Student student, Model model) {
		repository.save(student);
		model.addAttribute("message", "Record Updated Successfuly");
		return "home.html";
	}
}
