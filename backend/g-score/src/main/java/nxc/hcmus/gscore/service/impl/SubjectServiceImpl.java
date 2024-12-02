package nxc.hcmus.gscore.service.impl;

import lombok.RequiredArgsConstructor;
import nxc.hcmus.gscore.mapper.dto.SubjectDto;
import nxc.hcmus.gscore.model.entity.Subject;
import nxc.hcmus.gscore.repository.SubjectRepository;
import nxc.hcmus.gscore.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public SubjectDto create(SubjectDto subjectDto) {
        return null;
    }

    @Override
    public Subject create(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Optional<Subject> getSubjectByName(String name) {
        return subjectRepository.findByName(name);
    }
}
