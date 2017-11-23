package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.adapters.CategoryAdapter;
import renan.tsi.pro.br.keeptrackbooks.adapters.StatusAdapter;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteCategoryDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Category;
import renan.tsi.pro.br.keeptrackbooks.models.Status;

public class EditCategoryActivity extends MainActivity {

    private long id;
    private Category categoryEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        changeToCategories();

        setEditTextForCategory();
        updateCategory();
        deleteCategory();
    }

    private void setEditTextForCategory() {
        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if(params!=null){
            this.id = params.getLong("id");

            Log.i("Params", "id:"+params.getLong("id"));
        }

        SQLiteCategoryDatabase db = new SQLiteCategoryDatabase(getApplicationContext());
        this.categoryEdit = db.find((int) id);

        EditText editCategoryText = (EditText) findViewById(R.id.editCategory);
        editCategoryText.setText(categoryEdit.getName());
    }

    protected void changeToCategories() {
        Button backCategoriesBtn = (Button) findViewById(R.id.backCategoriesBtn);

        backCategoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), CategoriesActivity.class);
            }
        });
    }

    protected void updateCategory() {
        Button updateCategoryBtn = (Button) findViewById(R.id.updateCategoryBtn);

        updateCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText c = (EditText) findViewById(R.id.editCategory);

                if (c.getText().toString().trim().equals("") ||
                        c.getText().toString().trim().equals("")){

                    Toast.makeText(getApplicationContext(), "The field can't be empty!", Toast.LENGTH_LONG).show();

                } else {

                    Category category = new Category((int) id, c.getText().toString());

                    SQLiteCategoryDatabase db = new SQLiteCategoryDatabase(getApplicationContext());
                    db.update(category);
                    Toast.makeText(getBaseContext(), "Category was updated with success!", Toast.LENGTH_LONG).show();
                    changeActivity(getBaseContext(), CategoriesActivity.class);
                }
            }
        });
    }

    protected void deleteCategory() {
        Button deleteCategoryBtn = (Button) findViewById(R.id.deleteCategoryBtn);

        deleteCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText c = (EditText) findViewById(R.id.editCategory);

                SQLiteCategoryDatabase db = new SQLiteCategoryDatabase(getApplicationContext());
                db.delete(categoryEdit);
                Toast.makeText(getBaseContext(), "Category was deleted with success!", Toast.LENGTH_LONG).show();
                changeActivity(getBaseContext(), CategoriesActivity.class);
            }
        });
    }

}
