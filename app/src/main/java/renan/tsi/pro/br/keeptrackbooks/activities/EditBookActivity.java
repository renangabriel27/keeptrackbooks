package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.adapters.BookAdapter;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteBookDatabase;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteCategoryDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Book;

public class EditBookActivity extends MainActivity {

    private long id;
    private Book bookEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        changeToBooks();
        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if(params!=null){
            this.id = params.getLong("id");

            Log.i("Params", "id:"+params.getLong("id"));
        }

        SQLiteBookDatabase db = new SQLiteBookDatabase(getApplicationContext());
        this.bookEdit = db.find((int) id);

        deleteBook();
    }

    protected void changeToBooks() {
        Button backBooksBtn = (Button) findViewById(R.id.backBooksBtn);

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

}
