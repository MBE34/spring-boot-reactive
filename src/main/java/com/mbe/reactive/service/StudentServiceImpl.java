package com.mbe.reactive.service;

import com.mbe.reactive.entities.Student;
import com.mbe.reactive.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Flux<Student> saveAllStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    @Override
    public Mono<Student> createStudent(Student student) {

        return studentRepository.save(student);

    }

    @Override
    public Mono<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public String deleteStudentById(Long id) {
        Mono<Void> deletedStudent = studentRepository.deleteById(id);

        return "Student with ID: " + id + " deleted";

    }

    @Override
    public Mono<Student> saveSingleStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Flux<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Flux<Student> findStudentsByFirstnameIgnoreCase(String firstName){
        return studentRepository.findStudentsByFirstNameIgnoreCase(firstName);
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Override
    public Mono<Student> updateStudent(Student student, Long studentId) {
        return studentRepository.findById(studentId)
                .flatMap(existingStudent -> {
                    //update student new values here
                    existingStudent.setFirstName(student.getFirstName());
                    existingStudent.setLastName(student.getLastName());
                    existingStudent.setAge(student.getAge());

                    // now we save the existing student with the new values
                    return studentRepository.save(existingStudent);
                });
    }
}
