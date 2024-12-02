package nxc.hcmus.gscore.repository;

import nxc.hcmus.gscore.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findBySbd(String sbd);
}
