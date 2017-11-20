package renan.tsi.pro.br.keeptrackbooks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import renan.tsi.pro.br.keeptrackbooks.R;

public class StatusActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        changeToMain();
        changeToNewStatus();
    }

    private void changeToNewStatus() {
        Button newStatusBtn = (Button) findViewById(R.id.newStatusBtn);

        newStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(getBaseContext(), NewStatusActivity.class);
            }
        });
    }
}
