package com.fatec.student.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.student.StudentService;
import com.fatec.student.entities.Student;

@RestController
@RequestMapping("students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudent(){
        return studentService.getStudents();
    }

    @GetMapping("{id}")
    public Student getStudentByStudent(@PathVariable int id){
        return studentService.getStudentById(id);
    }

    @DeleteMapping("{id}")
    public void deleteStudentById(@PathVariable int id){
        this.studentService.deleteStudentById(id);
    }

    @PostMapping
    public void saveStudent(@RequestBody Student student){
        this.studentService.saveStudent(student);
    }

    @PutMapping("{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student){
        this.studentService.updateStudent(id, student);
    }

}