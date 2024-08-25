package com.example.school.Controller;

import com.example.school.Model.Student;
import com.example.school.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/stud")
@RestController //json
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(200).body("student added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable  Integer id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body("student updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable  Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body("student deleted successfully");
    }

    @PutMapping("/{courseId}/{studentId}")
    public ResponseEntity assignById(@PathVariable  Integer courseId,@PathVariable Integer studentId) {
        studentService.assignById(courseId, studentId);
        return ResponseEntity.status(200).body("student assigned successfully");
    }

    @PutMapping("/update/{id}/{major}")
    public ResponseEntity changeStudentMajor(@PathVariable Integer id, @PathVariable String major) {
        studentService.changeStudentMajor(id, major);
        return ResponseEntity.ok("Student major changed and courses dropped");
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity getStudentsByCourseId(@PathVariable Integer courseId) {
        List<Student> students = studentService.getStudentsByCourseId(courseId);
        return ResponseEntity.ok(students);
    }

}
