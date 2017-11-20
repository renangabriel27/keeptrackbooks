package renan.tsi.pro.br.keeptrackbooks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import renan.tsi.pro.br.keeptrackbooks.R;

public class CategoriesActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        changeToMain();
        changeToNewCategory();
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
