package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.adapters.CategoryAdapter;
import renan.tsi.pro.br.keeptrackbooks.adapters.StatusAdapter;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteBookDatabase;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteCategoryDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Category;
import renan.tsi.pro.br.keeptrackbooks.models.Status;

public class EditCategoryActivity extends MainActivity {

    private long id;
    private Category categoryEdit;
    private SQLiteCategoryDatabase db;
    private SQLiteBookDatabase dbBook;
    private EditText editCategoryText;
    private ImageButton backCategoriesBtn;
    private Button updateCategoryBtn;
    private Category category;

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
            id = params.getLong("id");
        }

        db = new SQLiteCategoryDatabase(getApplicationContext());
        categoryEdit = db.find((int) id);

        editCategoryText = (EditText) findViewById(R.id.editCategory);
        editCategoryText.setText(categoryEdit.getName());
    }

    protected void changeToCategories() {
        backCategoriesBtn = (ImageButton) findViewById(R.id.backCategoriesBtn);

        backCategoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), CategoriesActivity.class);
            }
        });
    }

    protected void updateCategory() {
        updateCategoryBtn = (Button) findViewById(R.id.updateCategoryBtn);

        updateCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText c = (EditText) findViewById(R.id.editCategory);

                if (fieldIsEmpty(c)){
                    showMessageWhenFieldsEmpty();
                } else {
                    category = new Category((int) id, c.getText().toString());
                    db = new SQLiteCategoryDatabase(getApplicationContext());
                    db.update(category);
                    showSuccessUpdateMessage();
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

                db = new SQLiteCategoryDatabase(getApplicationContext());
                dbBook = new SQLiteBookDatabase((getApplicationContext()));

                if(dbBook.hasBookWithCategory(categoryEdit.getId())) {
                    showMessage("Category cannot be deleted, because has relationships with books!");
                } else {
                    db.delete(categoryEdit);
                    showMessage("Category was deleted with success!");
                }

                changeActivity(getBaseContext(), CategoriesActivity.class);
            }
        });
    }

    private void showSuccessUpdateMessage() {
        showMessage("Category was updated with success!");
    }

}
