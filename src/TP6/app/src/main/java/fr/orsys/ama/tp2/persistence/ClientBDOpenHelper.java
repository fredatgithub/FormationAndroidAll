package fr.orsys.ama.tp2.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ClientBDOpenHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "client.db";
    public static final String CLIENT_TABLE_NAME = "Client";

    public ClientBDOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "create table "+CLIENT_TABLE_NAME+" ("
                + ClientColumns._ID +" integer primary key autoincrement, "
                + ClientColumns.LASTNAME + " text, "
                + ClientColumns.FIRSTNAME + " text, "
                + ClientColumns.GENDER + " text, "
                + ClientColumns.EMAIL + " text, "
                + ClientColumns.BIRTHDATE + " text, "
                + ClientColumns.LEVEL + " text, "
                + ClientColumns.ACTIVE + " text);";
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // en cas de mis à jour de tout le schema
    }

}
