package com.example.EquipmentApi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Tomasz Piszczek",
                        email = "Piszczek.Tomek09@gmail.com"
                ),
                description = "Api for equipmentManagementApp",
                title = "EquipmentApi",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "https://equipmentapi2.azurewebsites.net"
                )
        }
)
public class SwaggerConfig {
}