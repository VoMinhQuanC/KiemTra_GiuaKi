package com.example.Kiemtra.Repository;

import com.example.Kiemtra.Entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Tìm kiếm môn học theo tên và phân trang (Giải quyết Câu 1 & Câu 8)
    Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
