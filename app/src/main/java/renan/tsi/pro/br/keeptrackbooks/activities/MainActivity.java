package renan.tsi.pro.br.keeptrackbooks.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.InvalidMarkException;
import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.adapters.StatusAdapter;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteBookDatabase;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteCategoryDatabase;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteStatusDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;
import renan.tsi.pro.br.keeptrackbooks.models.Status;
import renan.tsi.pro.br.keeptrackbooks.services.ServiceBook;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeToNewStatus();
        initComponentsForMain();
    }

    public void initComponentsForMain() {
        setToolbar();
        setListView();
    }

    public void setToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
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
            case R.id.action_search_books:
                changeActivity(getBaseContext(), SearchBookActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void changeToMain() {
        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), MainActivity.class);
            }
        });
    }

    protected void backToMain() {
        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);

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

    protected boolean fieldIsEmpty(EditText text) {
        return text.getText().toString().trim().equals("") || text.getText().toString().trim().equals("");
    }

    private void changeToNewStatus() {
        ImageButton newStatusBtn = (ImageButton) findViewById(R.id.newStatusBtn);

        newStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), NewStatusActivity.class);
            }
        });
    }

    private void sendStatusIdWhenChangeActivity(ListAdapter statusAdapter, int position) {
        Bundle params = new Bundle();
        params.putLong("id", statusAdapter.getItemId(position));
        Intent intent = new Intent(getBaseContext(), EditStatusActivity.class);
        intent.putExtras(params);
        startActivity(intent);
    }

    private void setListView() {
        ListView lv = (ListView) findViewById(R.id.statusListView);
        final ListAdapter statusAdapter = new StatusAdapter(
                (ArrayList<Status>) Status.all(getApplicationContext()), getLayoutInflater());
        lv.setAdapter(statusAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendStatusIdWhenChangeActivity(statusAdapter, position);
            }
        });
    }





}
