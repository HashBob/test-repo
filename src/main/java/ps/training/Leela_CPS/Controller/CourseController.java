package ps.training.Leela_CPS.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps.training.Leela_CPS.Model.Course;
import ps.training.Leela_CPS.Model.Enroll;
import ps.training.Leela_CPS.Repository.CourseRepository;
import ps.training.Leela_CPS.Repository.EnrollRepository;
import ps.training.Leela_CPS.Service.CourseService.CourseService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/c1")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseService  courseService;
    @Autowired
    EnrollRepository enrollRepository;

    @PostMapping("/course")
    public ResponseEntity<String> addCourse(@RequestBody Course course){
        Optional<Course> existingCourse = courseRepository.findByCourseName(course.getCourseName());
        if (existingCourse.isPresent()) {
            return ResponseEntity.badRequest().body("Course with name '" + course.getCourseName() + "' already exists.");
        }

        courseService.addCourse(course);
        return new ResponseEntity<>("Course Added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourse();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCourseById/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable("id") long id) {
        List<Enroll> enrollments = enrollRepository.findByCourseId(id);
        if (!enrollments.isEmpty()) {
            enrollRepository.deleteAll(enrollments);
        }

        boolean deleted = courseService.deleteCourseById(id);
        if (deleted) {
            return ResponseEntity.ok("Course with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateCourseById/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable("id") long id, @RequestBody Course updatedCourse) {

        Course existingCourse = courseRepository.findById(id).orElse(null);


        if (existingCourse == null) {
            return ResponseEntity.notFound().build();
        }

        existingCourse.setCourseName(updatedCourse.getCourseName());
        existingCourse.setCourseDesc(updatedCourse.getCourseDesc());
        existingCourse.setSyllabus(updatedCourse.getSyllabus());
        existingCourse.setTechnicalRequirements(updatedCourse.getTechnicalRequirements());

        courseRepository.save(existingCourse);

        return ResponseEntity.ok("Course with ID " + id + " updated successfully.");
    }

    @GetMapping("/course/{courseId}")
    public Course getCourse(@PathVariable long courseId){
        Optional<Course> course =  courseRepository.findById(courseId);
        if(course.isPresent()){
            return course.get();
        }
        else {
            return null;
        }
    }
}
