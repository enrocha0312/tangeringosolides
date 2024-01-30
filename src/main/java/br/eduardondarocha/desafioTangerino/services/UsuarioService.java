package br.eduardondarocha.desafioTangerino.services;

import br.eduardondarocha.desafioTangerino.entities.Usuario;
import br.eduardondarocha.desafioTangerino.repositories.UsuarioRepository;
import br.eduardondarocha.desafioTangerino.security.services.JWTTokenService;
import br.eduardondarocha.desafioTangerino.shared.service.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private static final String HEADER_PREFIX = "Bearer ";
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTTokenService jwtTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer id){
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByUsername(String username){
        return usuarioRepository.findByUserName(username);
    }
    public Usuario adicionar(Usuario usuario){
        usuario.setId(null);
        if(findByUsername(usuario.getUsername()).isPresent()){
            throw new InputMismatchException("Ja existe usuario cadastro com esse email");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }
    public LoginDTO doLogin(String username, String senha){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,senha);
        try{
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = HEADER_PREFIX + jwtTokenService.generateToken(authentication);
            Usuario usuario = usuarioRepository.findByUserName(username).get();
            return new LoginDTO(token, usuario);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
