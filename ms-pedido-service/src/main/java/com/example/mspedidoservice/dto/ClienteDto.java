package com.example.mspedidoservice.dto;

import lombok.Data;

@Data
public class ClienteDto {
    private String direccion;
    private String telefono;
    private String nombre;
    private String dni;
    private Integer id;
    private String correo;
}
