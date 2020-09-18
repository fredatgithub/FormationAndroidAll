package fr.orsys.ama.tp2.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * POJO pour conserver les données
 */
@Getter @Setter
@ToString
@NoArgsConstructor
public class Client {
    protected String lastName;
    protected String firstName;
    protected Sexe sexe;
    protected String email;
    protected Date naissance;
    protected Level level;
    protected String active;

    public Client(String lastName, String firstName, Sexe sexe, String email, Date naissance, Level level, String active) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.sexe = sexe;
        this.email = email;
        this.naissance = naissance;
        this.level = level;
        this.active = active;
    }
}
