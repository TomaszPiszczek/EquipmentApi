package com.example.EquipmentApi.dto.projections;

import java.util.UUID;

public interface EmployeeProjection {
    String getName();
    String getSurname();
    UUID getEmployeeId();
    UUID getUserId();
}