package com.example.EquipmentApi.model.employee;

import com.example.EquipmentApi.model.user.Training;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Data
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


    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne()
    @JoinColumn(name = "training_id")
    private Training training;

    public Employee getEmployee() {
        return employee;
    }
}
