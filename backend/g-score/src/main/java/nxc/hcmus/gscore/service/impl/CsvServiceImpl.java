package nxc.hcmus.gscore.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import nxc.hcmus.gscore.model.entity.Grade;
import nxc.hcmus.gscore.model.entity.Student;
import nxc.hcmus.gscore.model.entity.Subject;
import nxc.hcmus.gscore.repository.GradeRepository;
import nxc.hcmus.gscore.repository.StudentRepository;
import nxc.hcmus.gscore.repository.SubjectRepository;
import nxc.hcmus.gscore.service.CsvService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CsvServiceImpl implements CsvService {

    private final StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private final SubjectRepository subjectRepository;

    private final GradeRepository gradeRepository;

    private static final int batchSize = 4096;

    private final Map<Integer, Subject> subjectMap = new HashMap<>();
    private final Map<String, List<Grade>> gradeMap = new HashMap<>();
    private final List<Student> batchStudents = new ArrayList<>();
    private final List<Grade> batchGrades = new ArrayList<>();

    private void loadSubjectMap(String[] subjects) {
        for (int i = 1; i < subjects.length - 1; i++) {
            String subjectName = subjects[i];
            var subject = subjectRepository.findByName(subjectName)
                    .orElseGet(() -> {
                        var newSubject = Subject.builder()
                                .name(subjectName)
                                .build();
                        return subjectRepository.save(newSubject);
                    });
            subjectMap.put(i, subject);
        }
    }

    private List<Grade> loadGradeList(String[] grades, int count) {
        List<Grade> gradeList = new ArrayList<>();
        for (int i = 1; i < Math.min(grades.length, count - 1); i++) {
            if (grades[i].isEmpty()) {
                continue;
            }

            var grade = Grade.builder()
                    .score(Double.parseDouble(grades[i]))
                    .subject(subjectMap.get(i))
                    .build();
            gradeList.add(grade);
        }

        return gradeList;
    }

    @Override
    public void importDataFromCsv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int columnCount = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values[0].equalsIgnoreCase("sbd")) {
                    columnCount = values.length;
                    loadSubjectMap(values);
                } else {
                    var student = Student.builder()
                            .sbd(values[0])
                            .build();

                    if (values.length == columnCount) {
                        student.setMaNgoaiNgu(values[columnCount - 1]);
                    }

                    List<Grade> grades = loadGradeList(values, columnCount);
                    gradeMap.put(student.getSbd(), grades);

                    batchStudents.add(student);
                }

                if (batchStudents.size() >= batchSize) {
                    saveBatch();
                }

            }

            saveBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveBatch() {
        var students = studentRepository.saveAll(batchStudents);
        students.forEach(student -> {
            var grades = gradeMap.get(student.getSbd());
            grades.forEach(grade -> grade.setStudent(student));
            batchGrades.addAll(grades);
        });
        gradeRepository.saveAll(batchGrades);

        // clear cache
        gradeMap.clear();
        batchStudents.clear();
        batchGrades.clear();
    }
}
