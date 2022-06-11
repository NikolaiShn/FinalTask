package com.dto.mappers;

import com.dto.UserDto;
import com.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {KnowledgeDirectoryMapper.class, CourseMapper.class})
public interface UserMapper {

    UserDto userToUserDto (User user);
    User userToUserDto (UserDto userDto);
}
