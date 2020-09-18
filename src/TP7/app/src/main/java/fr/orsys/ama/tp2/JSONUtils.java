package fr.orsys.ama.tp2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.orsys.ama.tp2.model.Client;
import fr.orsys.ama.tp2.model.Level;
import fr.orsys.ama.tp2.model.Sexe;

public class JSONUtils {

    public static final String JSON_CONTENT_TYPE = "application/json; charset=UTF-8";

    public static final String ID = "id";
    public static final String LASTNAME = "nom";
    public static final String FIRSTNAME = "prenom";
    public static final String EMAIL = "email";
    public static final String GENDER = "sexe";
    public static final String LEVEL = "niveau";
    public static final String ACTIVE = "actif";
    public static final String BIRTHDATE = "dateNaissance";

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static List<Client> parse(JSONArray json) throws JSONException {
        List<Client> clients = new ArrayList<Client>();
        for (int i = 0; i < json.length(); i++) {
            JSONObject element = (JSONObject)json.get(i);
            Client client = parse(element);
            clients.add(client);
        }
        return clients;
    }

    public static Client parse(JSONObject json) throws JSONException {
        Client client = new Client();
//        client.setId(json.getInt(ID));
        client.setLastName(json.getString(LASTNAME));
        client.setFirstName(json.getString(FIRSTNAME));
        client.setEmail(json.getString(EMAIL));
        Sexe gender = json.getString(GENDER).equals("HOMME") ? Sexe.MALE : Sexe.FEMALE;
        client.setSexe(gender);
        client.setLevel(parseLevel(json.getString(LEVEL)));
        if (json.getBoolean(ACTIVE))
            client.setActive("YES");
        else
            client.setActive("NO");
        Date dateNaissance = null;
        try {
            dateNaissance = sdf.parse(json.getString(BIRTHDATE));
        } catch (ParseException e) {
        }
        client.setNaissance(dateNaissance);
        return client;
    }

    private static Level parseLevel(String label) {
        switch (label) {
            case "Débutant":
                return Level.BEGINNER;
            case "Moyen":
                return Level.INTERMEDIATE;
            case "Avancé":
                return Level.ADVANCED;
            default:
                return Level.BEGINNER;
        }
    }

    public static JSONObject format(Client client) throws JSONException {
        JSONObject json = new JSONObject();
        json.put(LASTNAME, client.getLastName());
        json.put(FIRSTNAME, client.getFirstName());
        json.put(EMAIL, client.getEmail());
        json.put(GENDER, client.getSexe().name());
        json.put(LEVEL, client.getLevel());
        json.put(BIRTHDATE, sdf.format(client.getNaissance()));
        json.put(ACTIVE, client.getActive());
        return json;
    }

}