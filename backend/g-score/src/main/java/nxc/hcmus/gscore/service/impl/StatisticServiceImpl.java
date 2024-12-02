package nxc.hcmus.gscore.service.impl;

import lombok.RequiredArgsConstructor;
import nxc.hcmus.gscore.mapper.dto.ReportDto;
import nxc.hcmus.gscore.mapper.dto.StatisticDto;
import nxc.hcmus.gscore.mapper.dto.StudentTopDto;
import nxc.hcmus.gscore.mapper.mapper.StatisticMapper;
import nxc.hcmus.gscore.mapper.mapper.StudentTopMapper;
import nxc.hcmus.gscore.repository.GradeRepository;
import nxc.hcmus.gscore.service.StatisticService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final GradeRepository gradeRepository;

    private final StatisticMapper statisticMapper;

    private final StudentTopMapper studentTopMapper;

    private ReportDto cacheReport = null;

    private List<StudentTopDto> cacheTop10AStudents = null;

    @Override
    public ReportDto getStatistics() {
        if (cacheReport != null) {
            return cacheReport;
        }

        var statistics = statisticMapper.mapToStatisticDtos(gradeRepository.getStatistics());
        Long sum = 0L;
        for (StatisticDto statistic : statistics) {
            sum += statistic.count();
        }

        cacheReport = new ReportDto(sum, statistics);
        return cacheReport;
    }

    @Override
    public List<StudentTopDto> getTop10AStudents() {
        if (cacheTop10AStudents != null) {
            return cacheTop10AStudents;
        }

        cacheTop10AStudents = studentTopMapper.mapToStudentTopDto(gradeRepository.getTop10Students());
        return cacheTop10AStudents;
    }

    @Scheduled(fixedRate = 600000)
    private void updateCache() {
        cacheReport = getStatistics();
        cacheTop10AStudents = getTop10AStudents();
    }
}
