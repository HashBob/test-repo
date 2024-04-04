package ps.training.Leela_CPS.Service.StudentService;

import ps.training.Leela_CPS.Model.Student;

import java.util.List;

public interface StudentService {
    public void addStudent(Student student);
    public List<Student> getAllStudent();
    public Student getStudentById(long id);
    public boolean deleteStudentById(long id);
}
