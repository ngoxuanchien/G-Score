package nxc.hcmus.gscore.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "students_seq")
    @SequenceGenerator(name = "students_seq", sequenceName = "students_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String sbd;

    @Column(name = "ma_ngoai_ngu")
    private String maNgoaiNgu;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Grade> grades;
}
