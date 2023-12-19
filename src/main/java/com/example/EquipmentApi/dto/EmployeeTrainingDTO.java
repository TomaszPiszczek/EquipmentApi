package com.example.EquipmentApi.dto;

import com.example.EquipmentApi.model.employee.Employee;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EmployeeTrainingDTO(
        String name,
        String surname,
        LocalDateTime trainingDate,
        LocalDateTime expireDate,
        String description,
        String trainingName

) {

}