package com.example.EquipmentApi.service;

import com.example.EquipmentApi.dto.EmployeeTrainingDTO;
import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.employee.EmployeeEquipment;
import com.example.EquipmentApi.model.employee.EmployeeTraining;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.dto.projections.EmployeeProjection;
import com.example.EquipmentApi.repository.employee.EmployeeEquipmentRepository;
import com.example.EquipmentApi.repository.employee.EmployeeRepository;
import com.example.EquipmentApi.repository.employee.EmployeeTrainingRepository;
import com.example.EquipmentApi.repository.user.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeService {
   private final   EmployeeRepository employeeRepository;
   private final EmployeeTrainingRepository employeeTrainingRepository;
   private final EquipmentRepository equipmentRepository;
   private final EmployeeEquipmentRepository employeeEquipmentRepository;
   private static final long NO_TRAINING_DAYS = -99999;


    public Set<EmployeeProjection> getEmployeeDTO(User user) {




       return employeeRepository.findEmployeesWithUserByUserId(user.getId());


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

    public void removeEmployee(User user, UUID empoyeeUUID) {
        Employee employee = employeeRepository.findEmployeeByEmployeeIdAndUser(empoyeeUUID,user).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        List<EmployeeEquipment> employeeEquipment =   employeeEquipmentRepository.findEmployeeEquipmentByEmployee(employee).orElseThrow(() -> new EntityNotFoundException("relation not found"));
        List<EmployeeTraining> employeeTraining = employeeTrainingRepository.findEmployeeTrainingByEmployee(employee);
        employee.setUser(null);
        employeeEquipmentRepository.deleteAll(employeeEquipment);
        employeeTrainingRepository.deleteAll(employeeTraining);
        employeeRepository.delete(employee);
    }
}
