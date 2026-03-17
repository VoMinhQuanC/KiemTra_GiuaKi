package com.example.Kiemtra.Service;

import com.example.Kiemtra.Dto.UserDto;
import com.example.Kiemtra.Entity.Student;

public interface StudentService {
    // Hàm xử lý đăng ký tài khoản cho sinh viên (Lưu vào bảng student)
    void registerStudent(UserDto userDto);

    // Hàm tìm kiếm sinh viên theo username (Sẽ dùng rất nhiều ở Câu 5 - Đăng nhập)
    Student findByUsername(String username);
}