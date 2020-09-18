package fr.orsys.gestionclient;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void addClient(View view) {
        Intent intent = new Intent(this, AddClientActivity.class);
        startActivity(intent);
    }

    public void listClients(View view) {
        Intent intent = new Intent(this, ListClientsActivity.class);
        startActivity(intent);
    }
}
