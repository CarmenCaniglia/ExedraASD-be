package carmencaniglia.exedraAsd.security;

import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.UnauthorizedException;
import carmencaniglia.exedraAsd.services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UtenteService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Per favore metti il token nell'Authorization header");
        } else {
            String accessToken = authHeader.substring(7);
            // 2. Verifichiamo se il token è scaduto o se è stato manipolato (verifica signature)
            jwtTools.verifyToken(accessToken);

            // 3. Se è tutto OK

            // 3.1 Cerco l'utente a DB (l'id sta all'interno del token...)
            String id = jwtTools.extractIdFromToken(accessToken); // L'id è nel token quindi devo estrarlo da lì
            Utente user = utenteService.findById(Long.parseLong(id));

            // 3.2 Informo Spring Security che l'utente è autenticato (se non faccio questo passaggio continuerò ad avere 403 come risposte)
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 3.3 Possiamo proseguire al prossimo elemento della chain (e prima o poi si arriverà al controller)
            filterChain.doFilter(request, response); // va al prossimo elemento della catena
            // 4. Se non è OK --> 401
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Questo metodo serve per specificare quando il filtro NON deve entrare in azione
        // Ad esempio tutte le richieste al controller /auth non devono essere controllate dal filtro

        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

}
