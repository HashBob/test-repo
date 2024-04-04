package ps.training.Leela_CPS.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String userName;
    private String passWord;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Enroll> enrolls = new HashSet<>();

}
