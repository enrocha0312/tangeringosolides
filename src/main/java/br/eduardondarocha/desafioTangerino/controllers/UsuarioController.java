package br.eduardondarocha.desafioTangerino.controllers;

import br.eduardondarocha.desafioTangerino.entities.Usuario;
import br.eduardondarocha.desafioTangerino.services.UsuarioService;
import br.eduardondarocha.desafioTangerino.shared.controller.LoginRequest;
import br.eduardondarocha.desafioTangerino.shared.controller.LoginResponse;
import br.eduardondarocha.desafioTangerino.shared.service.LoginDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("tangerinoapi/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario){
        Usuario user = usuarioService.adicionar(usuario);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest request){
        LoginDTO loginDTO = usuarioService.doLogin(request.getUsername(), request.getSenha());
        LoginResponse loginResponse = new ModelMapper().map(loginDTO, LoginResponse.class);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
