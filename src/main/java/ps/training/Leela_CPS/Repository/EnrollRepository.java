package ps.training.Leela_CPS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ps.training.Leela_CPS.Model.Course;
import ps.training.Leela_CPS.Model.Enroll;
import ps.training.Leela_CPS.Model.Student;

import java.util.List;

public interface EnrollRepository extends JpaRepository<Enroll, Long> {
    Enroll findByStudentAndCourse(Student student, Course course);

    @Query("SELECT COUNT(e) FROM Enroll e WHERE e.student = ?1 AND e.course = ?2")
    int countByStudentAndCourse(Student student, Course course);

    boolean existsByStudentAndCourse(Student student, Course course);

    @Query("SELECT e FROM Enroll e WHERE e.student.id = ?1")
    List<Enroll> findByStudentId(Long studentId);

    @Query("SELECT e FROM Enroll e WHERE e.course.id = :courseId")
    List<Enroll> findByCourseId(Long courseId);

}
