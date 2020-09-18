package fr.orsys.ama.tp2;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ListClientActivity extends Activity implements ListClientFragment.OnClientSelectedListener{
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
    public void onClientSelected(int position) {

        if(findViewById(R.id.detailFragment) != null){
            DetailsClientFragment detailsFragment =
                    (DetailsClientFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
            detailsFragment.updateClient(position);
        }else{
            Intent intent = new Intent(this, DetailsClientActivity.class);
            intent.putExtra(DetailsClientActivity.CLIENT_ID, position);
            startActivity(intent);
        }
    }
}
