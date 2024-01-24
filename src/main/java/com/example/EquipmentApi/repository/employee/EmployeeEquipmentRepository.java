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
    @Query("""
        SELECT
        new com.example.EquipmentApi.dto.EmployeeEquipmentDTO(
            ee.assignId,
            e.name,
            e.description,
            e.imageData,
            ee.assignDate,
            ee.inUse
            
        )
        FROM
        Equipment e
        JOIN
        EmployeeEquipment ee ON e.equipmentId = ee.equipment.equipmentId
        JOIN
        Employee em ON ee.employee.employeeId = em.employeeId
        JOIN
        User ua ON e.user.id = ua.id
        WHERE
        em.employeeId = :employeeUUID
        AND ua.id= :userUUID
        """)

    List<EmployeeEquipmentDTO> getEmployeeEquipmentDTOList(UUID employeeUUID, UUID userUUID);

    Optional<EmployeeEquipment> findEmployeeEquipmentByAssignIdAndEmployee(UUID assignId, Employee employee);

}
