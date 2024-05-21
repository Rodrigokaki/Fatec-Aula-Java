package com.fatec.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.student.entities.Student;
import com.fatec.student.repositories.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int id){
        return studentRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Aluno nao cadastrado")
        );
    }

    public void deleteStudentById(int id){
        if (this.studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Aluno nao cadastrado");
        }
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public void updateStudent(int id, Student student){
        try {
            Student aux = studentRepository.getReferenceById(id);
            aux.setCourse(student.getCourse());
            aux.setName(student.getName());
            this.studentRepository.save(aux);

        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Aluno nao cadastrado");
        }
    }

}
