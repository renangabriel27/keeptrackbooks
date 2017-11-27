package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.adapters.BookAdapter;
import renan.tsi.pro.br.keeptrackbooks.adapters.CategoryAdapter;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteCategoryDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;

public class BooksActivity extends MainActivity {

    private Category category = new Category();
    private ArrayAdapter<Category> catAdapter;
    private ListAdapter bookAdapter;
    private AutoCompleteTextView selectCategory;
    private SQLiteCategoryDatabase dbCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        changeToMain();
        changeToNewBook();
        setListView();
    }

    protected void setAutoCompleteForCategory() {
        catAdapter = new ArrayAdapter<Category>(this,
                android.R.layout.simple_dropdown_item_1line, getCategories());

        selectCategory = (AutoCompleteTextView)
                findViewById(R.id.selectEditCategory);

        selectCategory.setAdapter(catAdapter);
        selectCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setCategory(catAdapter, position);
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

    protected  void setCategory(ArrayAdapter<Category> categoryAdapter, int position) {
        int categoryId = categoryAdapter.getItem(position).getId();
        dbCategory = new SQLiteCategoryDatabase(getApplicationContext());
        this.category = dbCategory.find(categoryId);
    }

    protected Category getCategory() { return this.category; }

    private void sendBookIdWhenChangeActivity(ListAdapter bookAdapter, int position) {
        Bundle params = new Bundle();
        params.putLong("id", bookAdapter.getItemId(position));
        Intent intent = new Intent(getBaseContext(), EditBookActivity.class);
        intent.putExtras(params);
        startActivity(intent);
    }

    private void setListView() {
        ListView lv = (ListView) findViewById(R.id.listViewBooks);

        bookAdapter = new BookAdapter(
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
        FloatingActionButton newBookBtn = (FloatingActionButton) findViewById(R.id.newCategoryBtn);

        newBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), NewBookActivity.class);
            }
        });
    }

}
