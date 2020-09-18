package fr.orsys.gestionclient;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.orsys.gestionclient.model.Client;


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

    /**
     *
     * @param json
     * @return
     * @throws JSONException
     */
    public static List<Client> parse(JSONArray json) throws JSONException {
		List<Client> clients = new ArrayList<Client>();
		for (int i = 0; i < json.length(); i++) {
			JSONObject element = (JSONObject)json.get(i);
            Client client = parse(element);
			clients.add(client);
		}
		return clients;
	}

    /**
     *
     * @param json
     * @return
     * @throws JSONException
     */
    public static Client parse(JSONObject json) throws JSONException {
        Client client = new Client();
//        client.setId(json.getInt(ID));
        client.setLastname(json.getString(LASTNAME));
        client.setFirstname(json.getString(FIRSTNAME));
        client.setEmail(json.getString(EMAIL));
        Client.Gender gender = json.getString(GENDER).equals("HOMME") ? Client.Gender.MAN : Client.Gender.WOMAN;
        client.setLevel(json.getString(LEVEL));
        client.setActive(json.getBoolean(ACTIVE));
        client.setGender(Client.Gender.MAN);
        Date dateNaissance = null;
        try {
            dateNaissance = sdf.parse(json.getString(BIRTHDATE));
        } catch (ParseException e) {
        }
        client.setBirthdate(dateNaissance);
        return client;
    }

    /**
     *
     * @param client
     * @return
     * @throws JSONException
     */
    public static JSONObject format(Client client) throws JSONException {
        JSONObject json = new JSONObject();
        json.put(LASTNAME, client.getLastname());
        json.put(FIRSTNAME, client.getFirstname());
        json.put(EMAIL, client.getEmail());
        json.put(GENDER, client.getGender().toString());
        json.put(LEVEL, client.getLevel());
        json.put(BIRTHDATE, sdf.format(client.getBirthdate()));
        json.put(ACTIVE, client.isActive());
        return json;
    }

}
