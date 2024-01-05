package com.mbe.reactive.controller;

import com.mbe.reactive.entities.Student;
import com.mbe.reactive.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public Mono<Student> saveStudent(@RequestBody Student student){
        return studentService.saveSingleStudent(student);
    }

    @GetMapping("/all")
    public Flux<Student> fetchAll(){
        return studentService.findAllStudents();
    }
}
