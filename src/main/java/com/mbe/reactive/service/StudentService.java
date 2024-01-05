package com.mbe.reactive.service;

import com.mbe.reactive.entities.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StudentService {


    // save all students to the database
    Flux<Student> saveAllStudents(List<Student> students);

    Mono<Student> createStudent(Student student);

    Mono<Student> findById(Long id);

    String deleteStudentById(Long id);

    Mono<Student> saveSingleStudent(Student student);

    Flux<Student> findAllStudents();

    Flux<Student> findStudentsByFirstnameIgnoreCase(String firstName);

    void deleteAll();

    Mono<Student> updateStudent(Student student, Long studentId);

}
