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
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private  String password;
    @Enumerated(EnumType.STRING)
    private TipoUtente TIPO;


    public Utente(String email, String fullname, String password, TipoUtente TIPO, String username) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.TIPO = TIPO;
        this.username = username;
    }
}
