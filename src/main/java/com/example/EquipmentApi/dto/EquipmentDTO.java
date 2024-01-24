package com.example.EquipmentApi.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record EquipmentDTO(
        UUID uuid,
        String name,
        String description,
        BigDecimal price,

        byte[] image

) {
}
