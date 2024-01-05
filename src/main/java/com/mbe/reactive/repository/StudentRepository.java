package com.mbe.reactive.repository;

import com.mbe.reactive.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    Flux<Student> findStudentsByFirstNameIgnoreCase(String firstName);
}
