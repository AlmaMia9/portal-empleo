package com.sise.portalempleo.pyload.requests.RequestUpdates;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UsuarioRequestUpdate {
    
    @NotNull
    @Positive
    private Integer idTipoUsuario;

    @NotNull
    @NotBlank
    private String nombreUsuario;

    @NotNull
    @NotBlank
    @Email
    private String email;
}
