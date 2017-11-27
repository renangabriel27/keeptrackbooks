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

    private SQLiteCategoryDatabase db;
    private EditText nameCategory;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        changeToMain();
        saveCategory();
    }

    private void createCategory(EditText nameCategory) {
        db = new SQLiteCategoryDatabase(getApplicationContext());

        category = new Category(nameCategory.getText().toString());

        db.create(category);

        showSuccessMessage();
        changeActivity(getBaseContext(), CategoriesActivity.class);
    }

    protected void saveCategory() {
        final Button createCategoryBtn = (Button) findViewById(R.id.createCategoryBtn);

        createCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameCategory = (EditText) findViewById(R.id.editCategory);
                validateAndCreate();
            }
        });
    }

    private void validateAndCreate() {
        if (fieldIsEmpty(nameCategory)){
            showMessageWhenFieldsEmpty();
        } else {
            createCategory(nameCategory);
        }
    }

    private void showSuccessMessage() {
        showMessage("Category was created with success!");
    }
}
