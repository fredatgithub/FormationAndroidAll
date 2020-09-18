package fr.orsys.gestionclient;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import fr.orsys.gestionclient.model.Client;


public class DetailsClientActivity extends ActionBarActivity {

    public static final String CLIENT_ID_EXTRA = "CLIENT_ID_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_client);
        Intent intent = getIntent();
        int id = intent.getIntExtra(CLIENT_ID_EXTRA, 0);
        Client client = Client.getClients().get(id);

        TextView nameTextView = (TextView)findViewById(R.id.nameTextView);
        nameTextView.setText(client.getLastname());
        TextView firstnameTextView = (TextView)findViewById(R.id.firstnameTextView);
        firstnameTextView.setText(client.getFirstname());
        TextView sexeTextView = (TextView)findViewById(R.id.sexeTextView);
        sexeTextView.setText(client.getSexe().toString());
        TextView emailTextView = (TextView)findViewById(R.id.emailTextView);
        emailTextView.setText(client.getEmail());
        TextView ageTextView = (TextView)findViewById(R.id.ageTextView);
        ageTextView.setText(String.valueOf(client.getAge()));
        TextView levelTextView = (TextView)findViewById(R.id.levelTextView);
        levelTextView.setText(client.getLevel());
        TextView activeTextView = (TextView)findViewById(R.id.activeTextView);
        activeTextView.setText(client.isActive() ? getString(R.string.yes) : getString(R.string.no));
    }

}
