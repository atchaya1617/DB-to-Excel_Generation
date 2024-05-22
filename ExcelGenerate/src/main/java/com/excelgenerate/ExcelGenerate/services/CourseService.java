package com.excelgenerate.ExcelGenerate.services;

import com.excelgenerate.ExcelGenerate.entities.Course;
import com.excelgenerate.ExcelGenerate.repositories.CourseRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public void generateExcel(OutputStream outputStream) throws IOException {
        List<Course> courses = courseRepository.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Courses Info");
        HSSFRow headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Price");

        int dataRowIndex = 1;
        for (Course course : courses) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(course.getCid());
            dataRow.createCell(1).setCellValue(course.getCname());
            dataRow.createCell(2).setCellValue(course.getPrice());
            dataRowIndex++;
        }

        workbook.write(outputStream);
//        workbook.close();
    }


    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course updateCourse(String id, Course course) {
        course.setCid(id);
        return courseRepository.save(course);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id).orElseGet(null);
    }

    public boolean deleteCourse(String id) {
        try {
            courseRepository.deleteById(id);
            return true;
        } catch (Exception error) {
            return false;
        }
    }
}
