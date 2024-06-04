package com.fatec.student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.student.dto.StudentRequest;
import com.fatec.student.dto.StudentResponse;
import com.fatec.student.entities.Student;
import com.fatec.student.mappers.StudentMapper;
import com.fatec.student.repositories.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentResponse> getStudents(){
        List<Student> students = studentRepository.findAll();
        return students.stream().map( s -> StudentMapper.toDTO(s)).collect(Collectors.toList());
    }

    public StudentResponse getStudentById(int id){
        return StudentMapper.toDTO( studentRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Aluno nao cadastrado")
        ));
    }

    public void deleteStudentById(int id){
        if (this.studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Aluno nao cadastrado");
        }
    }

    public StudentResponse saveStudent(StudentRequest request){
        Student student = StudentMapper.toEntity((request));
        return StudentMapper.toDTO(studentRepository.save(student));
    }

    public void updateStudent(int id, StudentRequest student){
        try {
            Student aux = studentRepository.getReferenceById(id);
            aux.setCourse(student.course());
            aux.setName(student.name());
            this.studentRepository.save(aux);

        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Aluno nao cadastrado");
        }
    }

}
