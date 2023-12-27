package com.example.EquipmentApi.dto;

import com.example.EquipmentApi.model.employee.Employee;
import lombok.Builder;

import java.util.UUID;


@Builder
public record EmployeeDTO(
        UUID uuid,
        String name,
        String surname
) {

}