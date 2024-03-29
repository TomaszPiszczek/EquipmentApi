package com.example.EquipmentApi.model.employee;

import com.example.EquipmentApi.model.user.Equipment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
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

    @Column(name = "in_use")
    private boolean inUse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;



}
