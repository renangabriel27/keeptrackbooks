package renan.tsi.pro.br.keeptrackbooks.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteCategoryDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Category;

public class NewCategoryActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        backToMain();
        saveCategory();
    }

    private void createCategory(EditText nameCategory) {
        SQLiteCategoryDatabase db = new SQLiteCategoryDatabase(getApplicationContext());

        Category category = new Category(nameCategory.getText().toString());

        db.create(category);

        Toast.makeText(getBaseContext(), "Category was created with success!", Toast.LENGTH_LONG).show();
        changeActivity(getBaseContext(), CategoriesActivity.class);
    }

    protected void saveCategory() {
        final Button createCategoryBtn = (Button) findViewById(R.id.createCategoryBtn);

        createCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameCategory = (EditText) findViewById(R.id.editCategory);

                if (fieldIsEmpty(nameCategory)){
                    Toast.makeText(getApplicationContext(), "The field can't be empty!", Toast.LENGTH_LONG).show();
                } else {
                    createCategory(nameCategory);
                }
            }
        });
    }
}
