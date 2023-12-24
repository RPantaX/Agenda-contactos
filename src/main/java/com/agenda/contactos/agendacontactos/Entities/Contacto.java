package com.agenda.contactos.agendacontactos.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contactos")
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Debe ingresar su nombre")
    private String nombre;
    @NotEmpty(message = "Debe ingresar su email")
    @Email
    private String email;
    @NotBlank(message = "Debe ingresar su número de celular")
    private String celular;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @Past //solo fechas en el pasado
    @NotNull(message = "Debe ingresar su fecha de nacimiento")
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;

    @PrePersist //asigna por defecto la fecha de hoy
    private void asignarFechaRegistro(){
        fechaRegistro= LocalDateTime.now();
    }

}
