package fr.orsys.gestionclient;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.orsys.gestionclient.model.Client;

/**
 * Created by Administrateur on 19/08/2015.
 */
public class ClientAdapter extends ArrayAdapter<Client> {

    private static final String TAG = ClientAdapter.class.getSimpleName();

    public ClientAdapter(Context context, List<Client> clients) {
        super(context,0, clients);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            LayoutInflater inflater =
                    (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.layout_client_adapter, parent, false);
        }

        ImageView clientImageView = (ImageView)convertView.findViewById(R.id.clientImageView);
        TextView clientTextView = (TextView)convertView.findViewById(R.id.clientTextView);

        Client client = getItem(position);

        clientImageView.setImageResource(
                client.getSexe() == Client.Sexe.MAN ? R.drawable.man : R.drawable.woman);
        clientTextView.setText(client.getLastname() + " " + client.getFirstname());

        return convertView;
    }
}
