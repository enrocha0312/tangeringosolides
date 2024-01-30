package br.eduardondarocha.desafioTangerino.services;

import br.eduardondarocha.desafioTangerino.entities.Post;
import br.eduardondarocha.desafioTangerino.repositories.PostRepository;
import br.eduardondarocha.desafioTangerino.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public Post adicionar(Post post){
        post.setId(null);
        return postRepository.save(post);
    }
    public List<Post> findAll(){
        return postRepository.findAll();
    }
    public void delete(Integer id){
        postRepository.deleteById(id);
    }
    public List<Post> findPostByUsername(String username){
        if(!usuarioRepository.findByUsername(username).isPresent())return null;
        return postRepository.findbyUsuario_Id(username).get();
    }
}
