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
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;

public class EditBookActivity extends BooksActivity {

    private long id;
    private Book bookEdit;

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
            this.id = params.getLong("id");

            Log.i("Params", "id:"+params.getLong("id"));
        }

        SQLiteBookDatabase db = new SQLiteBookDatabase(getApplicationContext());
        this.bookEdit = db.find((int) id);

        EditText editBookTitle = (EditText) findViewById(R.id.editBookTitle);
        editBookTitle.setText(bookEdit.getTitle());

        EditText editNumberPages = (EditText) findViewById(R.id.editNumberPages);
        editNumberPages.setText(String.valueOf(bookEdit.getNumberPages()));

        AutoCompleteTextView editCategoryBook = (AutoCompleteTextView)
                findViewById(R.id.selectEditCategory);

        editCategoryBook.setText(bookEdit.getCategory().getName());

        final ArrayAdapter<Category> catAdapter = new ArrayAdapter<Category>(this,
                android.R.layout.simple_dropdown_item_1line, getCategories());

        editCategoryBook.setAdapter(catAdapter);
        editCategoryBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setIdForCategory(catAdapter.getItem(position).getId());
            }
        });
    }

    protected void changeToBooks() {
        ImageButton backBooksBtn = (ImageButton) findViewById(R.id.backBtn);

        backBooksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), BooksActivity.class);
            }
        });
    }

    protected void deleteBook() {
        Button deleteBookBtn = (Button) findViewById(R.id.deleteBookBtn);

        deleteBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteBookDatabase db = new SQLiteBookDatabase(getApplicationContext());
                db.delete(bookEdit);
                Toast.makeText(getBaseContext(), "Book was deleted with success!", Toast.LENGTH_LONG).show();
                changeActivity(getBaseContext(), BooksActivity.class);
            }
        });
    }

    protected void updateBook() {
        Button updateBookBtn = (Button) findViewById(R.id.updateBookBtn);

        updateBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameBook = (EditText) findViewById(R.id.editBookTitle);
                EditText numberPages = (EditText) findViewById(R.id.editNumberPages);

                AutoCompleteTextView categoryBook = (AutoCompleteTextView)
                        findViewById(R.id.selectEditCategory);


                if (fieldIsEmpty(nameBook) || fieldIsEmpty(numberPages) || fieldIsEmpty(numberPages)){
                    Toast.makeText(getApplicationContext(), "The field can't be empty!", Toast.LENGTH_LONG).show();
                } else if(idCategory == 0) {
                    Toast.makeText(getApplicationContext(), "Invalid category!", Toast.LENGTH_LONG).show();
                    Log.d("TESTE", "" + idCategory);
                } else {
                    String bookName = nameBook.getText().toString();
                    int bookNumberPages = 0;

                    try {
                        bookNumberPages = Integer.parseInt(numberPages.getText().toString());
                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                    Log.d("IDD", ""  + idCategory);
                    Book book = new Book((int) id, bookName, bookNumberPages, idCategory);

                    SQLiteBookDatabase db = new SQLiteBookDatabase(getApplicationContext());
                    db.update(book);

                    Toast.makeText(getBaseContext(), "Book was updated with success!", Toast.LENGTH_LONG).show();
                    changeActivity(getBaseContext(), BooksActivity.class);
                }
            }
        });
    }

}
