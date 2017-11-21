package renan.tsi.pro.br.keeptrackbooks.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.models.Status;

/**
 * Created by renan on 20/11/17.
 */

public class StatusAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Status> status;

    public StatusAdapter(ArrayList<Status> status, LayoutInflater inflater){
        this.inflater = inflater;
        this.status = status;
    }

    public void add(Status s){
        status.add(s);
        notifyDataSetChanged();
        Log.d("PDM",">>"+status.size());
    }

    public void remove(int index){
        status.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return status.size();
    }

    @Override
    public Object getItem(int i) {
        return status.get(i);
    }


    @Override
    public long getItemId(int i) {
        return status.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("PDM","VIEW"+status.size());
        Status s = status.get(i);

        View v = inflater.inflate(R.layout.adapter_layout, null);

        ((TextView)v.findViewById(R.id.adapter_text1)).setText(s.toString());

        return v;
    }
}
