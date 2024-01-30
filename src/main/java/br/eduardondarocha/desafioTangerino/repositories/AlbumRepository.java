package br.eduardondarocha.desafioTangerino.repositories;

import br.eduardondarocha.desafioTangerino.entities.Album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    @Query(value = "Select * from Albuns a inner join Usuarios u on a.usuario_id = u.id",
    nativeQuery = true)
    Optional<List<Album>> findbyUsuario_Id(String username);
}
