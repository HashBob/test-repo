package ps.training.Leela_CPS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ps.training.Leela_CPS.Model.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByCourseName(String courseName);
}
