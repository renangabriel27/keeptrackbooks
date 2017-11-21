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

        changeToMain();
        saveCategory();
    }

    protected void saveCategory() {
        Button createCategoryBtn = (Button) findViewById(R.id.createCategoryBtn);

        createCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText c = (EditText) findViewById(R.id.editCategory);

                if (c.getText().toString().trim().equals("") ||
                        c.getText().toString().trim().equals("")){

                    Toast.makeText(getApplicationContext(), "The field can't be empty!", Toast.LENGTH_LONG).show();

                } else {

                    Category category = new Category(c.getText().toString());

                    SQLiteCategoryDatabase db = new SQLiteCategoryDatabase(getApplicationContext());
                    db.create(category);
                    changeActivity(getBaseContext(), CategoriesActivity.class);
                }
            }
        });
    }
}
