package com.example.EquipmentApi.dto.projections;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface EquipmentProjection {
     UUID getEquipmentId();
     String getName();
     String getDescription();
     BigDecimal getPrice();
     LocalDateTime getServiceDate();
     byte[] getImageData();


}
