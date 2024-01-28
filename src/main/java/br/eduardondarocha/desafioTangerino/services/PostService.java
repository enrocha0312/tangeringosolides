package br.eduardondarocha.desafioTangerino.services;

import br.eduardondarocha.desafioTangerino.entities.Post;
import br.eduardondarocha.desafioTangerino.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post adicionar(Post post){
        post.setId(null);
        return postRepository.save(post);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }
}
