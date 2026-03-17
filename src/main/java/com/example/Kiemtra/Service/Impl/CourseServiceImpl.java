package com.example.Kiemtra.Service.Impl;

import com.example.Kiemtra.Entity.Course;
import com.example.Kiemtra.Repository.CourseRepository;
import com.example.Kiemtra.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Page<Course> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public Page<Course> searchCoursesByName(String name, Pageable pageable) {
        // Hàm này Spring Data JPA đã tự viết sẵn cho chúng ta bên Repository
        return courseRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}