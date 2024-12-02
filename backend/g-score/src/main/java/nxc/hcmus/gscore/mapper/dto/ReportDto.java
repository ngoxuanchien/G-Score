package nxc.hcmus.gscore.mapper.dto;

import java.util.List;

public record ReportDto(
        Long sumStudent,
        List<StatisticDto> statistics
) {
}
