package com.example.EquipmentApi.model.car;

import com.example.EquipmentApi.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Data
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "car_id")
    private UUID carId;

    @Column(name = "brand")
    private String brand;

    @Column(name = "image")
    private byte[] imageData;

    @Column(name = "registration_number")
    private String registrationNumber;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "car")
    private Set<CarServices> carServices;
}
