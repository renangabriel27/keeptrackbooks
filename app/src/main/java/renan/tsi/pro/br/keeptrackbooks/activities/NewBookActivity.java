package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteBookDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;
import renan.tsi.pro.br.keeptrackbooks.services.ServiceBook;

public class NewBookActivity extends BooksActivity {

    private String bookName;
    private int bookNumberPages = 0;
    private EditText nameBook;
    private EditText numberPages;
    private AutoCompleteTextView category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        changeToMain();
        saveBook();
        setAutoCompleteForCategory();
    }

    private void createBook(EditText nameBook, EditText numberPages) {
        bookName = nameBook.getText().toString();

        try {
            bookNumberPages = Integer.parseInt(numberPages.getText().toString());
        } catch(Exception e) {
            e.printStackTrace();
        }

        Book book = new Book(bookName, bookNumberPages, getCategory());

        SQLiteBookDatabase db = new SQLiteBookDatabase(getApplicationContext());
        Object bookExists = db.findByTitle(bookName);

        if(bookExists == null) {
            db.create(book);
            initService();
            showSuccessMessage();
        } else {
            showMessage("The book has been created!");
        }

        changeActivity(getBaseContext(), BooksActivity.class);
    }

    private void initService() {
        Intent serviceIntent = new Intent(this, ServiceBook.class);
        startService(serviceIntent);
    }

    protected void saveBook() {
        final Button createBookBtn = (Button) findViewById(R.id.createBookBtn);

        createBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameBook = (EditText) findViewById(R.id.editBookTitle);
                numberPages = (EditText) findViewById(R.id.editNumberPages);
                category = (AutoCompleteTextView) findViewById(R.id.selectEditCategory);

                if (fieldIsEmpty(nameBook) || fieldIsEmpty(numberPages) || fieldIsEmpty(category)){
                    showMessageWhenFieldsEmpty();
                } else if(getCategory().getId() == 0) {
                    showMessage("Invalid category!");
                } else {
                    createBook(nameBook, numberPages);
                }
            }
        });
    }

    private void showSuccessMessage() {
        showMessage("Book was created with success!");
    }
}
