package br.eduardondarocha.desafioTangerino.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fotos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String linkFoto;
    private Usuario usuario;
}
