package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.adapters.BookAdapter;
import renan.tsi.pro.br.keeptrackbooks.adapters.StatusAdapter;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Status;

public class BooksActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        changeToMain();
        changeToNewBook();

        ListView lv = (ListView) findViewById(R.id.booksListView);

        ListAdapter bookAdapter = new BookAdapter(
                (ArrayList<Book>) Book.all(getApplicationContext()), getLayoutInflater());
        lv.setAdapter(bookAdapter);
    }

    private void changeToNewBook() {
        Button newBookBtn = (Button) findViewById(R.id.newBookBtn);

        newBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), NewBookActivity.class);
            }
        });
    }


}
