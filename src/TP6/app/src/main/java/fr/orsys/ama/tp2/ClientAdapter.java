package fr.orsys.ama.tp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import fr.orsys.ama.tp2.model.Client;
import fr.orsys.ama.tp2.model.ClientSet;
import fr.orsys.ama.tp2.model.Sexe;

/**
 * Chaque Client correspond à une ligne del'interface graphique
 */
public class ClientAdapter extends ArrayAdapter<Client> {

    public ClientAdapter(@NonNull Context context) {
        super(context, 0, ClientSet.INSTANCE.getContenu());
    }

    /**
     * la View retournée est la ligne qui correspond à un client
     * Elle sera affichée dans la ListView
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // cas particulier de la 1ere ligne
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            convertView = inflater.inflate(R.layout.row_client_layout, parent, false);
        }
        // remplir la ligne numero position avec une ImageView et un TextView
        ImageView imageView = convertView.findViewById(R.id.iClientImage);
        TextView textView = convertView.findViewById(R.id.tclientLabel);
        Client leClient = ClientSet.INSTANCE.getContenu().get(position);
        if (leClient.getSexe() == Sexe.MALE)
            imageView.setImageResource(R.drawable.man);
        else
            imageView.setImageResource(R.drawable.woman);
        textView.setText(leClient.getFirstName() + " " + leClient.getLastName());
        return convertView;
    }
}
