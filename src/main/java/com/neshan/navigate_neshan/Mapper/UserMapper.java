package com.neshan.navigate_neshan.Mapper;

import com.neshan.navigate_neshan.Dto.UserDto;
import com.neshan.navigate_neshan.Model.Report;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    UserDto userToUserDTO(Report user);
}
