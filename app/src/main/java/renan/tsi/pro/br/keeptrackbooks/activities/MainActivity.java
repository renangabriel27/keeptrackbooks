package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteBookDatabase;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteCategoryDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //Category c1 = new Category("tedfd");

        Book b1 = new Book("teste", 25, 1);
        Book b2 = new Book("camaro", 35, 1);

        SQLiteBookDatabase db = new SQLiteBookDatabase(getApplicationContext());
        //SQLiteCategoryDatabase db2 = new SQLiteCategoryDatabase(getApplicationContext());

        //db2.create(c1);
        //Log.d("PDMLog", "count categories: " + db2.count());

        db.create(b1);
        db.create(b2);

        Log.d("PDMLog","count:"+db.count());

        Book vFromDb = db.find(1);

        Log.d("PDMLog",vFromDb!=null?vFromDb.toString():"null");

        db.update(new Book(1, "vw/audi", 50, 2));

        System.out.println("---all:");

        for(Book b : db.all()){
            Log.d("PDMLog",b.toString());
        }

        System.out.println("---remove:"+b1);
        db.delete(b1);

        for(Book b : db.all()){
            Log.d("PDMLog",b.toString());
            db.delete(b);
        }

        Log.d("PDMLog","count:"+db.count());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_books:
                changeActivity(getBaseContext(), BooksActivity.class);
                break;
            case R.id.action_categories:
                changeActivity(getBaseContext(), CategoriesActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
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

}
