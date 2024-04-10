package ru.ivasenko.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.ivasenko.data.Task;
import ru.ivasenko.dto.TaskDTO;

import java.util.List;

@Mapper(uses = TaskMapper.class)
public interface ListTaskMapper {
    ListTaskMapper INSTANCE = Mappers.getMapper(ListTaskMapper.class);
    List<Task> toModel(List<TaskDTO> dto);
    List<TaskDTO> toDTO(List<Task> model);
}
