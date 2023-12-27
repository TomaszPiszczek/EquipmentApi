package com.example.EquipmentApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
//Cannot create record because intellij doesnt recognize constructor in EmployeeEquipmentRepository class
@Data
@Builder

public class EmployeeEquipmentDTO {
    private String name;
    private String description;
    private byte[] imageData;
    private LocalDateTime assignDate;
    private boolean inUse;

    public EmployeeEquipmentDTO(String name, String description, byte[] imageData, LocalDateTime assignDate, boolean inUse) {
        this.name = name;
        this.description = description;
        this.imageData = imageData;
        this.assignDate = assignDate;
        this.inUse = inUse;
    }
}