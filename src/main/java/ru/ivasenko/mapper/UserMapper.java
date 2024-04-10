package ru.ivasenko.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.ivasenko.data.User;
import ru.ivasenko.dto.UserDTO;
import ru.ivasenko.dto.UserDataDTO;

/**
 * The interface User mapper.
 */
@Mapper()
public interface UserMapper {
    /**
     * The constant userMapper.
     */
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    /**
     * To dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    @Mapping(expression = "java(ListTaskMapper.INSTANCE.toDTO(user.getTasks()))", target = "tasks")
    UserDTO toDto(User user);

    /**
     * To model user.
     *
     * @param dto the dto
     * @return the user
     */
    User toModel(UserDTO dto);
}
