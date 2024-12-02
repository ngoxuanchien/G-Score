package nxc.hcmus.gscore.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import nxc.hcmus.gscore.mapper.dto.GradeDto;
import nxc.hcmus.gscore.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    @GetMapping()
    public ResponseEntity<List<GradeDto>> getGrades(
            @PathParam("sbd") String sbd
    ) {
        return ResponseEntity.ok(gradeService.getGradesByStudentSbd(sbd));
    }
}
