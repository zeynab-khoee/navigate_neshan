package com.neshan.navigate_neshan.Mapper;

import com.neshan.navigate_neshan.Dto.ReportDto;
import com.neshan.navigate_neshan.Model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Mapping(source = "fullName", target = "name")
    ReportDto userToUserDTO(Report user);
}
