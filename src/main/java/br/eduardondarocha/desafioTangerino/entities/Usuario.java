package br.eduardondarocha.desafioTangerino.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name = "generator_usuario", sequenceName = "sequence_usuario", initialValue = 1, allocationSize = 1)
public class Usuario {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "generator_usuario")
    private Integer id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String senha;
    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Album> fotos;
}
