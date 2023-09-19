package com.neshan.navigate_neshan.Mapper;

import com.neshan.navigate_neshan.Dto.UserDto;
import com.neshan.navigate_neshan.Model.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    @Mapping(source = "id", target = "id")
    UserDto userToUserDto(UserInfo user);
    UserInfo userDtoToUsers(UserDto UserDto);

}


