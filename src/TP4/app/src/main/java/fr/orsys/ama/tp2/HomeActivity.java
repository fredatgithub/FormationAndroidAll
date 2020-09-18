package fr.orsys.ama.tp2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import static fr.orsys.ama.tp2.AddClientActivity.*;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button addClientButton = findViewById(R.id.bAddClient);
        Button listerClientsButton = findViewById(R.id.bListerClients);
        addClientButton.setOnClickListener(this);
        listerClientsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bAddClient:
                Log.i(TAG, "Add client");   // changement d'activity
                Intent callAddClient = new Intent(this, AddClientActivity.class);
                startActivity(callAddClient);
                break;
            case R.id.bListerClients:
                Log.i(TAG, "Lister les clients");
                Intent callListClients = new Intent(this, ListClientActivity.class);
                startActivity(callListClients);
                break;
        }
    }
}