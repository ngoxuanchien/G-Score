package nxc.hcmus.gscore.mapper.mapper;

import nxc.hcmus.gscore.mapper.dto.StudentDto;
import nxc.hcmus.gscore.model.entity.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {GradeMapper.class})
public interface StudentMapper {

    StudentDto studentToStudentDto(Student student);
}
