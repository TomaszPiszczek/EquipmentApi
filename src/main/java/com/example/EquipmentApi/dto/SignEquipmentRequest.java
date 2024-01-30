package com.example.EquipmentApi.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SignEquipmentRequest {

    private Set<UUID> employeeUUID;
    private Set<UUID> equipmentUUID;
    private LocalDateTime assignDate;

    // Gettery i settery

    public Set<UUID> getEmployeeUUID() {
        return employeeUUID;
    }

    public void setEmployeeUUID(Set<UUID> employeeUUID) {
        this.employeeUUID = employeeUUID;
    }

    public Set<UUID> getEquipmentUUID() {
        return equipmentUUID;
    }

    public void setEquipmentUUID(Set<UUID> equipmentUUID) {
        this.equipmentUUID = equipmentUUID;
    }

    public LocalDateTime getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(LocalDateTime assignDate) {
        this.assignDate = assignDate;
    }
}
