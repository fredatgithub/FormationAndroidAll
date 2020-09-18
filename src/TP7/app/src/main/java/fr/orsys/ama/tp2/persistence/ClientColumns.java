package fr.orsys.ama.tp2.persistence;

import android.provider.BaseColumns;

public class ClientColumns implements BaseColumns {
    public static final String LASTNAME = "lastname";
    public static final String FIRSTNAME = "firstname";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String BIRTHDATE = "birthdate";
    public static final String LEVEL = "level";
    public static final String ACTIVE = "active";

    public static final String[] ALL = {_ID, LASTNAME, FIRSTNAME, EMAIL, GENDER, BIRTHDATE, LEVEL, ACTIVE};
}
