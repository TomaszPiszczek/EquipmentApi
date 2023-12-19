package com.example.EquipmentApi.service;

import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.employee.EmployeeTraining;
import com.example.EquipmentApi.model.user.Training;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.repository.employee.EmployeeRepository;
import com.example.EquipmentApi.repository.employee.EmployeeTrainingRepository;
import com.example.EquipmentApi.repository.employee.TrainingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TrainingService {
    TrainingRepository trainingRepository;
    EmployeeTrainingRepository employeeTrainingRepository;
    EmployeeRepository employeeRepository;
    EntityManager entityManager;
    @Transactional
    public void createTraining(String name, String description, User user) {
        Training training = Training.builder()
                .description(description)
                .user(user)
                .name(name)
                .build();
        trainingRepository.save(training);
    }


    public void signEmployeeToTraining(User user,UUID employeeUUID, UUID trainingUUID, LocalDateTime trainingDate, LocalDateTime expireDate) {
        Employee employee = employeeRepository.findEmployeeByEmployeeIdAndUser(employeeUUID,user).orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        EmployeeTraining employeeTraining = EmployeeTraining.builder()
                .employee(employee)
                .trainingDate(trainingDate)
                .trainingExpireDate(expireDate)
                .employeeTrainingId(trainingUUID)
                .build();
        employeeTrainingRepository.save(employeeTraining);
    }
    @Transactional
    public void signEmployeesToTraining(Set<UUID> employeeUUIDs, UUID trainingUUID, LocalDateTime trainingDate, LocalDateTime expireDate) {
        List<Employee> employees = employeeRepository.findAllById(employeeUUIDs);

        employees.forEach(employee -> {
            EmployeeTraining employeeTraining = EmployeeTraining.builder()
                    .employee(employee)
                    .trainingDate(trainingDate)
                    .trainingExpireDate(expireDate)
                    .employeeTrainingId(trainingUUID)
                    .build();
            entityManager.persist(employeeTraining);
        });

        entityManager.flush();
    }

    public void removeTraining(User user,UUID trainingUUID) {
        Training training = trainingRepository.getTrainingByTrainingIdAndUser(trainingUUID,user).orElseThrow(() -> new EntityNotFoundException("Training not found"));
        trainingRepository.delete(training);
    }

    public List<Training> getTrainings(User user) {
        return trainingRepository.getTrainingByUser(user);
    }
}
