package br.eduardondarocha.desafioTangerino.security.filters;

import br.eduardondarocha.desafioTangerino.entities.Usuario;
import br.eduardondarocha.desafioTangerino.repositories.UsuarioRepository;
import br.eduardondarocha.desafioTangerino.security.services.AuthenticationService;
import br.eduardondarocha.desafioTangerino.security.services.JWTTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JWTTokenService jwtTokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = getToken(request);
        if(token!=null){
            Optional<Integer> id = jwtTokenService.obtainIdFromUsuario(token);
            if(id.isPresent()) {
                Usuario usuario = usuarioRepository.findById(id.get()).get();
                UserDetails userDetails = authenticationService.loadUserByUsername(usuario.getUsername());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
    private String getToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(!StringUtils.hasText(token)){
            return null;
        }
        return token.substring(7);
    }
}
