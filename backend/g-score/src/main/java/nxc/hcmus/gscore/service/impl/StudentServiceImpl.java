package nxc.hcmus.gscore.service.impl;

import lombok.RequiredArgsConstructor;
import nxc.hcmus.gscore.mapper.dto.StudentDto;
import nxc.hcmus.gscore.mapper.dto.SubjectDto;
import nxc.hcmus.gscore.mapper.mapper.StudentMapper;
import nxc.hcmus.gscore.model.entity.Subject;
import nxc.hcmus.gscore.repository.StudentRepository;
import nxc.hcmus.gscore.service.StudentService;
import nxc.hcmus.gscore.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final SubjectService subjectService;

    @Override
    public StudentDto getStudentBySbd(String sbd) {
        Optional.ofNullable(sbd)
                .orElseThrow(() -> new RuntimeException("Sbd is required"));

        return studentMapper.studentToStudentDto(
                studentRepository.findBySbd(sbd)
                        .orElseThrow(() -> new RuntimeException("Student not found")));

    }

    @Override
    public void importStudent(MultipartFile file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        String[] data = br.readLine().split(",");
        String[] subjects = Arrays.copyOfRange(data, 5, data.length);
    }

    private Subject[] getSubject(String[] subjects) {
        Subject[] subjectArray = new Subject[subjects.length];
        int n = subjects.length;

        for (int i = 1; i < n - 1; i++) {
            var subject = subjectService.getSubjectByName(subjects[i]);
            if (subject.isEmpty()) {
                subjectArray[i] = subjectService.create(Subject.builder().name(subjects[i]).build());
            } else {
                subjectArray[i] = subject.get();
            }
        }

        return subjectArray;
    }
}
