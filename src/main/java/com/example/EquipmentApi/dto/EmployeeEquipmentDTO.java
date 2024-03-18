package com.example.EquipmentApi.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

//Cannot create record because intellij doesnt recognize constructor in EmployeeEquipmentRepository class
@Getter
@Setter
@Builder

public class EmployeeEquipmentDTO {
    private UUID uuid;
    private String name;
    private String description;
    private byte[] imageData;
    private LocalDateTime assignDate;
    private boolean inUse;

    public EmployeeEquipmentDTO(UUID uuid,String name, String description, byte[] imageData, LocalDateTime assignDate, boolean inUse) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.imageData = imageData;
        this.assignDate = assignDate;
        this.inUse = inUse;
    }
}