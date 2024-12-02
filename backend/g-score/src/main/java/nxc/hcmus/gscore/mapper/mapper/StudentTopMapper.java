package nxc.hcmus.gscore.mapper.mapper;

import nxc.hcmus.gscore.mapper.dto.StudentTopDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface StudentTopMapper {

    default StudentTopDto mapToStudentTopDto(Map<String, Object> student) {
        return new StudentTopDto(
                (Long) student.get("id"),
                (String) student.get("sbd"),
                (Double) student.get("total_score")
        );
    }

    default List<StudentTopDto> mapToStudentTopDto(List<Map<String, Object>> students) {
        return students.stream()
                .map(this::mapToStudentTopDto)
                .toList();
    }
}
