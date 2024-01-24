package com.example.EquipmentApi.dto;

import com.example.EquipmentApi.model.employee.Employee;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record EmployeeTrainingDTO(
        //todo uuid add to DTO
        UUID uuid,
        String name,
        String surname,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDateTime trainingDate,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDateTime expireDate,
        String description,
        String trainingName

) {

}