package com.example.EquipmentApi.auth;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterRequest {

    private String username;

    private String email;

    private String password;
}
