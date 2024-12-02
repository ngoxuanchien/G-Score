package nxc.hcmus.gscore.service;

import nxc.hcmus.gscore.mapper.dto.StudentDto;
import nxc.hcmus.gscore.model.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StudentService {
    StudentDto getStudentBySbd(String sbd);

    void importStudent(MultipartFile file) throws IOException;
}
