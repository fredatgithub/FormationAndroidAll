package fr.orsys.ama.tp2.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Pour accumuler tous les clients
 */
public enum ClientSet {
    INSTANCE;

    @Getter
    protected List<Client> contenu;

    ClientSet() {
        contenu = new ArrayList<>();
    }

}
