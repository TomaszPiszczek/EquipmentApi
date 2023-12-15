package com.example.EquipmentApi.model.employee;

import com.example.EquipmentApi.model.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor

@Data
@Table(name = "training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "training_id")
    private UUID trainingId;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "description")
    private String description;


    @ManyToMany(
            cascade = {CascadeType.MERGE,CascadeType.MERGE}
    )
    @JoinTable(
            name = "employee_training",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees;

}
