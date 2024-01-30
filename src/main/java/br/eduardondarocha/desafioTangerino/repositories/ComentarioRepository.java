package br.eduardondarocha.desafioTangerino.repositories;

import br.eduardondarocha.desafioTangerino.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    @Query(value = "Select * from Comentarios c inner join Usuarios u on c.usuario_id = u.id",
            nativeQuery = true)
    Optional<List<Comentario>> findbyUsuario_Id(String username);
}
