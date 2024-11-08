package loicMangele.gestioneEventi.tools;

import loicMangele.gestioneEventi.entities.Utente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWT {
    @Value("${jwt.secret}")
    private String secret;


    }
}
