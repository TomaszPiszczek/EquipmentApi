package com.example.EquipmentApi.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EquipmentDTO(
        UUID uuid,
        String name,
        String description,
        byte[] image

) {
}
