package renan.tsi.pro.br.keeptrackbooks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import renan.tsi.pro.br.keeptrackbooks.R;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteBookDatabase;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteStatusDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;
import renan.tsi.pro.br.keeptrackbooks.models.Status;

public class EditStatusActivity extends MainActivity {

    private long id;
    private Status statusEdit;
    private int idStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_status);

        changeToMain();
        setFieldsForStatus();
        deleteStatus();
    }

    private void setFieldsForStatus() {
        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if(params!=null){
            this.id = params.getLong("id");

            Log.i("Params", "id:"+params.getLong("id"));
        }

        SQLiteStatusDatabase db = new SQLiteStatusDatabase(getApplicationContext());
        this.statusEdit = db.find((int) id);

        EditText editNotes = (EditText) findViewById(R.id.editNotes);
        editNotes.setText(statusEdit.getNotes());

        //CheckBox editFinished = (CheckBox) findViewById(R.id.editFinished);
        //editFinished.set(String.valueOf(bookEdit.getNumberPages()));

        AutoCompleteTextView editSelectedBook = (AutoCompleteTextView)
                findViewById(R.id.selectEditBook);

        //editSelectedBook.setText(statusEdit.getBook().getTitle());

        final ArrayAdapter<Book> statusAdapter = new ArrayAdapter<Book>(this,
                android.R.layout.simple_dropdown_item_1line, getBooks());

        editSelectedBook.setAdapter(statusAdapter);
        editSelectedBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setIdForStatus(statusAdapter.getItem(position).getId());
            }
        });
    }

    protected ArrayList<Book> getBooks() {
        ArrayList<Book> b = new ArrayList<Book>();

        for(Book book : Book.all(getApplicationContext())){
            b.add(book);
        }

        return  b;
    }
    protected void setIdForStatus(int id) {
        this.idStatus = id;
    }

    protected void deleteStatus() {
        Button deleteStatusBtn = (Button) findViewById(R.id.deleteStatusBtn);

        deleteStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteStatusDatabase db = new SQLiteStatusDatabase(getApplicationContext());
                db.delete(statusEdit);
                Toast.makeText(getBaseContext(), "Status was deleted with success!", Toast.LENGTH_LONG).show();
                changeActivity(getBaseContext(), MainActivity.class);
            }
        });
    }
}
