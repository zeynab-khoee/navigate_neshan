package com.neshan.navigate_neshan.Mapper;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Model.Report.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Mapping(source = "id", target = "id")
    ReportDto reportToReportDTO(Report user);
    Report reportDtoToReport(ReportDto reportDto);
}
