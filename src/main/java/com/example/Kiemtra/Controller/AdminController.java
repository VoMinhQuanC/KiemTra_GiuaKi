package com.example.Kiemtra.Controller;

import com.example.Kiemtra.Entity.Course;
import com.example.Kiemtra.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/courses") // Gom chung đường dẫn cho Admin
public class AdminController {

    @Autowired
    private CourseService courseService;

    // 1. Hiển thị danh sách khóa học dưới dạng bảng
    @GetMapping
    public String listCourses(Model model, @RequestParam(defaultValue = "0") int page) {
        // Tái sử dụng hàm phân trang, cho hiển thị 10 môn/trang ở trang quản trị
        Page<Course> coursePage = courseService.getAllCourses(PageRequest.of(page, 10));

        model.addAttribute("courses", coursePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", coursePage.getTotalPages());

        return "admin/course-list"; // Trả về giao diện bảng danh sách
    }

    // 2. Hiển thị Form Thêm mới (Create)
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course()); // Tạo một đối tượng rỗng gửi sang Form
        return "admin/course-form";
    }

    // 3. Xử lý Lưu dữ liệu (Dùng chung cho cả Create và Update)
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course); // Nếu có ID thì nó tự hiểu là Update, không có ID là Create
        return "redirect:/admin/courses"; // Lưu xong chuyển hướng về trang danh sách
    }

    // 4. Hiển thị Form Cập nhật (Update)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id); // Lấy môn học cũ từ DB lên
        if (course != null) {
            model.addAttribute("course", course); // Gửi dữ liệu cũ sang Form để điền sẵn
            return "admin/course-form";
        }
        return "redirect:/admin/courses";
    }

    // 5. Xử lý Xóa (Delete)
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/admin/courses"; // Xóa xong load lại trang danh sách
    }
}