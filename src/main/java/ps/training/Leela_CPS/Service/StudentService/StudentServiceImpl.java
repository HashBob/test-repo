package ps.training.Leela_CPS.Service.StudentService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ps.training.Leela_CPS.Model.Student;
import ps.training.Leela_CPS.Repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }
    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
    @Override
    public Student getStudentById(long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(null);
    }
    @Override
    public boolean deleteStudentById(long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
