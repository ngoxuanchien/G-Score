package nxc.hcmus.gscore.controller;

import lombok.RequiredArgsConstructor;
import nxc.hcmus.gscore.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/import")
    public ResponseEntity<?> importStudent(
            @RequestBody MultipartFile file
    ) {
        if (file.getContentType() == null || !file.getContentType().equals("csv")) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{sbd}")
    public ResponseEntity<?> getStudentBySbd(
            @PathVariable String sbd
    ) {
        return ResponseEntity.ok(studentService.getStudentBySbd(sbd));
    }
}
