package nxc.hcmus.gscore.mapper.mapper;

import nxc.hcmus.gscore.mapper.dto.StatisticDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface StatisticMapper {

    default StatisticDto mapToStatisticDto(Map<String, Object> statistic) {
        return new StatisticDto(
                (String) statistic.get("subject"),
                (String) statistic.get("level"),
                (Long) statistic.get("count")
        );
    }

    default List<StatisticDto> mapToStatisticDtos(List<Map<String, Object>> statistics) {
        return statistics.stream()
                .map(this::mapToStatisticDto)
                .toList();
    }
}
