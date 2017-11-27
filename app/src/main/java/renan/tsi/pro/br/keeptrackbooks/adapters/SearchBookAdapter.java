package renan.tsi.pro.br.keeptrackbooks.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.models.Book;

/**
 * Created by renan on 26/11/17.
 */

public class SearchBookAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<String> books;

    public SearchBookAdapter(ArrayList<String> books, LayoutInflater inflater){
        this.inflater = inflater;
        this.books = books;
    }

    public void add(String b){
        books.add(b);
        notifyDataSetChanged();
    }

    public void remove(int index){
        books.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        String b = books.get(i);

        View v = inflater.inflate(R.layout.adapter_search_book_layout, null);

        ((TextView)v.findViewById(R.id.bookText)).setText(b);

        return v;
    }
}
