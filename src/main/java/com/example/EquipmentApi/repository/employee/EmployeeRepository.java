package com.example.EquipmentApi.repository.employee;

import com.example.EquipmentApi.dto.EmployeeDTO;
import com.example.EquipmentApi.dto.projections.EmployeeProjection;
import com.example.EquipmentApi.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query(value = "SELECT new com.example.EquipmentApi.dto.EmployeeDTO(e.employeeId, e.name, e.surname) FROM Employee e WHERE e.user.id = :userId")
    Set<EmployeeDTO> findEmployeesByUserId(UUID userId);

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.user WHERE e.employeeId = :employeeId")
    Optional<Employee> findEmployeeByEmployeeId(@Param("employeeId") UUID employeeId);


    @Query(value = "SELECT new com.example.EquipmentApi.dto.EmployeeDTO(e.employeeId, e.name, e.surname) FROM Employee e WHERE e.employeeId = :employeeId")
    Optional<EmployeeDTO> getEmployeeDtoByEmployeeId(@Param("employeeId") UUID employeeId);





}
