package com.example.EquipmentApi.model.employee;

import com.example.EquipmentApi.model.user.Training;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_training")
public class EmployeeTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "employee_training_id")
    private UUID employeeTrainingId;

    @Column(name = "training_date")
    private LocalDateTime trainingDate;

    @Column(name = "expire_date")
    private LocalDateTime trainingExpireDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id")
    private Training training;

    public Employee getEmployee() {
        return employee;
    }
}
