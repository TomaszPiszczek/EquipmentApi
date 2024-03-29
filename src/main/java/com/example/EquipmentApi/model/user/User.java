package com.example.EquipmentApi.model.user;

import com.example.EquipmentApi.model.car.Car;
import com.example.EquipmentApi.model.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "user_account")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @NotBlank
    @Length(min =3)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Length(min =6)
    @Column(name = "password")
    private String password;
    @NotBlank
    @Pattern(regexp = "^(.+)@(.+)$")
    @Column(name = "email",unique = true)
    private String email;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Equipment> tools;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Training> trainings;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Employee> employees;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Car> cars;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
