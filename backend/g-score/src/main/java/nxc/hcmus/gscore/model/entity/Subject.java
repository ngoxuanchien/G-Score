package nxc.hcmus.gscore.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "subjects_seq")
    @SequenceGenerator(name = "subjects_seq", sequenceName = "subjects_seq", allocationSize = 1)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Grade> grades;
}
