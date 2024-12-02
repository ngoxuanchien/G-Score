package nxc.hcmus.gscore.service;

import nxc.hcmus.gscore.mapper.dto.ReportDto;
import nxc.hcmus.gscore.mapper.dto.StatisticDto;
import nxc.hcmus.gscore.mapper.dto.StudentDto;
import nxc.hcmus.gscore.mapper.dto.StudentTopDto;

import java.util.List;

public interface StatisticService {
    ReportDto getStatistics();

    List<StudentTopDto> getTop10AStudents();
}
