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
import renan.tsi.pro.br.keeptrackbooks.models.Category;

/**
 * Created by renan on 21/11/17.
 */

public class CategoryAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Category> categories;

    public CategoryAdapter(ArrayList<Category> categories, LayoutInflater inflater){
        this.inflater = inflater;
        this.categories = categories;
    }

    public void add(Category c){
        categories.add(c);
        notifyDataSetChanged();
        Log.d("PDM",">>"+categories.size());
    }

    public void remove(int index){
        categories.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return categories.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("PDM","VIEW"+categories.size());
        Category c = categories.get(i);

        View v = inflater.inflate(R.layout.adapter_category_layout, null);

        ((TextView)v.findViewById(R.id.categoryText)).setText(c.toString());

        return v;
    }
}
