package com.example.school.Repository;

import com.example.school.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
  Student findStudentById(Integer id);
}
