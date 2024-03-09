package com.example.EquipmentApi.repository.employee;

import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.dto.projections.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Set<EmployeeProjection> findEmployeesWithUserByUserId(UUID userId);
    
    Optional<Employee> findEmployeeByEmployeeIdAndUser(UUID uuid, User user);

}
