package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteBookDatabase;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteCategoryDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;

import static android.util.Log.*;

public class NewBookActivity extends MainActivity {

    private int idCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        changeToMain();

        saveBook();

        final ArrayAdapter<Category> catAdapter = new ArrayAdapter<Category>(this,
                android.R.layout.simple_dropdown_item_1line, getCategories());

        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.selectCategory);

        textView.setAdapter(catAdapter);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               setIdForCategory(catAdapter.getItem(position).getId());
            }
        });

    }

    private ArrayList<Category> getCategories() {
        ArrayList<Category> c = new ArrayList<Category>();

        for(Category category : Category.all(getApplicationContext())){
            c.add(category);
        }

        return  c;
    }

    private void setIdForCategory(int id) {
        this.idCategory = id;
    }


    protected void saveBook() {
        Button createBookBtn = (Button) findViewById(R.id.createBookBtn);

        createBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameBook = (EditText) findViewById(R.id.nameBook);
                EditText numberPages = (EditText) findViewById(R.id.numberPages);
                AutoCompleteTextView textView = (AutoCompleteTextView)
                        findViewById(R.id.selectCategory);


                if (fieldIsEmpty(nameBook) || fieldIsEmpty(numberPages)){

                    Toast.makeText(getApplicationContext(), "The field(s) can't be empty!", Toast.LENGTH_LONG).show();

                } else {
                    String bookName = nameBook.getText().toString();
                    int bookNumberPages = 0;

                    try {
                        bookNumberPages = Integer.parseInt(numberPages.getText().toString());
                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                    Log.d("IDD", ""  + idCategory);
                    Book book = new Book(bookName, bookNumberPages, idCategory);

                    SQLiteBookDatabase db = new SQLiteBookDatabase(getApplicationContext());
                    db.create(book);
                    Toast.makeText(getBaseContext(), "Book was created with success!", Toast.LENGTH_LONG).show();
                    changeActivity(getBaseContext(), BooksActivity.class);
                }
            }
        });
    }
}
