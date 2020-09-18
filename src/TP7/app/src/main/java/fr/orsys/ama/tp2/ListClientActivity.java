package fr.orsys.ama.tp2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import fr.orsys.ama.tp2.model.Client;
import fr.orsys.ama.tp2.persistence.ClientDAO;


public class ListClientActivity extends AppCompatActivity implements ListClientFragment.OnClientSelectedListener{
    protected ClientDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client);
        dao = new ClientDAO(this);
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
            case R.id.action_sync:
                syncClient();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addClient() {
        Intent callAddClient = new Intent(this, AddClientActivity.class);
        startActivity(callAddClient);
    }

    /**
     * Creer une tache asynchrone:
     *  - onPreExecute: faire une notification
     *  - doInBackgroung: appel REST: HttpURLCOnnection
     *  - onPostExecute: notifier le broadcast receiver
     */
    public void syncClient() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // Requete REST http://ama-gestion-clients.appspot.com/rest/client GET
                URL urlService = null;
                try {
                    urlService = new URL("http://ama-gestion-clients.appspot.com/rest/client");
                    HttpURLConnection connection = (HttpURLConnection) urlService.openConnection();
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(input);
                    BufferedReader reader = new BufferedReader(isr);
                    String jsonLine = reader.readLine();
                    Log.i(AddClientActivity.TAG, jsonLine);
                    JSONArray array = new JSONArray(jsonLine);
                    for (int i=0; i<array.length(); i++) {
                        JSONObject jsonClient = (JSONObject) array.get(i);
                        Client client = JSONUtils.parse(jsonClient);
                        dao.addClient(client);
                    }
                    Intent ajoutIntent = new Intent( ListClientFragment.AJOUT_CLIENT);
                    sendBroadcast(ajoutIntent);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.execute();
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
