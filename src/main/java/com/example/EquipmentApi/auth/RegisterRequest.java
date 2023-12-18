package com.example.EquipmentApi.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterRequest {

    private String username;

    private String email;

    private String password;
}
