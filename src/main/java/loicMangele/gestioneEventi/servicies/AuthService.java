package loicMangele.gestioneEventi.servicies;

import loicMangele.gestioneEventi.entities.Utente;
import loicMangele.gestioneEventi.exceptions.UnauthorizedException;
import loicMangele.gestioneEventi.payloads.UtenteLoginDTO;
import loicMangele.gestioneEventi.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWT jwt;

    public String checkCredentialsAndGenerateToken(UtenteLoginDTO body) {

        Utente found = this.utenteService.findByEmail(body.email());

        if (found.getPassword().equals(body.password())) {

            String accessToken = jwt.createToken(found);

            return accessToken;
        } else {

            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
