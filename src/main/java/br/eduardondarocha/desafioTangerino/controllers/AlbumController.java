package br.eduardondarocha.desafioTangerino.controllers;

import br.eduardondarocha.desafioTangerino.entities.Album;
import br.eduardondarocha.desafioTangerino.entities.Usuario;
import br.eduardondarocha.desafioTangerino.repositories.AlbumRepository;
import br.eduardondarocha.desafioTangerino.repositories.UsuarioRepository;
import br.eduardondarocha.desafioTangerino.security.services.JWTTokenService;
import br.eduardondarocha.desafioTangerino.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("tangerinoapi/albuns")
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private JWTTokenService jwtTokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Album>> findAll(){
        return new ResponseEntity<>(albumService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Album> add(@RequestBody Album album){
        Album response = albumService.adicionar(album);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id, @RequestHeader Map<String, String> headers){
        var token = headers.get("authorization").substring(7);
        Optional<Integer> optionalId = jwtTokenService.obtainIdFromUsuario(token);
        Album albumASerApagado = albumRepository.findById(id).get();
        Usuario usuarioQueDesejaApagarOPost = usuarioRepository.findById(optionalId.get()).get();
        if(usuarioQueDesejaApagarOPost.getUsername().equals(albumASerApagado.getUsuario().getUsername())){
            albumService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/usuario")
    public ResponseEntity<List<Album>> findAlbunsByUser(@RequestParam String username){
        if (albumService.findAlbumByUsername(username) == null) return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(albumService.findAlbumByUsername(username), HttpStatus.OK);
    }
}
