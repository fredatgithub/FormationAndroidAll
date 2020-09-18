package fr.orsys.gestionclient.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrateur on 19/08/2015.
 */
public class Client {

    private static List<Client> clients;

    static{
        clients = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Sexe s = i % 3 == 0 ? Sexe.MAN : Sexe.WOMAN;
            Client c = new Client("Nom" + i, "Prenom " + i, s,
                    "nom"+ i + ".prenom"+i+"@orsys.fr", 20 + i,
                    "Débutant", i % 3 == 0);
            clients.add(c);
        }
    }

    public static List<Client> getClients() {
        return clients;
    }

    public enum Sexe{
        MAN,WOMAN
    }

    protected String lastname;
    protected String firstname;
    private Sexe sexe;
    private String email;
    private int age;
    private String level;
    private boolean active;

    public Client(String lastname, String firstname,
                  Sexe sexe, String email, int age,
                  String level, boolean active) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.sexe = sexe;
        this.email = email;
        this.age = age;
        this.level = level;
        this.active = active;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return lastname + " " + firstname;
    }
}
