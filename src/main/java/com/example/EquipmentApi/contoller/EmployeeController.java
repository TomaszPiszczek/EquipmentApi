package com.example.EquipmentApi.contoller;

import com.example.EquipmentApi.dto.EmployeeDTO;
import com.example.EquipmentApi.dto.EmployeeTrainingDTO;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.repository.employee.EmployeeRepository;
import com.example.EquipmentApi.service.EmployeeService;
import com.example.EquipmentApi.service.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    EmployeeService employeeService;
    EmployeeRepository employeeRepository;
    TrainingService trainingService;
    @GetMapping("/getEmployees")
    ResponseEntity<Set<EmployeeDTO>> getEmployees(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(employeeService.getEmployeeDTO(user));
    }

    @GetMapping("/getEmployeeService")
    ResponseEntity<Set<EmployeeTrainingDTO>> getEmployeeService(@AuthenticationPrincipal User user, UUID EmployeeUUID) {
        return ResponseEntity.ok(employeeService.getEmployeeTrainingDTO(EmployeeUUID,user));
    }
    @PostMapping("/addEmployee")
    ResponseEntity<String> addEmployee(@AuthenticationPrincipal User user, String name,String surname){
        employeeService.addEmployee(user,name,surname);
        return ResponseEntity.ok("Employee added");
    }
    //todo add USER for employee leak protection
    @PostMapping("/signEmployeeToTraining")
    public ResponseEntity<String> signEmployeeToTraining(User user,UUID employeeUUID, UUID trainingUUID, LocalDateTime trainigDate, LocalDateTime expireDate){

        trainingService.signEmployeeToTraining(user,employeeUUID,trainingUUID,trainigDate,expireDate);
        return ResponseEntity.ok("Employee signed to new training");
    }

    @PostMapping("/signEmployeesToTraining")
    public ResponseEntity<String> signEmployeesToTraining(User user,Set<UUID> employeeUUID, UUID trainingUUID, LocalDateTime trainigDate, LocalDateTime expireDate){

        trainingService.signEmployeesToTraining(employeeUUID,trainingUUID,trainigDate,expireDate);
        return ResponseEntity.ok("Employee signed to new training");
    }


}
