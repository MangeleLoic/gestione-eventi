package loicMangele.gestioneEventi.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Utente {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private  Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "fullname")
    private String fullname;
    @Enumerated(EnumType.STRING)
    private TipoUtente TIPO;
}
