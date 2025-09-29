package com.example.SparkDesk.mapper;

import com.example.SparkDesk.dto.task.TaskDTO;
import com.example.SparkDesk.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "assignedTo.id", target = "assignedToId")
    @Mapping(source = "assignedBy.id", target = "assignedById")
    TaskDTO toDTO(Task task);

    @Mapping(source = "assignedToId", target = "assignedTo.id")
    @Mapping(source = "assignedById", target = "assignedBy.id")
    Task toEntity(TaskDTO dto);
}
