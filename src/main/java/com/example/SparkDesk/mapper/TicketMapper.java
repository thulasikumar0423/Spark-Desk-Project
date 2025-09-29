package com.example.SparkDesk.mapper;

import com.example.SparkDesk.dto.ticket.SupportTicketDTO;
import com.example.SparkDesk.model.SupportTicket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    @Mapping(source = "raisedBy.id", target = "raisedById")
    @Mapping(source = "assignedToIt.id", target = "assignedToItId")
    SupportTicketDTO toDTO(SupportTicket ticket);

    @Mapping(source = "raisedById", target = "raisedBy.id")
    @Mapping(source = "assignedToItId", target = "assignedToIt.id")
    SupportTicket toEntity(SupportTicketDTO dto);
}
