package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import renan.tsi.pro.br.keeptrackbooks.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeScreens();
    }

    protected void changeToMain() {
        Button backBtn = (Button) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), MainActivity.class);
            }
        });
    }

    protected void changeActivity(Context context, Class nameClass) {
        Intent intent = new Intent(context, nameClass);
        startActivity(intent);
    }

    private void changeScreens() {
        changeToBooks();
        changeToCategories();
        changeToStatus();
    }

    private void changeToBooks() {
        Button booksBtn = (Button) findViewById(R.id.booksBtn);

        booksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), BooksActivity.class);
            }
        });
    }

    private void changeToCategories() {
        Button categoriesBtn = (Button) findViewById(R.id.categoriesBtn);

        categoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), CategoriesActivity.class);
            }
        });
    }

    private void changeToStatus() {
        Button statusBtn = (Button) findViewById(R.id.statusBtn);

        statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), StatusActivity.class);
            }
        });
    }


}
