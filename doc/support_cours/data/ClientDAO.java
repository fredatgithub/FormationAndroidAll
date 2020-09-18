package fr.orsys.gestionclient.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.orsys.gestionclient.Util;
import fr.orsys.gestionclient.model.Client;
import fr.orsys.gestionclient.model.Gender;

/**
 * Created by Administrateur on 20/08/2015.
 */
public class ClientDAO {

    private final Context context;
    private final ClientDBOpenHelper helper;

    public ClientDAO(Context context) {
        this.context = context;
        helper = new ClientDBOpenHelper(context);
    }

    public Cursor getClientCursor(){
        SQLiteDatabase database = helper.getReadableDatabase();
       return database.query(ClientDBOpenHelper.CLIENT_TABLE_NAME,ClientColumns.ALL,null,null,null,null,ClientColumns.LASTNAME);
    }

    /**
     * Add a client
     *
     * @param client
     */
    public void addClient(Client client){
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ClientColumns.LASTNAME, client.getLastname());
        cv.put(ClientColumns.FIRSTNAME, client.getFirstname());
        cv.put(ClientColumns.EMAIL, client.getEmail());
        cv.put(ClientColumns.GENDER, client.getGender().toString());
        cv.put(ClientColumns.BIRTHDATE, Util.formatDate(client.getBirthday()));
        cv.put(ClientColumns.LEVEL, client.getLevel());
        cv.put(ClientColumns.ACTIVE, client.isActive() ? 1 : 0);
        database.insert(ClientDBOpenHelper.CLIENT_TABLE_NAME, null, cv);

    }

    public Cursor getClientsCursor(){
        SQLiteDatabase database = helper.getReadableDatabase();

        return	database.query(ClientDBOpenHelper.CLIENT_TABLE_NAME,ClientColumns.ALL,
                null, null, null, null, ClientColumns.LASTNAME);
    }

    public Cursor getClientsCursor(String like){
        SQLiteDatabase database = helper.getReadableDatabase();
        String where = ClientColumns.LASTNAME + " like '%" + like + "%'";
        String[] whereArgs = new String[]{like};
        return	database.query(ClientDBOpenHelper.CLIENT_TABLE_NAME,ClientColumns.ALL,
                where, null, null, null, ClientColumns.LASTNAME);
    }

    /**
     *
     * @param cursor
     * @return
     */
    public Client getClient(Cursor cursor){
        Client client = new Client();
        client.setId(cursor.getInt(0));
        client.setLastname(cursor.getString(1));
        client.setFirstname(cursor.getString(2));
        client.setEmail(cursor.getString(3));
        Gender gender = Gender.valueOf(cursor.getString(4));
        client.setGender(gender);
        try {
            Date birthday = Util.parserDate(cursor.getString(5));
            client.setBirthday(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        client.setLevel(cursor.getString(6));
        client.setActive(cursor.getInt(7) == 1);

        return client;
    }

    /**
     *
     * @return
     */
    public List<Client> getClients(){
        SQLiteDatabase database = helper.getWritableDatabase();
        List<Client> clients = new ArrayList<Client>();
        Cursor cursor =
                database.query(ClientDBOpenHelper.CLIENT_TABLE_NAME,ClientColumns.ALL,
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
        database.delete(ClientDBOpenHelper.CLIENT_TABLE_NAME, where, whereArgs);
        database.close();
    }

    public Client getClient(int id) {
        SQLiteDatabase database = helper.getWritableDatabase();
        String where = ClientColumns._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        Cursor cursor =	database.query(ClientDBOpenHelper.CLIENT_TABLE_NAME,ClientColumns.ALL,
                where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Client client = getClient(cursor);
        database.close();
        return client;
    }



}
