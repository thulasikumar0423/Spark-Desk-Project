package com.example.SparkDesk.mapper;

import com.example.SparkDesk.dto.progress.ProgressDTO;
import com.example.SparkDesk.model.Progress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProgressMapper {
    ProgressMapper INSTANCE = Mappers.getMapper(ProgressMapper.class);

    @Mapping(source = "task.Id", target = "taskId")
    ProgressDTO toDTO(Progress progress);

    @Mapping(source = "taskId", target = "task.Id")
    Progress toEntity(ProgressDTO dto);
}
