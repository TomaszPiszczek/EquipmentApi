package com.example.EquipmentApi.contoller;

import com.example.EquipmentApi.dto.TrainingDTO;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.service.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/training")
public class TrainingController {
    TrainingService trainingService;
    @PostMapping("/createTraining")
    public ResponseEntity<String> createTraining(String name, String description,@AuthenticationPrincipal User user){
        trainingService.createTraining(name,description,user);

     return ResponseEntity.ok().build();
    }
    @DeleteMapping("/removeTrainingFromEmployee")
    public ResponseEntity<String> removeTrainingFromEmployee(UUID employeeTrainingUUID,@AuthenticationPrincipal User user){
        trainingService.removeTrainingFromEmployee(user,employeeTrainingUUID);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/removeTraining")
    public ResponseEntity<String> removeTraining(UUID trainingUUID,@AuthenticationPrincipal User user){
        trainingService.removeTraining(user,trainingUUID);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getTrainings")
    public ResponseEntity<List<TrainingDTO>> getTrainings(@AuthenticationPrincipal User user){
       return ResponseEntity.ok(trainingService.getTrainings(user));
    }



}
