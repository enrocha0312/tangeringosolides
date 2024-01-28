package br.eduardondarocha.desafioTangerino.repositories;

import br.eduardondarocha.desafioTangerino.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
