package com.example.SparkDesk.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.SparkDesk.dto.user.UserResponseDTO;
import com.example.SparkDesk.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toDTO(User user);
    User toEntity(UserResponseDTO dto);
}
