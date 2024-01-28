package br.eduardondarocha.desafioTangerino.repositories;

import br.eduardondarocha.desafioTangerino.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    Optional<List<Comentario>> findbyUsuario(String username);
}
