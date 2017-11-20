package renan.tsi.pro.br.keeptrackbooks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import renan.tsi.pro.br.keeptrackbooks.R;

public class NewCategoryActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        changeToMain();
    }
}
