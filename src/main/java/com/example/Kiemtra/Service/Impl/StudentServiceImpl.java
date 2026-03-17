package com.example.Kiemtra.Service.Impl;

import com.example.Kiemtra.Dto.UserDto;
import com.example.Kiemtra.Entity.Role;
import com.example.Kiemtra.Entity.Student;
import com.example.Kiemtra.Repository.RoleRepository;
import com.example.Kiemtra.Repository.StudentRepository;
import com.example.Kiemtra.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void registerStudent(UserDto userDto) {
        Student student = new Student();
        student.setUsername(userDto.getUsername());
        student.setEmail(userDto.getEmail());

        // Mã hóa mật khẩu theo chuẩn Security
        student.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Gán quyền mặc định là STUDENT
        // Đảm bảo RoleRepository.findByName trả về kiểu Role (không phải Optional)
        Role role = roleRepository.findByName("STUDENT");

        if (role == null) {
            role = new Role();
            role.setName("STUDENT");
            roleRepository.save(role);
        }

        student.setRoles(Collections.singleton(role));
        studentRepository.save(student);
    }

    @Override
    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }
}