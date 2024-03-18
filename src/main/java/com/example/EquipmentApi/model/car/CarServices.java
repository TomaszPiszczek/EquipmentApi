package com.example.EquipmentApi.model.car;

import com.example.EquipmentApi.model.car.Car;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "car_services")
public class CarServices {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "car_service_id")
    private UUID carServiceId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "service_date")
    private LocalDateTime serviceDate;

    @Column(name = "service_expire_date")
    private LocalDateTime serviceExpireDate;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;



}
