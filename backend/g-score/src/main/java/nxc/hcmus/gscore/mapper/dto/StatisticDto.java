package nxc.hcmus.gscore.mapper.dto;

import lombok.*;

public record StatisticDto (
        String subject,
        String level,
        Long count
) {
}
