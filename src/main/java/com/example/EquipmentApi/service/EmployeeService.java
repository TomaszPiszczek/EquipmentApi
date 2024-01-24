package com.example.EquipmentApi.service;

import com.example.EquipmentApi.dto.EmployeeDTO;
import com.example.EquipmentApi.dto.EmployeeTrainingDTO;
import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.employee.EmployeeTraining;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.repository.employee.EmployeeRepository;
import com.example.EquipmentApi.repository.employee.EmployeeTrainingRepository;
import com.example.EquipmentApi.repository.user.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.EquipmentApi.dto.EmployeeDTO.calculateDaysToTraining;

@Service
@AllArgsConstructor
public class EmployeeService {
   private final   EmployeeRepository employeeRepository;
   private final EmployeeTrainingRepository employeeTrainingRepository;
   private final EquipmentRepository equipmentRepository;

    public Set<EmployeeDTO> getEmployeeDTO(User user) {
        return employeeRepository.findEmployeesByUser(user)
                .stream()
                .map(employee -> {
                    EmployeeTraining employeeTraining = employeeTrainingRepository.findFirstByEmployeeEmployeeIdOrderByTrainingExpireDateAsc(employee.getEmployeeId());
                    long equipmentCount = equipmentRepository.countToolsForEmployee(employee.getEmployeeId());

                    long daysToTraining;
                    if(employeeTraining == null) {
                         daysToTraining = -99999;
                    }else{
                         daysToTraining = calculateDaysToTraining(employeeTraining);

                    }
                    return EmployeeDTO.builder()
                            .uuid(employee.getEmployeeId())
                            .name(employee.getName())
                            .surname(employee.getSurname())
                            .daysToTraining(daysToTraining)
                            .numberOfTools(equipmentCount)
                            .build();
                })
                .collect(Collectors.toSet());
    }
    public Set<EmployeeTrainingDTO> getEmployeeTrainingDTO(UUID uuid,User user) {
        Employee employee = employeeRepository.findEmployeeByEmployeeIdAndUser(uuid,user).orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        Set<EmployeeTraining> employeeTrainingList = employeeTrainingRepository.getEmployeeTrainingByEmployee(employee);
        return employeeTrainingList
                .stream()
                .map(
                        employeeTraining -> EmployeeTrainingDTO.builder()
                                .uuid(employeeTraining.getEmployeeTrainingId())
                                .trainingDate(employeeTraining.getTrainingDate())
                                .expireDate(employeeTraining.getTrainingExpireDate())
                                .name(employee.getName())
                                .surname(employee.getSurname())
                                .trainingName(employeeTraining.getTraining().getName())
                                .description(employeeTraining.getTraining().getDescription())
                                .build()
                        ).collect(Collectors.toSet());


    }

    @Transactional
    public void addEmployee(User user, String name, String surname) {
        Employee employee = Employee.builder()
                .surname(surname)
                .name(name)
                .user(user)
                .build();
        employeeRepository.save(employee);
    }

    public Employee getEmployee(User user, UUID employeeUUID) {
        return employeeRepository.findEmployeeByEmployeeIdAndUser(employeeUUID,user).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }
}
