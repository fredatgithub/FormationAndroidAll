package fr.orsys.ama.tp2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;

import fr.orsys.ama.tp2.model.Client;
import fr.orsys.ama.tp2.model.ClientSet;
import fr.orsys.ama.tp2.persistence.ClientDAO;

public class DetailsClientFragment extends Fragment {

    private TextView nameTextView;
    private TextView firstnameTextView;
    private TextView sexeTextView;
    private TextView emailTextView;
    private TextView birthdayTextView;
    private TextView levelTextView;
    private TextView activeTextView;
    private int position;
    private ClientDAO dao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new ClientDAO(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // /!\ tres important
        View view = inflater.inflate(R.layout.fragment_details_client, container);

        nameTextView = (TextView)view.findViewById(R.id.tnameValue);
        firstnameTextView = (TextView)view.findViewById(R.id.tfirstnameValue);
        sexeTextView = (TextView)view.findViewById(R.id.tsexeValue);
        emailTextView = (TextView)view.findViewById(R.id.temailValue);
        birthdayTextView = (TextView)view.findViewById(R.id.tageValue);
        levelTextView = (TextView)view.findViewById(R.id.tlevelValue);
        activeTextView = (TextView)view.findViewById(R.id.tactiveValue);
        return view;
    }

    public void updateClient(int position) {

        this.position = position;
        // Client client = ClientSet.INSTANCE.getContenu().get(position);
        Client client = dao.getClient(position);
        nameTextView.setText(client.getLastName());
        firstnameTextView.setText(client.getFirstName());
        sexeTextView.setText(client.getSexe().toString());
        emailTextView.setText(client.getEmail());
        SimpleDateFormat sdf = new SimpleDateFormat(("dd/MM/yyyy"));
        birthdayTextView.setText(sdf.format(client.getNaissance()));
        levelTextView.setText(client.getLevel().name());
        activeTextView.setText(client.getActive());
    }
    // TODO
}
