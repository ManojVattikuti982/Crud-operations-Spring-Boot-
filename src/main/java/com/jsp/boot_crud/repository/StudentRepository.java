package com.jsp.boot_crud.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.boot_crud.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByNameLike(String search);

	Collection<? extends Student> findByMobile(long num);

	Collection<? extends Student> findByEmailLike(String string);

	

}
