package com.example.Kiemtra.Controller;

import com.example.Kiemtra.Entity.Course;
import com.example.Kiemtra.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private CourseService courseService;

    // Mapping đường dẫn gốc "/" và "/home"
    @GetMapping({"/", "/home"})
    public String home(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5; // Đề bài yêu cầu mỗi trang có 5 học phần

        // Gọi Service lấy dữ liệu có phân trang
        Page<Course> coursePage = courseService.getAllCourses(PageRequest.of(page, pageSize));

        // Đẩy dữ liệu sang giao diện (View)
        model.addAttribute("courses", coursePage.getContent()); // Danh sách 5 khóa học hiện tại
        model.addAttribute("currentPage", page);                // Trang hiện tại
        model.addAttribute("totalPages", coursePage.getTotalPages()); // Tổng số trang

        return "home"; // Trả về file home.html trong thư mục templates
    }
}