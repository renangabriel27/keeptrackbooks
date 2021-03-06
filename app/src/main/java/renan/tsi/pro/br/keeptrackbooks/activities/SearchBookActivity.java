package renan.tsi.pro.br.keeptrackbooks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.adapters.BookAdapter;
import renan.tsi.pro.br.keeptrackbooks.adapters.CategoryAdapter;
import renan.tsi.pro.br.keeptrackbooks.adapters.SearchBookAdapter;
import renan.tsi.pro.br.keeptrackbooks.models.Book;

public class SearchBookActivity extends MainActivity {

    private ArrayList<String> result = new ArrayList<String>();
    private ListView lv;
    private ListAdapter searchBookAdapter;
    private Button searchBtn;
    private EditText nameForSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        changeToMain();
        sendRequisition();
    }

    private void sendRequisition() {
        searchBtn = (Button) findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameForSearch = (EditText) findViewById(R.id.nameForSearch);

                if (fieldIsEmpty(nameForSearch)) {
                    showMessageWhenFieldsEmpty();
                } else {
                    result.clear();
                    showMessage("Loading...");
                    requisitionGetJson(nameForSearch.getText().toString());
                }
            }
        });
    }

    private void setListView() {
        lv = (ListView) findViewById(R.id.searchListView);

        searchBookAdapter = new SearchBookAdapter(result, getLayoutInflater());
        lv.setAdapter(searchBookAdapter);
    }

    private void requisitionGetJson(String name) {
        RequestQueue queue = Volley.newRequestQueue(this);

        name = name.replaceAll("\\s+", "+");

        String url ="https://www.googleapis.com/books/v1/volumes?q=" + name;

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray items;
                            items = response.getJSONArray("items");

                            for (int i = 0; i < items.length(); i++) {
                                try {
                                    JSONObject jsonObject = items.getJSONObject(i);
                                    JSONObject volumeInfo = jsonObject.getJSONObject("volumeInfo");
                                    String title = volumeInfo.getString("title");
                                    String category = volumeInfo.getString("categories");
                                    String pageCount = volumeInfo.getString("pageCount");
                                    String authors = volumeInfo.getString("authors");

                                    String bookFormated = title + "\n" + authors + "\n" + category + "\n" + pageCount + " pages";
                                    result.add(bookFormated);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    continue;
                                } finally {
                                    setListView();
                                }
                            }
                        } catch(JSONException e) {
                            showMessage("Error on connection!");
                        }
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showMessage("Book not found!");
                    }
                });
        queue.add(stringRequest);
    }
}
