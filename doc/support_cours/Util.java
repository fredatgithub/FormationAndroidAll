package fr.orsys.gestionclient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrateur on 20/08/2015.
 */
public abstract class Util {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static String formatDate(Date date){
        return sdf.format(date);
    }

    public static Date parserDate(String date) throws ParseException {
        return sdf.parse(date);
    }
}
