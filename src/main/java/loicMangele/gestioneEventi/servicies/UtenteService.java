package loicMangele.gestioneEventi.servicies;

import loicMangele.gestioneEventi.entities.Utente;
import loicMangele.gestioneEventi.exceptions.BadRequestException;
import loicMangele.gestioneEventi.exceptions.NotFoundException;
import loicMangele.gestioneEventi.payloads.NewUtenteDTO;
import loicMangele.gestioneEventi.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;

    public Utente saveUtente(NewUtenteDTO body) {

        this.utenteRepository.findByEmail(body.email()).ifPresent(
                utente -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );


        Utente newUtente = new Utente();
        newUtente.setFullname(body.fullName());
        newUtente.setEmail(body.email());
        newUtente.setPassword(body.password());

        return this.utenteRepository.save(newUtente);
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato"));
    }

    public Utente findById(Long id) {
        return this.utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("L'utente con ID " + id + " non è stato trovato"));
    }

    public void deleteUtente(Long id) {
        Utente user = findById(id);
        this.utenteRepository.delete(user);
    }

    public Page<Utente> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utenteRepository.findAll(pageable);
    }

}
