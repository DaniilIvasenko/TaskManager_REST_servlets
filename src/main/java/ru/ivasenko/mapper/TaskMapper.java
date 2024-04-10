package ru.ivasenko.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.ivasenko.data.Task;
import ru.ivasenko.dto.TaskDTO;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task toModel(TaskDTO dto);
    @Mapping(expression = "java(model.getUser().getId())", target = "userID")
    TaskDTO toDto(Task model);
}
