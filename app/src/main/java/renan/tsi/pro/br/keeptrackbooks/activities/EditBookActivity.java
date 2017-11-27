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
import android.widget.ImageButton;
import android.widget.Toast;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteBookDatabase;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteStatusDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;

public class EditBookActivity extends BooksActivity {

    private long id;
    private Book bookEdit;
    private ArrayAdapter<Category> catAdapter;
    private SQLiteBookDatabase dbBook;
    private SQLiteStatusDatabase dbStatus;
    private EditText editBookTitle;
    private EditText editNumberPages;
    private AutoCompleteTextView editCategoryBook;
    private ImageButton backBooksBtn;
    private Button deleteBookBtn;
    private int bookNumberPages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        changeToBooks();

        setFieldsForBook();
        deleteBook();
        updateBook();
    }

    private void setFieldsForBook() {
        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if(params!=null){
            id = params.getLong("id");
        }

        dbBook = new SQLiteBookDatabase(getApplicationContext());
        bookEdit = dbBook.find((int) id);

        editBookTitle = (EditText) findViewById(R.id.editBookTitle);
        editBookTitle.setText(bookEdit.getTitle());

        editNumberPages = (EditText) findViewById(R.id.editNumberPages);
        editNumberPages.setText(String.valueOf(bookEdit.getNumberPages()));

        editCategoryBook = (AutoCompleteTextView)
                findViewById(R.id.selectEditCategory);
        editCategoryBook.setText(bookEdit.getCategory().getName());

        catAdapter = new ArrayAdapter<Category>(this,
                android.R.layout.simple_dropdown_item_1line, getCategories());

        editCategoryBook.setAdapter(catAdapter);
        editCategoryBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               setCategory(catAdapter, position);
            }
        });
    }

    protected void changeToBooks() {
        backBooksBtn = (ImageButton) findViewById(R.id.backBtn);
        backBooksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), BooksActivity.class);
            }
        });
    }

    protected void deleteBook() {
        deleteBookBtn = (Button) findViewById(R.id.deleteBookBtn);

        deleteBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbBook = new SQLiteBookDatabase(getApplicationContext());
                dbStatus = new SQLiteStatusDatabase(getApplicationContext());

                if(dbStatus.hasBook(bookEdit.getId())) {
                    showMessage("Book can't deleted, because has relationships with status!");
                } else {
                    dbBook.delete(bookEdit);
                    showMessage("Book was deleted with success!");
                }

                changeActivity(getBaseContext(), BooksActivity.class);
            }
        });
    }

    protected void updateBook() {
        Button updateBookBtn = (Button) findViewById(R.id.updateBookBtn);

        updateBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldIsEmpty(editBookTitle) || fieldIsEmpty(editNumberPages) || fieldIsEmpty(editNumberPages) || fieldIsEmpty(editCategoryBook)) {
                    showMessageWhenFieldsEmpty();
                } else if(getCategory().getId() == 0) {
                    validAndUpdate(bookEdit.getCategory());
                } else {
                    validAndUpdate(getCategory());
                }
            }
        });
    }

    private void validAndUpdate(Category category) {
        String bookName = editBookTitle.getText().toString();

        try {
            bookNumberPages = Integer.parseInt(editNumberPages.getText().toString());
        } catch(Exception e) {
            e.printStackTrace();
        }

        Book book = new Book((int) id, bookName, bookNumberPages, category);

        dbBook = new SQLiteBookDatabase(getApplicationContext());
        dbBook.update(book);

        showSuccessUpdateMessage();
        changeActivity(getBaseContext(), BooksActivity.class);
    }

    private void showSuccessUpdateMessage() {
        showMessage("Book was updated with success!");
    }

}
