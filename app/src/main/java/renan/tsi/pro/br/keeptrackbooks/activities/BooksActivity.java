package renan.tsi.pro.br.keeptrackbooks.activities;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.adapters.BookAdapter;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteBookDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;
import renan.tsi.pro.br.keeptrackbooks.services.ServiceBook;

public class BooksActivity extends MainActivity {

    protected int idCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        changeToMain();
        changeToNewBook();
        setListView();
    }

    protected void setAutoCompleteForCategory() {
        final ArrayAdapter<Category> catAdapter = new ArrayAdapter<Category>(this,
                android.R.layout.simple_dropdown_item_1line, getCategories());

        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.selectEditCategory);

        textView.setAdapter(catAdapter);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setIdForCategory(catAdapter.getItem(position).getId());
            }
        });
    }

    protected ArrayList<Category> getCategories() {
        ArrayList<Category> c = new ArrayList<Category>();

        for(Category category : Category.all(getApplicationContext())){
            c.add(category);
        }

        return  c;
    }

    protected void setIdForCategory(int id) {
        this.idCategory = id;
    }

    protected  int getIdCategory() { return this.idCategory; }

    private void sendBookIdWhenChangeActivity(ListAdapter bookAdapter, int position) {
        Bundle params = new Bundle();
        params.putLong("id", bookAdapter.getItemId(position));
        Intent intent = new Intent(getBaseContext(), EditBookActivity.class);
        intent.putExtras(params);
        startActivity(intent);
    }

    private void setListView() {
        ListView lv = (ListView) findViewById(R.id.listViewBooks);

        final ListAdapter bookAdapter = new BookAdapter(
                (ArrayList<Book>) Book.all(getApplicationContext()), getLayoutInflater());
        lv.setAdapter(bookAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendBookIdWhenChangeActivity(bookAdapter, position);
            }
        });
    }

    private void changeToNewBook() {
        ImageButton newBookBtn = (ImageButton) findViewById(R.id.newBookBtn);

        newBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), NewBookActivity.class);
            }
        });
    }

}
