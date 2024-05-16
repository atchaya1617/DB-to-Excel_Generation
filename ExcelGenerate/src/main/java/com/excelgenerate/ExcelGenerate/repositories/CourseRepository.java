package com.excelgenerate.ExcelGenerate.repositories;

import com.excelgenerate.ExcelGenerate.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
