package com.example.Kiemtra.Repository;

import com.example.Kiemtra.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Dùng để tìm quyền (STUDENT/ADMIN) gán cho user lúc đăng ký (Câu 3)
    Role findByName(String name);
}
