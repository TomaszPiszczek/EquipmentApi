package com.example.EquipmentApi.repository.employee;

import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.employee.EmployeeTraining;
import com.example.EquipmentApi.model.user.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface EmployeeTrainingRepository extends JpaRepository<EmployeeTraining,UUID > {
    Set<EmployeeTraining> getEmployeeTrainingByEmployee(Employee employee);
    EmployeeTraining findFirstByEmployeeEmployeeIdOrderByTrainingExpireDateAsc(UUID employeeId);
    Optional<EmployeeTraining> findEmployeeTrainingByEmployeeTrainingId(UUID employeeTrainingUUID);
    List<EmployeeTraining> findEmployeeTrainingByEmployee(Employee employee);

    boolean existsByEmployeeAndTraining(Employee employee, Training training);
}
