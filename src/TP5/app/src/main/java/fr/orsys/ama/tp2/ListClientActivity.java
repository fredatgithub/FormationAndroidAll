package fr.orsys.ama.tp2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class ListClientActivity extends AppCompatActivity implements ListClientFragment.OnClientSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "Portrait mode", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "landscape mode", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                addClient();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addClient() {
        Intent callAddClient = new Intent(this, AddClientActivity.class);
        startActivity(callAddClient);
    }

    @Override
    public void onClientSelected(int position) {

        if(findViewById(R.id.detailFragment) != null){ // landscape
            DetailsClientFragment detailsFragment =
                    (DetailsClientFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
            detailsFragment.updateClient(position);
        }else{                      // Portrait
            Intent intent = new Intent(this, DetailsClientActivity.class);
            intent.putExtra(DetailsClientActivity.CLIENT_ID, position);
            startActivity(intent);
        }
    }
}
