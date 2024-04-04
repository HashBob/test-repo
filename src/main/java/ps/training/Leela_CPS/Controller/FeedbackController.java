package ps.training.Leela_CPS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps.training.Leela_CPS.Model.Course;
import ps.training.Leela_CPS.Model.Feedback;
import ps.training.Leela_CPS.Model.Student;
import ps.training.Leela_CPS.Repository.CourseRepository;
import ps.training.Leela_CPS.Repository.FeedbackRepository;
import ps.training.Leela_CPS.Repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/f1")
@CrossOrigin(origins = "*")
public class FeedbackController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/getFeedbacksByCourseId/course/{courseId}")
    public ResponseEntity<?> getAllFeedbacksByCourseId(@PathVariable Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);

        if (course == null) {
            return ResponseEntity.badRequest().body("Course not found.");
        }

        List<Feedback> feedbacks = feedbackRepository.findFeedbackByCourse(course);

        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping("/addFeedbackByStudent/student/{studentId}/course/{courseId}")
    public String addFeedbackByStudent(@PathVariable Long studentId, @PathVariable Long courseId, @RequestBody Feedback feedback1) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student == null || course == null) {
            return "Student or course not found.";
        }

        Feedback feedback = new Feedback();
        feedback.setStudent(student);
        feedback.setCourse(course);
        feedback.setFeedMsg(feedback1.getFeedMsg());
        feedbackRepository.save(feedback);

        return "Feedback added Successfully";
    }
}
