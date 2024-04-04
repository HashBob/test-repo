package ps.training.Leela_CPS.Service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ps.training.Leela_CPS.Model.Admin;
import ps.training.Leela_CPS.Model.Course;
import ps.training.Leela_CPS.Repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void addCourse(Course course) {
        courseRepository.save(course);
    }
    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }
    @Override
    public Course getCourseById(long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        return optionalCourse.orElse(null);
    }
    @Override
    public boolean deleteCourseById(long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
