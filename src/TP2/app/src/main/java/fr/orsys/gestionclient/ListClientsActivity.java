package fr.orsys.gestionclient;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import fr.orsys.gestionclient.model.Client;


public class ListClientsActivity extends ListActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListAdapter adapter =
                new ClientAdapter(this, Client.getClients());
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, DetailsClientActivity.class);
        intent.putExtra(DetailsClientActivity.CLIENT_ID_EXTRA, position);
        startActivity(intent);
    }
}
