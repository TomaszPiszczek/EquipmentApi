package com.example.EquipmentApi.dto;

import com.example.EquipmentApi.model.employee.Employee;
import lombok.Builder;

import java.util.UUID;


@Builder
public record EmployeeDTO(
        String name,
        String surname,
        UUID uuid
) {

}