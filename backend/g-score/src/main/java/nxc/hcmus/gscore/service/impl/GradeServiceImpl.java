package nxc.hcmus.gscore.service.impl;

import lombok.RequiredArgsConstructor;
import nxc.hcmus.gscore.exception.StudentNotFoundException;
import nxc.hcmus.gscore.mapper.dto.GradeDto;
import nxc.hcmus.gscore.mapper.mapper.GradeMapper;
import nxc.hcmus.gscore.model.entity.Student;
import nxc.hcmus.gscore.repository.GradeRepository;
import nxc.hcmus.gscore.repository.StudentRepository;
import nxc.hcmus.gscore.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    private final GradeMapper gradeMapper;

    private final StudentRepository studentRepository;

    @Override
    public List<GradeDto> getGradesByStudentSbd(String sbd) {
        var student = studentRepository.findBySbd(sbd)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with sbd: " + sbd));

        return gradeMapper.gradesToGradeDtos(gradeRepository.findGradesByStudentIs(student));
    }
}
