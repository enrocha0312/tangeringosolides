package br.eduardondarocha.desafioTangerino.repositories;


import br.eduardondarocha.desafioTangerino.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<List<Post>> findbyUsuario(String username);
}
