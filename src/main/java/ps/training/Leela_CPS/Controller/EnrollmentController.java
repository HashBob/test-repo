package ps.training.Leela_CPS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps.training.Leela_CPS.Model.Course;
import ps.training.Leela_CPS.Model.Enroll;
import ps.training.Leela_CPS.Model.Student;
import ps.training.Leela_CPS.Repository.CourseRepository;
import ps.training.Leela_CPS.Repository.EnrollRepository;
import ps.training.Leela_CPS.Repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/e1")
@CrossOrigin(origins = "http://localhost:3000")
public class EnrollmentController {

    @Autowired
    EnrollRepository enrollRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;




    @PostMapping("/student/{studentId}/course/{courseId}")
    public String enrollStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student == null || course == null) {
            return "Student or course not found.";
        }

        int enrollCount = enrollRepository.countByStudentAndCourse(student, course);
        if (enrollCount > 0) {
            return "Student is already enrolled in this course.";
        }

        Enroll enroll = new Enroll();
        enroll.setStudent(student);
        enroll.setCourse(course);
        enrollRepository.save(enroll);

        return "Student enrolled to the course successfully.";
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Boolean> fetchEnrollmentStatus(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            System.out.println("Fetching enrollment status for studentId: " + studentId + ", courseId: " + courseId);

            Student student = studentRepository.findById(studentId).orElse(null);
            Course course = courseRepository.findById(courseId).orElse(null);

            if (student == null || course == null) {
                System.out.println("Student or course not found.");
                return ResponseEntity.notFound().build();
            }

            boolean isEnrolled = enrollRepository.existsByStudentAndCourse(student, course);
            System.out.println("Enrollment status for studentId " + studentId + " and courseId " + courseId + ": " + isEnrolled);

            return ResponseEntity.ok(isEnrolled);
        } catch (Exception e) {
            System.err.println("Error fetching enrollment status: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public String unenrollStudentFromCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student == null || course == null) {
            return "Student or course not found.";
        }

        Enroll enroll = enrollRepository.findByStudentAndCourse(student, course);
        if (enroll == null) {
            return "Student is not enrolled in this course.";
        }

        enrollRepository.delete(enroll);
        return "Student unenrolled from the course successfully.";
    }

    @GetMapping("/student/{studentId}/enrolledCourses")
    public ResponseEntity<?> getEnrolledCourses(@PathVariable Long studentId) {
        try {
            System.out.println("Fetching enrolled courses for studentId: " + studentId);

            // Retrieve the enrollments for the student
            List<Enroll> enrollments = enrollRepository.findByStudentId(studentId);
            List<Course> enrolledCourses = new ArrayList<>();

            for (Enroll enroll : enrollments) {
                enrolledCourses.add(enroll.getCourse());
            }

            System.out.println("Enrolled courses for studentId " + studentId + ": " + enrolledCourses);

            return ResponseEntity.ok(enrolledCourses);
        } catch (Exception e) {
            System.err.println("Error fetching enrolled courses: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
