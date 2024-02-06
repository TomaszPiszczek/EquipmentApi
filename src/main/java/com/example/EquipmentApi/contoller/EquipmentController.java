package com.example.EquipmentApi.contoller;

import com.example.EquipmentApi.dto.EmployeeEquipmentDTO;
import com.example.EquipmentApi.dto.EquipmentDTO;
import com.example.EquipmentApi.dto.SignEquipmentRequest;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/equipment")
public class EquipmentController {
    EquipmentService equipmentService;

    @PostMapping("/createEquipment")
    public ResponseEntity<String> createEquipment(byte[] image, BigDecimal price, String name, String description, LocalDateTime serviceDate, @AuthenticationPrincipal User user) {
        equipmentService.createEquipment(image, price, name, description,serviceDate ,user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/removeEquipment")
    public ResponseEntity<String> removeEquipment(UUID equipmentUUID, @AuthenticationPrincipal User user) {
        equipmentService.removeEquipment(equipmentUUID, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getEquipment")
    public ResponseEntity<Set<EquipmentDTO>> getEquipment(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(equipmentService.getEquipment(user));

    }

    @PatchMapping("/changeDate")
    public ResponseEntity<String> changeDate(UUID equipmentUUID,LocalDateTime dateTime,@AuthenticationPrincipal User user) {
        equipmentService.changeDate(equipmentUUID,dateTime,user);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/getEmployeeEquipment")
    public ResponseEntity<List<EmployeeEquipmentDTO>> getEmployeeEquipment(@AuthenticationPrincipal User user, UUID employeeUUID) {
        return ResponseEntity.ok(equipmentService.getEmployeeEquipment(user, employeeUUID));

    }

    @PostMapping("/signEquipmentToEmployee")
    public ResponseEntity<String> signEmployeeToEquipment(@AuthenticationPrincipal User user, UUID employeeUUID, UUID equipmentUUID, LocalDateTime assignDate) {
        equipmentService.signEmployeeEquipment(user, employeeUUID,equipmentUUID,assignDate);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/signEquipmentToEmployees")
    public ResponseEntity<String> signEmployeesToEquipment(@AuthenticationPrincipal User user,@RequestBody Set<UUID> employeeUUID, UUID equipmentUUID, LocalDateTime assignDate) {
        equipmentService.signEmployeesEquipment(user, employeeUUID,equipmentUUID,assignDate);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/signEquipmentsToEmployees")
    public ResponseEntity<String> signEmployeesToEquipments(
            @AuthenticationPrincipal User user,
            @RequestBody SignEquipmentRequest request) {

        equipmentService.signEmployeesEquipments(user, request.getEmployeeUUID(), request.getEquipmentUUID(), request.getAssignDate());

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/removeEquipmentFromEmployee")
    public ResponseEntity<String> removeEquipmentFromEmployee(@AuthenticationPrincipal User user, UUID employeeEquipmentUUID) {
        equipmentService.removeEquipmentFromEmployee(user,employeeEquipmentUUID);
        return ResponseEntity.ok().build();
        }
    @PatchMapping("/unSetEquipmentFromEmployee")
    public ResponseEntity<String> unSetEquipmentFromEmployee(@AuthenticationPrincipal User user, UUID employeeEquipmentUUID,UUID employeeUUID) {
        equipmentService.unSetEquipmentFromEmployee(user,employeeEquipmentUUID,employeeUUID) ;
        return ResponseEntity.ok().build();
    }

}
