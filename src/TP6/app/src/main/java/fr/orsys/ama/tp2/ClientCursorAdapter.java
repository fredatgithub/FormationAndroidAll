package fr.orsys.ama.tp2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fr.orsys.ama.tp2.model.Client;
import fr.orsys.ama.tp2.model.Sexe;
import fr.orsys.ama.tp2.persistence.ClientDAO;

public class ClientCursorAdapter extends CursorAdapter {
    public ClientDAO dao;

    public ClientCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        dao = new ClientDAO(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.row_client_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView imageView = view.findViewById(R.id.iClientImage);
        TextView textView = view.findViewById(R.id.tclientLabel);
        Client leClient = dao.getClient(cursor);
        if (leClient.getSexe() == Sexe.MALE)
            imageView.setImageResource(R.drawable.man);
        else
            imageView.setImageResource(R.drawable.woman);
        textView.setText(leClient.getFirstName() + " " + leClient.getLastName());
    }
}
