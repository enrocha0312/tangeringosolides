package br.eduardondarocha.desafioTangerino.repositories;


import br.eduardondarocha.desafioTangerino.entities.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Integer> {
}
