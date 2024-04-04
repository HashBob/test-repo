package ps.training.Leela_CPS.Repository;

import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import ps.training.Leela_CPS.Model.Course;
import ps.training.Leela_CPS.Model.Feedback;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
    List<Feedback> findFeedbackByCourse(Course course);

}
