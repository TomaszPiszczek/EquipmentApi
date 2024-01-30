package com.example.EquipmentApi.service;

import com.example.EquipmentApi.dto.TrainingDTO;
import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.employee.EmployeeTraining;
import com.example.EquipmentApi.model.user.Training;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.repository.employee.EmployeeRepository;
import com.example.EquipmentApi.repository.employee.EmployeeTrainingRepository;
import com.example.EquipmentApi.repository.employee.TrainingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final EmployeeTrainingRepository employeeTrainingRepository;
    private final EmployeeRepository employeeRepository;
    @Transactional
    public void createTraining(String name, String description, User user) {
        Training training = Training.builder()
                .description(description)
                .user(user)
                .name(name)
                .build();
        trainingRepository.save(training);
    }

    @Transactional
    public void signEmployeeToTraining(User user,UUID employeeUUID, UUID trainingUUID, LocalDateTime trainingDate, LocalDateTime expireDate) {
        Employee employee = employeeRepository.findEmployeeByEmployeeIdAndUser(employeeUUID,user).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        Training training = trainingRepository.findTrainingByTrainingIdAndUser(trainingUUID,user).orElseThrow(() -> new EntityNotFoundException("Training not found"));


        if (employeeTrainingRepository.existsByEmployeeAndTraining(employee, training)) {
            throw new IllegalStateException("Employee is already signed up for this training");
        }

        EmployeeTraining employeeTraining = EmployeeTraining.builder()
                .employee(employee)
                .trainingDate(trainingDate)
                .trainingExpireDate(expireDate)
                .training(training)
                .build();
        employeeTrainingRepository.save(employeeTraining);
    }

    @Transactional
    public void signEmployeesToTraining(User user,Set<UUID> employeeUUIDs, UUID trainingUUID, LocalDateTime trainingDate, LocalDateTime expireDate) {
        employeeUUIDs.forEach(uuid -> signEmployeeToTraining(user,uuid,trainingUUID,trainingDate,expireDate));
    }


    public void removeTrainingFromEmployee(User user, UUID employeeTrainingUUID) {
        EmployeeTraining employeeTraining = employeeTrainingRepository.findEmployeeTrainingByEmployeeTrainingId(employeeTrainingUUID).orElseThrow(()->new EntityNotFoundException("Training not found"));
        Training training = employeeTraining.getTraining();
        employeeTrainingRepository.delete(employeeTraining);
        trainingRepository.delete(training);

    }


    public List<TrainingDTO> getTrainings(User user) {
        List<Training> trainings =  trainingRepository.getTrainingByUser(user);

        return trainings.stream().map(
                training ->
                        TrainingDTO
                                .builder()
                                .uuid(training.getTrainingId())
                                .description(training.getDescription())
                                .trainingName(training.getName())
                                .build()
        ).collect(Collectors.toList());
    }

    public void removeTraining(User user, UUID trainingUUID) {
        Training training = trainingRepository.getTrainingByTrainingId(trainingUUID).orElseThrow(()->new EntityNotFoundException("Training not found"));
        trainingRepository.delete(training);
    }
}
