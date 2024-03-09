package com.example.EquipmentApi.repository.user;

import com.example.EquipmentApi.model.user.Equipment;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.dto.projections.EquipmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {
    Optional<Equipment> findEquipmentByEquipmentIdAndUser(UUID equipmentUUID, User user);
    Set<EquipmentProjection> findAllByUser(User user);
    @Query("""
        SELECT COUNT(ee)
        FROM EmployeeEquipment ee
        WHERE ee.employee.employeeId = :employeeUUID
    """)
    long countToolsForEmployee(@Param("employeeUUID") UUID employeeUUID);

}
