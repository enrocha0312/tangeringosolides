package br.eduardondarocha.desafioTangerino.shared.controller;

import br.eduardondarocha.desafioTangerino.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
    private String token;
    private Usuario usuario;
}
