package fr.orsys.ama.tp2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ListClientActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client);
        // ClientAdapter sert à creer une ligne
        ClientAdapter clientAdapter = new ClientAdapter(this);
        setListAdapter(clientAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // envoyer un message à l'activité DetailsClientActivity
        Log.i(AddClientActivity.TAG, "position = "+position);
        Intent callDetailsClient = new Intent(this, DetailsClientActivity.class);
        callDetailsClient.putExtra(DetailsClientActivity.CLIENT_ID, position);
        startActivity(callDetailsClient);
    }
}
