package br.eduardondarocha.desafioTangerino.repositories;


import br.eduardondarocha.desafioTangerino.entities.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FotoRepository extends JpaRepository<Foto, Integer> {
    Optional<List<Foto>> findbyUsuario(String username);
}
