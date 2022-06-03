package com.dto.mappers;

import com.dto.UserDto;
import com.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {KnowledgeDirectoryMapper.class, CourseMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto (User user);
    User userToUserDto (UserDto userDto);
}
