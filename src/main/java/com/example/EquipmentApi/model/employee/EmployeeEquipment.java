package com.example.EquipmentApi.model.employee;

import com.example.EquipmentApi.model.user.Equipment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_equipment")
public class EmployeeEquipment {
    @Column(name = "assign_id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID assignId;

    @Column(name = "date")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime assignDate;

    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne()
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;


}
