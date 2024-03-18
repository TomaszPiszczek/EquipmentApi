package com.example.EquipmentApi.model.employee;

import com.example.EquipmentApi.model.user.Equipment;
import com.example.EquipmentApi.model.user.Training;
import com.example.EquipmentApi.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "employee_id")
    private UUID employeeId;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "surname")
    @NonNull
    private String surname;

    @ManyToOne( cascade = {CascadeType.MERGE,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(
            cascade = {CascadeType.MERGE,CascadeType.MERGE},fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "employee_equipment",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> tools;
    @ManyToMany(
            cascade = {CascadeType.MERGE,CascadeType.MERGE},fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "employee_training",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "training_id")
    )
    private Set<Training> trainings;


    @PreRemove
    private void preRemove() {

    }
}
