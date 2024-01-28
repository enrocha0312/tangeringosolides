package br.eduardondarocha.desafioTangerino.services;

import br.eduardondarocha.desafioTangerino.entities.Album;
import br.eduardondarocha.desafioTangerino.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public Album adicionar(Album album){
        album.setId(null);
        return albumRepository.save(album);
    }
}
