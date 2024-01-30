package br.eduardondarocha.desafioTangerino.repositories;


import br.eduardondarocha.desafioTangerino.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "Select * from Posts p inner join Usuarios u on p.usuario_id = u.id",
            nativeQuery = true)
    Optional<List<Post>> findbyUsuario_Id(String username);
}
