package br.eduardondarocha.desafioTangerino.services;

import br.eduardondarocha.desafioTangerino.entities.Comentario;
import br.eduardondarocha.desafioTangerino.repositories.ComentarioRepository;
import br.eduardondarocha.desafioTangerino.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public Comentario adicionarComentario(Comentario comentario){
        comentario.setId(null);
        Comentario c = comentarioRepository.save(comentario);
        return c;
    }
    public List<Comentario> findAll(){
        return comentarioRepository.findAll();
    }
    public void delete(Integer id){
        comentarioRepository.deleteById(id);
    }
    public List<Comentario> findComentarioByUser(String username){
        if(!usuarioRepository.findByUserName(username).isPresent())return null;
        return comentarioRepository.findbyUsuario(username).get();
    }
}
