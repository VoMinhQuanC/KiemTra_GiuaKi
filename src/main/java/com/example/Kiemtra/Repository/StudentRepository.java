package com.example.Kiemtra.Repository;

import com.example.Kiemtra.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Dùng để tìm user khi đăng nhập (Câu 5)
    Student findByUsername(String username);

    // Kiểm tra trùng lặp khi đăng ký tài khoản (Câu 3)
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
