package com.example.EquipmentApi.dto;

import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.employee.EmployeeTraining;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record EmployeeDTO(
        UUID uuid,
        String name,
        String surname,
        long daysToTraining,
        long numberOfTools
) {
}
