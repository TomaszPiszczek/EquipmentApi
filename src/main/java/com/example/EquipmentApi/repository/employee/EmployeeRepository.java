package com.example.EquipmentApi.repository.employee;

import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.user.User;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Set<Employee> findEmployeesByUser(User user);
    Optional<Employee> findEmployeeByEmployeeIdAndUser(UUID uuid, User user);

}
