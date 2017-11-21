package renan.tsi.pro.br.keeptrackbooks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.adapters.CategoryAdapter;
import renan.tsi.pro.br.keeptrackbooks.adapters.StatusAdapter;
import renan.tsi.pro.br.keeptrackbooks.models.Category;
import renan.tsi.pro.br.keeptrackbooks.models.Status;

public class CategoriesActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        changeToMain();
        changeToNewCategory();

        ListView lv = (ListView) findViewById(R.id.categoriesListView);

        ListAdapter categoryAdapter = new CategoryAdapter(
                (ArrayList<Category>) Category.all(getApplicationContext()), getLayoutInflater());
        lv.setAdapter(categoryAdapter);
    }

    private void changeToNewCategory() {
        Button newCategoryBtn = (Button) findViewById(R.id.newCategoryBtn);

        newCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), NewCategoryActivity.class);
            }
        });
    }
}
