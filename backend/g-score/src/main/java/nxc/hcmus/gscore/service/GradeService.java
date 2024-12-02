package nxc.hcmus.gscore.service;

import nxc.hcmus.gscore.mapper.dto.GradeDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GradeService {

    List<GradeDto> getGradesByStudentSbd(String sbd);
}
