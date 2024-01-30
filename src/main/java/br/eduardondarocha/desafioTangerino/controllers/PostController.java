package br.eduardondarocha.desafioTangerino.controllers;

import br.eduardondarocha.desafioTangerino.entities.Album;
import br.eduardondarocha.desafioTangerino.entities.Post;
import br.eduardondarocha.desafioTangerino.entities.Usuario;
import br.eduardondarocha.desafioTangerino.repositories.PostRepository;
import br.eduardondarocha.desafioTangerino.repositories.UsuarioRepository;
import br.eduardondarocha.desafioTangerino.security.services.JWTTokenService;
import br.eduardondarocha.desafioTangerino.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("tangerinoapi/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private JWTTokenService jwtTokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        return new ResponseEntity<>(postService
                .findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Post> add(@RequestBody Post post){
        Post response = postService.adicionar(post);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id, @RequestHeader Map<String, String> headers){
        var token = headers.get("authorization").substring(7);
        Optional<Integer> optionalId = jwtTokenService.obtainIdFromUsuario(token);
        Post postASerApagado = postRepository.findById(id).get();
        Usuario usuarioQueDesejaApagarOPost = usuarioRepository.findById(optionalId.get()).get();
        if(usuarioQueDesejaApagarOPost.getUsername().equals(postASerApagado.getUsuario().getUsername())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/usuario")
    public ResponseEntity<List<Post>> findAlbunsByUser(@RequestParam String username){
        if (postService.findPostByUsername(username) == null) return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(postService.findPostByUsername(username), HttpStatus.OK);
    }
}
