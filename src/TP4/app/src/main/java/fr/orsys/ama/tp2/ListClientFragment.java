package fr.orsys.ama.tp2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import fr.orsys.ama.tp2.model.ClientSet;

public class ListClientFragment extends ListFragment {
    private OnClientSelectedListener listener;
    private ClientAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ClientAdapter(getActivity());
        setListAdapter(adapter);
        //
    }

    public interface OnClientSelectedListener{
        public void onClientSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof OnClientSelectedListener){
            listener = (OnClientSelectedListener)activity;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(listener != null){
            listener.onClientSelected(position);
        }
    }

    private void addClient() {
        Intent intent = new Intent(getActivity(),AddClientActivity.class);
        startActivity(intent);
    }
}
