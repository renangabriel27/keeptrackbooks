package renan.tsi.pro.br.keeptrackbooks.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Status;

/**
 * Created by renan on 21/11/17.
 */

public class BookAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Book> books;

    public BookAdapter(ArrayList<Book> books, LayoutInflater inflater){
        this.inflater = inflater;
        this.books = books;
    }

    public void add(Book b){
        books.add(b);
        notifyDataSetChanged();
        Log.d("PDM",">>"+books.size());
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
        return books.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("PDM","VIEW"+books.size());
        Book b = books.get(i);

        View v = inflater.inflate(R.layout.adapter_book_layout, null);

        ((TextView)v.findViewById(R.id.adapter_text1)).setText(b.getTitle());
        ((TextView)v.findViewById(R.id.adapter_text2)).setText(b.getCategory().getName());
        ((TextView)v.findViewById(R.id.adapter_text3)).setText(String.valueOf(b.getNumberPages()) + " pages");

        return v;
    }
}
