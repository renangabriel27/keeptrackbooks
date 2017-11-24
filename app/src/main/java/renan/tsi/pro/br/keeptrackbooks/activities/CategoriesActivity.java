package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.adapters.CategoryAdapter;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteCategoryDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Category;

public class CategoriesActivity extends MainActivity {

    private SQLiteCategoryDatabase dbCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        dbCategory = new SQLiteCategoryDatabase(getApplicationContext());
        backToMain();
        changeToNewCategory();
        setListView();
    }

    private void sendCategoryIdWhenChangeActivity(ListAdapter categoryAdapter, int position) {
        Bundle params = new Bundle();
        params.putLong("id", categoryAdapter.getItemId(position));
        Intent intent = new Intent(getBaseContext(), EditCategoryActivity.class);
        intent.putExtras(params);
        startActivity(intent);
    }

    private void setListView() {
        ListView lv = (ListView) findViewById(R.id.categoriesListView);

        final ListAdapter categoryAdapter = new CategoryAdapter(
                (ArrayList<Category>) dbCategory.all(), getLayoutInflater());
        lv.setAdapter(categoryAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            sendCategoryIdWhenChangeActivity(categoryAdapter, position);
            }
        });
    }

    private void changeToNewCategory() {
        ImageButton newCategoryBtn = (ImageButton) findViewById(R.id.newCategoryBtn);

        newCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), NewCategoryActivity.class);
            }
        });
    }
}
