package com.example.EquipmentApi.model.employee;

import com.example.EquipmentApi.model.user.Equipment;
import com.example.EquipmentApi.model.user.Training;
import com.example.EquipmentApi.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Data
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

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "employees", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Equipment> tools;

    @ManyToMany(mappedBy = "employees", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Training> trainings;


    @PreRemove
    private void preRemove() {

    }
}
