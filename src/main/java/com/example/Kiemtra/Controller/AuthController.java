package com.example.Kiemtra.Controller;

import com.example.Kiemtra.Dto.UserDto;
import com.example.Kiemtra.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private StudentService studentService;

    // Hiển thị form đăng ký
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Xử lý lưu thông tin sinh viên
    @PostMapping("/register/save")
    public String registerStudent(@ModelAttribute("user") UserDto userDto) {
        studentService.registerStudent(userDto);
        // Đăng ký xong chuyển về trang đăng nhập với thông báo thành công
        return "redirect:/login?success";
    }
}