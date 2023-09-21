package com.neshan.navigate_neshan.Mapper;

import com.neshan.navigate_neshan.Data.Dto.ReportDto;
import com.neshan.navigate_neshan.Data.Model.Report.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Mapping(source = "id", target = "id")
    ReportDto reportToReportDTO(Report user);
    Report reportDtoToReport(Optional<Report> reportDto);
}
