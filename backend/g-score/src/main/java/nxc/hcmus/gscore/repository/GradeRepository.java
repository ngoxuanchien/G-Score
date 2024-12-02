package nxc.hcmus.gscore.repository;

import nxc.hcmus.gscore.model.entity.Grade;
import nxc.hcmus.gscore.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findGradesByStudentIs(Student student);

    @Query(
            value = "SELECT sub.name AS subject, " +
                    "CASE " +
                    "WHEN g.score >= 8 THEN 'A' " +
                    "WHEN g.score >= 6 THEN 'B' " +
                    "WHEN g.score >= 4 THEN 'C' " +
                    "ELSE 'D' " +
                    "END AS level, " +
                    "COUNT(*) AS count " +
                    "FROM subjects sub " +
                    "JOIN grades g ON sub.id = g.subject_id " +
                    "JOIN students s ON g.student_id = s.id " +
                    "GROUP BY sub.name, level " +
                    "ORDER BY sub.name, level, count DESC",
            nativeQuery = true
    )
    List<Map<String, Object>> getStatistics();

    @Query(
            value = "SELECT s.id AS id, " +
                    "s.sbd AS sbd, " +
                    "SUM(g.score) AS total_score " +
                    "FROM students s " +
                    "JOIN grades g ON s.id = g.student_id " +
                    "JOIN subjects sub ON g.subject_id = sub.id " +
                    "WHERE sub.name IN ('toan', 'vat_li', 'hoa_hoc') " +
                    "GROUP BY s.id, s.sbd " +
                    "ORDER BY total_score DESC " +
                    "LIMIT 10",
            nativeQuery = true
    )
    List<Map<String, Object>> getTop10Students();

}
