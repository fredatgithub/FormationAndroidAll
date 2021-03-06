package fr.orsys.ama.tp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;

import fr.orsys.ama.tp2.model.Client;
import fr.orsys.ama.tp2.model.ClientSet;

public class DetailsClientActivity extends Activity implements View.OnClickListener {

    protected static final String CLIENT_ID = "CLIENT_ID_POSITION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_client);
        // recuperation du numéro
        Intent call = this.getIntent();
        int position = call.getIntExtra(CLIENT_ID, 0);
        Client client = ClientSet.INSTANCE.getContenu().get(position);
        // remplir l'interface graphique
        TextView name = findViewById(R.id.tnameValue);
        name.setText(client.getLastName());
        TextView firstname = findViewById(R.id.tfirstnameValue);
        firstname.setText(client.getFirstName());
        TextView sexe = findViewById(R.id.tsexeValue);
        sexe.setText(client.getSexe().name());
        TextView email = findViewById(R.id.temailValue);
        email.setText(client.getEmail());
        TextView age = findViewById(R.id.tageValue);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        age.setText(sdf.format(client.getNaissance()));
        TextView level = findViewById(R.id.tlevelValue);
        level.setText(client.getLevel().name());
        TextView active = findViewById(R.id.tactiveValue);
        active.setText(client.getActive());
        Button close = findViewById(R.id.bclose);
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
