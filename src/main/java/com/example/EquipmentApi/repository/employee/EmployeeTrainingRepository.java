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
    @Query("SELECT et FROM EmployeeTraining et JOIN FETCH et.employee e WHERE e = :employee")
    Set<EmployeeTraining> getEmployeeTrainingByEmployee(Employee employee);
    Optional<EmployeeTraining> findEmployeeTrainingByEmployeeTrainingId(UUID employeeTrainingUUID);
    List<EmployeeTraining> findEmployeeTrainingByEmployee(Employee employee);

    boolean existsByEmployeeAndTraining(Employee employee, Training training);
}
