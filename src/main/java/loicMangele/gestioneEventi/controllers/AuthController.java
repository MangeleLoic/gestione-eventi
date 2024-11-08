package loicMangele.gestioneEventi.controllers;

import loicMangele.gestioneEventi.entities.Utente;
import loicMangele.gestioneEventi.exceptions.BadRequestException;
import loicMangele.gestioneEventi.payloads.NewUtenteDTO;
import loicMangele.gestioneEventi.payloads.UtenteLoginDTO;
import loicMangele.gestioneEventi.servicies.AuthService;
import loicMangele.gestioneEventi.servicies.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public UtenteLoginDTO login(@RequestBody UtenteLoginDTO body) {

        String token = this.authService.checkCredentialsAndGenerateToken(body);
        String email = body.email();
        return new UtenteLoginDTO(email, token);
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente save(@RequestBody @Validated NewUtenteDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.utenteService.saveUtente(body);
    }
}
