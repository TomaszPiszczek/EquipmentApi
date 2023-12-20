package com.example.EquipmentApi.repository.user;

import com.example.EquipmentApi.model.user.Equipment;
import com.example.EquipmentApi.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {
    Optional<Equipment> findEquipmentByEquipmentIdAndUser(UUID equipmentUUID, User user);
    List<Equipment> findAllByUser(User user);

}
