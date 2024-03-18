package com.example.EquipmentApi;

import com.example.EquipmentApi.contoller.EmployeeController;
import com.example.EquipmentApi.model.employee.Employee;
import com.example.EquipmentApi.model.user.Equipment;
import com.example.EquipmentApi.model.user.Training;
import com.example.EquipmentApi.model.user.User;
import jakarta.transaction.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JPAUnitTestCase {

    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    @Test
    public void testLazyLoading() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        UUID employeeId = UUID.fromString("935ad5a2-34cb-4b27-8706-7d07fc283c84");


        User user = new User(UUID.randomUUID(),"name","password","email", null,null,null,null);

        Employee employee2 = new Employee(UUID.randomUUID(),"name","surname",user,null,null);
        Employee employee1 = new Employee(UUID.randomUUID(),"name","surname",user,null,null);
        Equipment equipment = new Equipment(UUID.randomUUID(),"name", BigDecimal.valueOf(30),"desc", LocalDateTime.now(),null,Set.of(employee1,employee2),user);
        Equipment equipment2 = new Equipment(UUID.randomUUID(),"name",BigDecimal.valueOf(30),"desc", LocalDateTime.now(),null,Set.of(employee1,employee2),user);
        Training training = new Training(UUID.randomUUID(),"NAME","desc",Set.of(employee1,employee2),user);
        employee1.setTools(Set.of(equipment,equipment2));
        employee2.setTools(Set.of(equipment,equipment2));
        employee1.setTrainings(Set.of(training));
        employee2.setTrainings(Set.of(training));
        user.setTools(Set.of(equipment));
        user.setTools(Set.of(equipment2));

        user.setTrainings(Set.of(training));
        user.setEmployees(Set.of(employee1,employee2));


        ResponseEntity<Employee> employee = employeeController.getEmployee(user,employeeId);

        entityManager.getTransaction().commit();
        entityManager.close();

        assert employee != null;

    }
}