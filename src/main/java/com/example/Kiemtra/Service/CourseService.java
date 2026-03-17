package com.example.Kiemtra.Service;

import com.example.Kiemtra.Entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    // Dành cho Câu 1: Hiển thị và phân trang
    Page<Course> getAllCourses(Pageable pageable);

    // Dành cho Câu 8: Tìm kiếm và phân trang
    Page<Course> searchCoursesByName(String name, Pageable pageable);

    // Chuẩn bị sẵn cho Câu 2: Chức năng CRUD của Admin
    Course saveCourse(Course course);
    Course getCourseById(Long id);
    void deleteCourse(Long id);
}