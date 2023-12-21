package com.example.EquipmentApi.model.user;

import com.example.EquipmentApi.model.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Data
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "equipment_id")
    private UUID equipmentId;

    @Column(name = "name",unique = true)
    @NonNull
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String  description;

    @Column(name = "image")
    private byte[] imageData;

    @ManyToMany(
            cascade = {CascadeType.MERGE,CascadeType.MERGE}
    )
    @JoinTable(
            name = "employee_equipment",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;





}
