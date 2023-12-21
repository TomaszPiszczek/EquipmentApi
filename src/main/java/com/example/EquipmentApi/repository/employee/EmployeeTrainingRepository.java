package com.example.EquipmentApi.repository.employee;

import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.employee.EmployeeTraining;
import com.example.EquipmentApi.model.user.Training;
import com.example.EquipmentApi.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface EmployeeTrainingRepository extends JpaRepository<EmployeeTraining,UUID > {
    Set<EmployeeTraining> getEmployeeTrainingByEmployee(Employee employee);
    boolean existsByEmployeeAndTraining(Employee employee, Training training);
}
