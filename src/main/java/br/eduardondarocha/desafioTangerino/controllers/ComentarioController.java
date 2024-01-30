package br.eduardondarocha.desafioTangerino.controllers;

import br.eduardondarocha.desafioTangerino.entities.Album;
import br.eduardondarocha.desafioTangerino.entities.Comentario;
import br.eduardondarocha.desafioTangerino.entities.Usuario;
import br.eduardondarocha.desafioTangerino.repositories.ComentarioRepository;
import br.eduardondarocha.desafioTangerino.repositories.UsuarioRepository;
import br.eduardondarocha.desafioTangerino.security.services.JWTTokenService;
import br.eduardondarocha.desafioTangerino.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("tangerinoapi/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private JWTTokenService jwtTokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping
    public ResponseEntity<List<Comentario>> findAll(){
        return new ResponseEntity<>(comentarioService
                .findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Comentario> add(@RequestBody Comentario comentario){
        Comentario response = comentarioService.adicionarComentario(comentario);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id, @RequestHeader Map<String, String> headers){
        var token = headers.get("authorization").substring(7);
        Optional<Integer> optionalId = jwtTokenService.obtainIdFromUsuario(token);
        Comentario comentarioASerApagado = comentarioRepository.findById(id).get();
        Usuario usuarioQueDesejaApagarOPost = usuarioRepository.findById(optionalId.get()).get();
        if(usuarioQueDesejaApagarOPost.getUsername().equals(comentarioASerApagado.getUsuario().getUsername())){
            comentarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/usuario")
    public ResponseEntity<List<Comentario>> findAlbunsByUser(@RequestParam String username){
        if (comentarioService.findComentarioByUser(username) == null) return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(comentarioService.findComentarioByUser(username), HttpStatus.OK);
    }
}
