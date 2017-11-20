package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import renan.tsi.pro.br.keeptrackbooks.R;

public class BooksActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        changeToMain();
        changeToNewBook();
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
