package br.eduardondarocha.desafioTangerino.repositories;

import br.eduardondarocha.desafioTangerino.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUserName(String username);
}
