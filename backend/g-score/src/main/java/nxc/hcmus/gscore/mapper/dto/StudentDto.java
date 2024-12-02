package nxc.hcmus.gscore.mapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StudentDto(
        Long id,
        String sbd,

        @JsonProperty("ma_ngoai_ngu")
        String maNgoaiNgu,
        
        List<GradeDto> grades
) {
}
