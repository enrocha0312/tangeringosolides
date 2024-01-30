package br.eduardondarocha.desafioTangerino.services;

import br.eduardondarocha.desafioTangerino.entities.Album;
import br.eduardondarocha.desafioTangerino.repositories.AlbumRepository;
import br.eduardondarocha.desafioTangerino.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Album adicionar(Album album){
        album.setId(null);
        return albumRepository.save(album);
    }
    public List<Album> findAll(){
        return albumRepository.findAll();
    }
    public void delete(Integer id){
        albumRepository.deleteById(id);
    }
    public List<Album> findAlbumByUsername(String username){
        if(!usuarioRepository.findByUsername(username).isPresent())return null;
        return albumRepository.findbyUsuario_Id(username).get();
    }
}
