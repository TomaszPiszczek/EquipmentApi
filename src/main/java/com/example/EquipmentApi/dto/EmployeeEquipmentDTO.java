package com.example.EquipmentApi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EmployeeEquipmentDTO(
        String name,
        String description,
        LocalDateTime assignDate,
        byte[] imageData,
        boolean inUse

) {
}
