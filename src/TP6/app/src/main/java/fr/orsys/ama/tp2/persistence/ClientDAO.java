package fr.orsys.ama.tp2.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.orsys.ama.tp2.model.Client;
import fr.orsys.ama.tp2.model.Level;
import fr.orsys.ama.tp2.model.Sexe;;

/**
 * C'est un CRUDS sur la table Client
 * Elle contient 5 methodes pour:
 *   - creer un client
 *   - lire un client
 *   - mettre a jour un client
 *   - supprimer un client
 *   - avoir un cursor pour lire tous les clients
 */
public class ClientDAO {
    protected ClientBDOpenHelper helper;
    protected Context context;

    public ClientDAO(Context context) {
        this.context = context;
        helper = new ClientBDOpenHelper(context);
    }

    public Cursor getAllClients() {
        SQLiteDatabase database = helper.getReadableDatabase();
        // rechercher tous les client triés par nom
        return database.query(ClientBDOpenHelper.CLIENT_TABLE_NAME, ClientColumns.ALL, null, null, null, null, ClientColumns.LASTNAME);
    }

    public void addClient(Client client) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ClientColumns.LASTNAME, client.getLastName());
        cv.put(ClientColumns.FIRSTNAME, client.getFirstName());
        cv.put(ClientColumns.GENDER, client.getSexe().name());
        cv.put(ClientColumns.EMAIL, client.getEmail());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        cv.put(ClientColumns.BIRTHDATE, sdf.format(client.getNaissance()));
        cv.put(ClientColumns.LEVEL, client.getLevel().name());
        cv.put(ClientColumns.ACTIVE, client.getActive());
        database.insert(ClientBDOpenHelper.CLIENT_TABLE_NAME, null, cv);
    }

    public Cursor getClientsCursor(){
        SQLiteDatabase database = helper.getReadableDatabase();

        return	database.query(ClientBDOpenHelper.CLIENT_TABLE_NAME,ClientColumns.ALL,
                null, null, null, null, ClientColumns.LASTNAME);
    }

    public Cursor getClientsCursor(String like){
        SQLiteDatabase database = helper.getReadableDatabase();
        String where = ClientColumns.LASTNAME + " like '%" + like + "%'";
        String[] whereArgs = new String[]{like};
        return	database.query(ClientBDOpenHelper.CLIENT_TABLE_NAME,ClientColumns.ALL,
                where, null, null, null, ClientColumns.LASTNAME);
    }

    public Client getClient(Cursor cursor){
        Client client = new Client();
    //    client.setId(cursor.getInt(0));
        client.setLastName(cursor.getString(1));
        client.setFirstName(cursor.getString(2));
        client.setEmail(cursor.getString(3));
        Sexe gender = Sexe.valueOf(cursor.getString(4));
        client.setSexe(gender);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date birthday = sdf.parse(cursor.getString(5));
            client.setNaissance(birthday);
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.setLevel(Level.valueOf(cursor.getString(6)));
        client.setActive(cursor.getString(7));

        return client;
    }

    public List<Client> getClients(){
        SQLiteDatabase database = helper.getWritableDatabase();
        List<Client> clients = new ArrayList<Client>();
        Cursor cursor =
                database.query(ClientBDOpenHelper.CLIENT_TABLE_NAME,ClientColumns.ALL,
                        null, null, null, null, ClientColumns.LASTNAME);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Client client = getClient(cursor);
            clients.add(client);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return clients;
    }

    public void deleteClient(int id){
        SQLiteDatabase database = helper.getWritableDatabase();
        String where = ClientColumns._ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        database.delete(ClientBDOpenHelper.CLIENT_TABLE_NAME, where, whereArgs);
        database.close();
    }

    public Client getClient(int id) {
        SQLiteDatabase database = helper.getWritableDatabase();
        String where = ClientColumns._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        Cursor cursor =	database.query(ClientBDOpenHelper.CLIENT_TABLE_NAME,ClientColumns.ALL,
                where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Client client = getClient(cursor);
        database.close();
        return client;
    }
}
