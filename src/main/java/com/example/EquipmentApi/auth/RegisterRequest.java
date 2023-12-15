package com.example.EquipmentApi.auth;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterRequest {
    @NonNull
    @Length(min = 3)
    private String username;
    @NonNull
    @Length(min = 3)
    private String email;
    @NonNull
    @Length(min = 6)
    private String password;
}
