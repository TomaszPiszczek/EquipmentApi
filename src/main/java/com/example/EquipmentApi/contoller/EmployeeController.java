package com.example.EquipmentApi.contoller;

import com.example.EquipmentApi.dto.EmployeeDTO;
import com.example.EquipmentApi.dto.EmployeeTrainingDTO;
import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.repository.employee.EmployeeRepository;
import com.example.EquipmentApi.service.EmployeeService;
import com.example.EquipmentApi.service.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getEmployee")
    ResponseEntity<Employee> getEmployees(@AuthenticationPrincipal User user, UUID employeeUUID){
        return ResponseEntity.ok(employeeService.getEmployee(user,employeeUUID));
    }
    @GetMapping("/getEmployeeTrainings")
    ResponseEntity<Set<EmployeeTrainingDTO>> getEmployeeService(@AuthenticationPrincipal User user, UUID EmployeeUUID) {
        return ResponseEntity.ok(employeeService.getEmployeeTrainingDTO(EmployeeUUID,user));
    }
    @PostMapping("/addEmployee")
    ResponseEntity<String> addEmployee(@AuthenticationPrincipal User user, String name,String surname){
        employeeService.addEmployee(user,name,surname);
        return ResponseEntity.ok("Employee added");
    }
    @PostMapping("/signEmployeeToTraining")
    public ResponseEntity<String> signEmployeeToTraining(@AuthenticationPrincipal User user,UUID employeeUUID, UUID trainingUUID, LocalDateTime trainingDate, LocalDateTime expireDate){

        trainingService.signEmployeeToTraining(user,employeeUUID,trainingUUID,trainingDate,expireDate);
        return ResponseEntity.ok("Employee signed to new training");
    }

    @PostMapping("/signEmployeesToTraining")
    public ResponseEntity<String> signEmployeesToTraining(@AuthenticationPrincipal User user, @RequestBody Set<UUID> employeeUUID, UUID trainingUUID, LocalDateTime trainingDate, LocalDateTime expireDate){

        trainingService.signEmployeesToTraining(user,employeeUUID,trainingUUID,trainingDate,expireDate);
        return ResponseEntity.ok("Employee signed to new training");
    }


}
