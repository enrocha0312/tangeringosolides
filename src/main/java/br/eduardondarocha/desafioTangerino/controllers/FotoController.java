package br.eduardondarocha.desafioTangerino.controllers;

import br.eduardondarocha.desafioTangerino.entities.Foto;
import br.eduardondarocha.desafioTangerino.services.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tangerinoapi/fotos")
public class FotoController {
    @Autowired
    private FotoService fotoService;
    public ResponseEntity<Foto> adicionarFoto(@RequestBody Foto foto){
        return new ResponseEntity<>(fotoService.adicionar(foto), HttpStatus.CREATED);
    }
}
