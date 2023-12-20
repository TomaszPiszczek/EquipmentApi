package com.example.EquipmentApi.repository.employee;

import com.example.EquipmentApi.dto.EmployeeEquipmentDTO;
import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.employee.EmployeeEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeEquipmentRepository extends JpaRepository<EmployeeEquipment, UUID> {
    @Query(value = """
            SELECT
            e.name,
            e.description,
            e.image,
            ee.date AS assignDate,
            ee.in_use AS inUse
            FROM
            public.equipment e
            JOIN
            public.employee_equipment ee ON e.equipment_id = ee.equipment_id
            JOIN
            public.employee em ON ee.employee_id = em.employee_id
            JOIN
            public.user_account ua ON e.user_id = ua.user_id
            WHERE
            em.employee_id = :employeeUUID
            AND ua.user_id = :userUUID
            """,nativeQuery = true)
    List<EmployeeEquipmentDTO> getEmployeeEquipmentDTOList(UUID employeeUUID,UUID userUUID);

    Optional<EmployeeEquipment> findEmployeeEquipmentByAssignIdAndEmployee(UUID assignId, Employee employee);

}
