package nxc.hcmus.gscore.service;

import nxc.hcmus.gscore.mapper.dto.SubjectDto;
import nxc.hcmus.gscore.model.entity.Subject;

import java.util.Optional;

public interface SubjectService {
    SubjectDto create(SubjectDto subjectDto);

    Subject create(Subject subject);

    Optional<Subject> getSubjectByName(String name);
}
