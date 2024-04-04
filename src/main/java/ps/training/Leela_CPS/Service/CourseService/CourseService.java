package ps.training.Leela_CPS.Service.CourseService;
import ps.training.Leela_CPS.Model.Course;
import ps.training.Leela_CPS.Model.Student;

import java.util.List;

public interface CourseService {

    public void addCourse(Course course);
    public List<Course> getAllCourse();
    public Course getCourseById(long id);
    public boolean deleteCourseById(long id);

}
