package ps.training.Leela_CPS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps.training.Leela_CPS.Model.Course;
import ps.training.Leela_CPS.Model.Student;
import ps.training.Leela_CPS.Repository.CourseRepository;
import ps.training.Leela_CPS.Repository.StudentRepository;
import ps.training.Leela_CPS.Service.StudentService.StudentService;

import java.util.*;

@RestController
@RequestMapping("/api/s1")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/student")
    public ResponseEntity<String> addCourse(@RequestBody Student student){
        Optional<Student> existingStudent = studentRepository.findByUserName(student.getUserName());
        if (existingStudent.isPresent()) {
            return ResponseEntity.badRequest().body("Student with name '" + student.getUserName() + "' already exists.");
        }

        studentService.addStudent(student);
        return new ResponseEntity<>("Student Added Successfully", HttpStatus.CREATED);
    }

//    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudent();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") long id) {
        boolean deleted = studentService.deleteStudentById(id);
        if (deleted) {
            return ResponseEntity.ok("Student with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //    Using RequestBody and using Post Method
    @PostMapping("/student/{studentId}")
    public Student getstudent(@PathVariable long studentId){
        Optional<Student> student =  studentRepository.findById(studentId);
        if(student.isPresent()){
            return student.get();
        }
        else {
            return null;
        }
    }

    @GetMapping("/student/{userName}")
    public long getStudentByName(@PathVariable String userName){
        Optional<Student> stud = studentRepository.findByUserName(String.valueOf(userName));
        return stud.get().getUserId();
    }

}