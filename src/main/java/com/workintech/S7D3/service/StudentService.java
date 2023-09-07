package com.workintech.S7D3.service;

import com.workintech.S7D3.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();
    Student findById(int id);
    Student save(Student student);
    Student delete(String tckn);
}
