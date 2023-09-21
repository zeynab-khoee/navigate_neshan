package com.neshan.navigate_neshan.Data.Dto;

import com.neshan.navigate_neshan.Data.Enum.ReportType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportDto {
    private Long id;
    private ReportType type;
}
