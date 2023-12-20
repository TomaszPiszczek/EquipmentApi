package com.example.EquipmentApi.service;

import com.example.EquipmentApi.dto.EmployeeEquipmentDTO;
import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.employee.EmployeeEquipment;
import com.example.EquipmentApi.model.user.Equipment;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.repository.employee.EmployeeEquipmentRepository;
import com.example.EquipmentApi.repository.employee.EmployeeRepository;
import com.example.EquipmentApi.repository.user.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EquipmentService {
    EquipmentRepository equipmentRepository;
    EmployeeRepository employeeRepository;
    EmployeeEquipmentRepository employeeEquipmentRepository;
    @Transactional
    public void createEquipment(byte[] image,BigDecimal price, String name, String description, User user) {
        Equipment equipment = Equipment.builder()
                .price(price)
                .description(description)
                .user(user)
                .name(name)
                .imageData(image)
                .build();
        equipmentRepository.save(equipment);
    }

    @Transactional
    public void removeEquipment(UUID equipmentUUID, User user) {
        Equipment equipment = equipmentRepository.findEquipmentByEquipmentIdAndUser(equipmentUUID,user).orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
        equipmentRepository.delete(equipment);

    }

    public List<Equipment> getEquipment(User user) {
        return equipmentRepository.findAllByUser(user);
    }
        //todo POSTMAN TEST
    public List<EmployeeEquipmentDTO> getEmployeeEquipment(User user, UUID employeeUUID) {
        return employeeEquipmentRepository.getEmployeeEquipmentDTOList(employeeUUID,user.getId());
    }
    @Transactional
    public void signEmployeeEquipment(User user, UUID employeeUUID, UUID equipmentUUID, LocalDateTime assignDate) {
        Employee employee = employeeRepository.findEmployeeByEmployeeIdAndUser(employeeUUID,user).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        Equipment equipment = equipmentRepository.findEquipmentByEquipmentIdAndUser(equipmentUUID,user).orElseThrow(() -> new EntityNotFoundException("Equipment not found"));

        EmployeeEquipment employeeEquipment = EmployeeEquipment.builder()
                .equipment(equipment)
                .employee(employee)
                .assignDate(assignDate)
                .inUse(true)
                .build();

        employeeEquipmentRepository.save(employeeEquipment);
    }

    @Transactional
    public void removeEquipmentFromEmployee(User user, UUID employeeEquipmentUUID, UUID employeeUUID) {
        Employee employee = employeeRepository.findEmployeeByEmployeeIdAndUser(employeeUUID,user).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        EmployeeEquipment employeeEquipment = employeeEquipmentRepository.findEmployeeEquipmentByAssignIdAndEmployee(employeeEquipmentUUID,employee).orElseThrow(() -> new EntityNotFoundException("Assign not found"));

        employeeEquipmentRepository.delete(employeeEquipment);
    }

    @Transactional
    public void unSetEquipmentFromEmployee(User user, UUID employeeEquipmentUUID, UUID employeeUUID) {
        Employee employee = employeeRepository.findEmployeeByEmployeeIdAndUser(employeeUUID,user).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        EmployeeEquipment employeeEquipment = employeeEquipmentRepository.findEmployeeEquipmentByAssignIdAndEmployee(employeeEquipmentUUID,employee).orElseThrow(() -> new EntityNotFoundException("Assign not found"));
        employeeEquipment.setInUse(false);

        employeeEquipmentRepository.save(employeeEquipment);

    }
}
