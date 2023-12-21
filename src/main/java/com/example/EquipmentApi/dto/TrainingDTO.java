package com.example.EquipmentApi.dto;

import lombok.Builder;

import java.util.UUID;
@Builder
public record TrainingDTO(
        UUID uuid,

        String name,
        String description

) {
}
