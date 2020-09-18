package fr.orsys.gestionclient.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrateur on 20/08/2015.
 */
public class ClientDBOpenHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gestionclients.db";

    public static final String CLIENT_TABLE_NAME = "Client";
    public ClientDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + CLIENT_TABLE_NAME +  "( " + ClientColumns._ID + " integer primary key autoincrement," +
                ClientColumns.LASTNAME + " text, " + ClientColumns.FIRSTNAME + " text," +
                ClientColumns.EMAIL + " text, " + ClientColumns.GENDER + " text," +
                ClientColumns.BIRTHDATE + " text, " + ClientColumns.LEVEL + " text," +
                ClientColumns.ACTIVE + " integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
