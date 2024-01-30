package br.eduardondarocha.desafioTangerino.services;

import br.eduardondarocha.desafioTangerino.entities.Foto;
import br.eduardondarocha.desafioTangerino.repositories.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {
    @Autowired
    private FotoRepository fotoRepository;

    public Foto adicionar(Foto foto){
        foto.setId(null);
        return fotoRepository.save(foto);
    }
    public void delete(Integer id){
        fotoRepository.deleteById(id);
    }
}
