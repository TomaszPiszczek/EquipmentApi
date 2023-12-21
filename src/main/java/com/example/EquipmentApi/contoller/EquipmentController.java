package com.example.EquipmentApi.contoller;

import com.example.EquipmentApi.dto.EmployeeEquipmentDTO;
import com.example.EquipmentApi.dto.EquipmentDTO;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.service.EquipmentService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<String> createEquipment(byte[] image, BigDecimal price, String name, String description, @AuthenticationPrincipal User user) {
        equipmentService.createEquipment(image, price, name, description, user);
        return ResponseEntity.ok("Equipment created");
    }

    @DeleteMapping("/removeEquipment")
    public ResponseEntity<String> removeEquipment(UUID equipmentUUID, @AuthenticationPrincipal User user) {
        equipmentService.removeEquipment(equipmentUUID, user);
        return ResponseEntity.ok("Equipment deleted");
    }

    @GetMapping("/getEquipment")
    public ResponseEntity<Set<EquipmentDTO>> getEquipment(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(equipmentService.getEquipment(user));

    }

    @GetMapping("/getEmployeeEquipment")
    public ResponseEntity<List<EmployeeEquipmentDTO>> getEmployeeEquipment(@AuthenticationPrincipal User user, UUID employeeUUID) {
        return ResponseEntity.ok(equipmentService.getEmployeeEquipment(user, employeeUUID));

    }

    @PostMapping("/signEquipmentToEmployee")
    public ResponseEntity<String> signEmployeeToEquipment(@AuthenticationPrincipal User user, UUID employeeUUID, UUID equipmentUUID, LocalDateTime assignDate) {
        equipmentService.signEmployeeEquipment(user, employeeUUID,equipmentUUID,assignDate);
        return ResponseEntity.ok("Equipment signed to employee");

    }

    @PostMapping("/signEquipmentToEmployees")
    public ResponseEntity<String> signEmployeesToEquipment(@AuthenticationPrincipal User user,@RequestBody Set<UUID> employeeUUID, UUID equipmentUUID, LocalDateTime assignDate) {
        equipmentService.signEmployeesEquipment(user, employeeUUID,equipmentUUID,assignDate);
        return ResponseEntity.ok("Equipment signed to employee");

    }
    @DeleteMapping("/removeEquipmentFromEmployee")
    public ResponseEntity<String> removeEquipmentFromEmployee(@AuthenticationPrincipal User user, UUID employeeEquipmentUUID,UUID employeeUUID) {
        equipmentService.removeEquipmentFromEmployee(user,employeeEquipmentUUID,employeeUUID);
        return ResponseEntity.ok("Equipment deleted from employee");
        }
    @PatchMapping("/unSetEquipmentFromEmployee")
    public ResponseEntity<String> unSetEquipmentFromEmployee(@AuthenticationPrincipal User user, UUID employeeEquipmentUUID,UUID employeeUUID) {
        equipmentService.unSetEquipmentFromEmployee(user,employeeEquipmentUUID,employeeUUID) ;
        return ResponseEntity.ok("Equipment un set from employee");
    }

}
