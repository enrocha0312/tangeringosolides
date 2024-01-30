package br.eduardondarocha.desafioTangerino.shared.service;

import br.eduardondarocha.desafioTangerino.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
    private String token;
    private Usuario usuario;
}
