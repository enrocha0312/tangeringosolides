package br.eduardondarocha.desafioTangerino.repositories;

import br.eduardondarocha.desafioTangerino.entities.Album;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Optional<List<Album>> findbyUsuario(String username);
}
