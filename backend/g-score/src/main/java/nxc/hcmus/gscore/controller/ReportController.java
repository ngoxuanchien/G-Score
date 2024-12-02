package nxc.hcmus.gscore.controller;

import lombok.RequiredArgsConstructor;
import nxc.hcmus.gscore.mapper.dto.ReportDto;
import nxc.hcmus.gscore.mapper.dto.StudentTopDto;
import nxc.hcmus.gscore.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final StatisticService statisticService;

    @GetMapping("/top-10-A")
    public ResponseEntity<List<StudentTopDto>> getTop10AStudents() {
        return ResponseEntity.ok(statisticService.getTop10AStudents());
    }

    @GetMapping("/statistics")
    public ResponseEntity<ReportDto> getStatistics() {
        return ResponseEntity.ok(statisticService.getStatistics());
    }
}
