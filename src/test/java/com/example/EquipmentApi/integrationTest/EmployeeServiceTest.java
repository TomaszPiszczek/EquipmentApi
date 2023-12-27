package com.example.EquipmentApi.integrationTest;

import com.example.EquipmentApi.dto.EmployeeDTO;
import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.user.User;
import com.example.EquipmentApi.repository.employee.EmployeeRepository;
import com.example.EquipmentApi.repository.employee.EmployeeTrainingRepository;
import com.example.EquipmentApi.repository.employee.TrainingRepository;
import com.example.EquipmentApi.repository.user.UserRepository;
import com.example.EquipmentApi.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    TrainingRepository trainingRepository;
    @Mock
    EmployeeTrainingRepository employeeTrainingRepository;
    @Autowired
    UserRepository userRepository;

    EmployeeService employeeService;
    @BeforeEach
    public void setUp(){
        employeeService =  new EmployeeService(employeeRepository, employeeTrainingRepository, trainingRepository);
    }

    @Transactional
    @Test
    public void getEmployeeDTOShouldReturnDtoTest(){
        //given
        Employee employee1 = Employee.builder()
                .name("name")
                .employeeId(UUID.fromString("71f0ecdc-a0de-11ee-8c90-0242ac120002"))
                .surname("surname")
                .build();
        Employee employee2 = Employee.builder()
                .name("name")
                .employeeId(UUID.fromString("71f0ecdc-a0de-11ee-8c90-0242ac120003"))
                .surname("surname")
                .build();
        User user = User.builder().build();
        //when
        when(employeeRepository.findEmployeesByUser(user)).thenReturn(Set.of(employee1,employee2));
        Set<EmployeeDTO> employeeDTOSet = employeeService.getEmployeeDTO(user);
        EmployeeDTO EmployeeDTO = employeeDTOSet.iterator().next();

        //then

        assertEquals(2,employeeDTOSet.size());
        assertEquals("name",EmployeeDTO.name());
        assertEquals("surname",EmployeeDTO.surname());
        assertEquals(UUID.fromString("71f0ecdc-a0de-11ee-8c90-0242ac120002"),EmployeeDTO.uuid());


    }




}
