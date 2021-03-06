package fr.orsys.ama.tp2;

import android.app.Activity;
import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ListClientFragment extends ListFragment {
    public static final String AJOUT_CLIENT = "AJOUT_CLIENT";
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            adapter.notifyDataSetChanged();
        }
    };

    private OnClientSelectedListener listener;
    private ClientAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ClientAdapter(getActivity());
        setListAdapter(adapter);
        IntentFilter filter = new IntentFilter(AJOUT_CLIENT);
        this.getActivity().registerReceiver(receiver, filter);
    }

    public interface OnClientSelectedListener{
        public void onClientSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnClientSelectedListener){
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
