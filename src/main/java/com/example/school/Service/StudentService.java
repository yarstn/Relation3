package com.example.school.Service;

import com.example.school.Api.ApiException;
import com.example.school.Model.Course;
import com.example.school.Model.Student;
import com.example.school.Repository.CourseRepo;
import com.example.school.Repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    public List<Student> getAllStudents() {
       return studentRepo.findAll();
    }
    public void addStudent(Student student) {
        studentRepo.save(student);
    }

    public void updateStudent(Integer id,Student student) {
        Student stud = studentRepo.findStudentById(id);
        if (stud == null) {
            throw new ApiException("student not found");
        }
        stud.setName(student.getName());
        stud.setAge(student.getAge());
        stud.setMajor(student.getMajor());
        studentRepo.save(stud);
    }
    public void deleteStudent(Integer id) {
        Student stud = studentRepo.findStudentById(id);
        if (stud == null) {
            throw new ApiException("student not found");
        }
        studentRepo.delete(stud);
    }

    public void assignById(Integer courseId,Integer studentId) {
        Course course = courseRepo.findCourseById(courseId);
        Student stud = studentRepo.findStudentById(studentId);
        if (stud == null || course == null) {
            throw new ApiException("student or course not found");
        }
        stud.getCourses().add(course);
        course.getStudents().add(stud);
        studentRepo.save(stud);
        courseRepo.save(course);
    }

    public Student changeStudentMajor(Integer id, String major) {
        Student student = studentRepo.findStudentById(id);
        if (student == null) {
            throw new ApiException("Student not found");
        }
        for (Course course : student.getCourses()) {
            course.getStudents().remove(student); // Remove the student from the course's student set
        }
        student.getCourses().clear();
        student.setMajor(major);
       return studentRepo.save(student);
    }


    public List<Student> getStudentsByCourseId(Integer courseId) {
        Course course = courseRepo.findCourseById(courseId);
        if (course == null) {
            throw new ApiException("Course not found");
        }
        return new ArrayList<>(course.getStudents());
    }

}
