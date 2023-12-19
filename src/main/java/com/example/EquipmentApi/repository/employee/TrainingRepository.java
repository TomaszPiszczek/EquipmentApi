package com.example.EquipmentApi.repository.employee;

import com.example.EquipmentApi.model.user.Training;
import com.example.EquipmentApi.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainingRepository extends JpaRepository<Training, UUID> {
    Optional<Training> getTrainingByTrainingIdAndUser(UUID uuid,User user);
    List<Training> getTrainingByUser(User user);
}
