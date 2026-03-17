package com.example.Kiemtra.Repository;

import com.example.Kiemtra.Entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Lấy danh sách các môn mà một sinh viên đã đăng ký (Giải quyết Câu 7: My Courses)
    List<Enrollment> findByStudent_StudentId(Long studentId);
}
