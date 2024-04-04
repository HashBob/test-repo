package ps.training.Leela_CPS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ps.training.Leela_CPS.Model.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByUserName(String userName);
}
