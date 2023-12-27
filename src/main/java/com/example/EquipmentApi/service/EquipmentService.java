package com.example.EquipmentApi.service;

import com.example.EquipmentApi.dto.EmployeeEquipmentDTO;
import com.example.EquipmentApi.dto.EquipmentDTO;
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
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeEquipmentRepository employeeEquipmentRepository;
    //fixme 2
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


    public Set<EquipmentDTO> getEquipment(User user) {
        Set<Equipment> equipment = equipmentRepository.findAllByUser(user);
         return equipment.stream().map(
                equipment1 ->
                        EquipmentDTO.builder()
                                .image(equipment1.getImageData())
                                .name(equipment1.getName())
                                .description(equipment1.getDescription())
                                .uuid(equipment1.getEquipmentId())
                                .build()
                ).collect(Collectors.toSet());
    }
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
    @Transactional
    public void signEmployeesEquipment(User user, Set<UUID> employeeUUID, UUID equipmentUUID, LocalDateTime assignDate) {
        employeeUUID.forEach(uuid -> signEmployeeEquipment(user,uuid,equipmentUUID,assignDate));
    }
}
