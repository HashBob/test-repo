package ps.training.Leela_CPS.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps.training.Leela_CPS.Model.Admin;
import ps.training.Leela_CPS.Model.Enroll;
import ps.training.Leela_CPS.Model.Student;
import ps.training.Leela_CPS.Repository.AdminRepository;
import ps.training.Leela_CPS.Repository.EnrollRepository;
import ps.training.Leela_CPS.Service.AdminService.AdminService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/a1")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    EnrollRepository enrollRepository;

    @PostMapping("/admin")
    public ResponseEntity<String> addStudent(@RequestBody Admin admin){
        adminService.addAdmin(admin);
        return new ResponseEntity<>("Admin Added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<Admin>> getAllStudents() {
        List<Admin> admins = adminService.getAllAdmin();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    //    Using RequestBody and using Post Method
    @PostMapping("/admin/{adminId}")
    public Admin getAdmin(@PathVariable long adminId){
        Optional<Admin> admin =  adminRepository.findById(adminId);
        if(admin.isPresent()){
            return admin.get();
        }
        else {
            return null;
        }
    }

    @GetMapping("/getEnrolledStudents/{courseId}")
    public ResponseEntity<?> getEnrolledStudents(@PathVariable Long courseId) {
        try {
            System.out.println("Getting enrolled students for Course Id: " + courseId);

            List<Enroll> enrollments = enrollRepository.findByCourseId(courseId);
            if (enrollments.isEmpty()) {
                System.out.println("No students are Enrolled in this Course.");
                return ResponseEntity.ok(new ArrayList<Student>());
            }
            List<Student> enrolledStudents = new ArrayList<>();
            for (Enroll enroll : enrollments) {
                enrolledStudents.add(enroll.getStudent());
            }

            System.out.println("Enrolled Students for CourseId " + courseId + ": " + enrolledStudents);

            return ResponseEntity.ok(enrolledStudents);
        } catch (Exception e) {
            System.err.println("Error fetching enrolled students: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching enrolled students");
        }
    }
}
