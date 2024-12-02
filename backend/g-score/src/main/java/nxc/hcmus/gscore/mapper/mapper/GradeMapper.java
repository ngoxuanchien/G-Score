package nxc.hcmus.gscore.mapper.mapper;

import nxc.hcmus.gscore.mapper.dto.GradeDto;
import nxc.hcmus.gscore.model.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    @Mapping(target = "subject", source = "subject.name")
    GradeDto gradeToGradeDto(Grade grade);

    List<GradeDto> gradesToGradeDtos(List<Grade> grades);
}
