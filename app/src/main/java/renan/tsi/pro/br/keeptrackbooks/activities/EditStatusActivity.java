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
    private SQLiteStatusDatabase db;
    private EditText editNotes;
    private CheckBox editFinished;
    private AutoCompleteTextView editSelectedBook;
    private ArrayAdapter<Book> statusAdapter;
    private Status status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_status);

        changeToMain();
        setFieldsForStatus();
        deleteStatus();
        updateStatus();
    }

    private void setFieldsForStatus() {
        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if(params!=null){
            id = params.getLong("id");
        }

        db = new SQLiteStatusDatabase(getApplicationContext());
        statusEdit = db.find((int) id);

        getValuesFromView();
        editNotes.setText(statusEdit.getNotes());

        if(statusEdit.getStatus() == 1)
          editFinished.setChecked(true);

        editSelectedBook.setText(statusEdit.getBook().getTitle());

        statusAdapter = new ArrayAdapter<Book>(this,
                android.R.layout.simple_dropdown_item_1line, getBooks());

        editSelectedBook.setAdapter(statusAdapter);
        editSelectedBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setBook(statusAdapter, position);
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

    protected void updateStatus() {
        Button updateStatusBtn = (Button) findViewById(R.id.updateStatusBtn);
        editSelectedBook = (AutoCompleteTextView) findViewById(R.id.selectEditBook);

        updateStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldIsEmpty(editSelectedBook) || fieldIsEmpty(editNotes)) {
                    showMessageWhenFieldsEmpty();
                } else if(getBook() == null) {
                   validateAndUpdate(statusEdit.getBook());
                } else {
                    validateAndUpdate(getBook());
                }
            }
        });
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

    private void validateAndUpdate(Book book) {
        String bookName = editSelectedBook.getText().toString();

        SQLiteBookDatabase dbBook = new SQLiteBookDatabase(getApplicationContext());

        if(editFinished.isChecked()) {
            status = new Status((int) id, book, 1, editNotes.getText().toString());
        } else {
            status = new Status((int) id, book, 0, editNotes.getText().toString());
        }

        db = new SQLiteStatusDatabase(getApplicationContext());
        db.update(status);

        showSuccessUpdateMessage();
        changeActivity(getBaseContext(), MainActivity.class);
    }

    private void showSuccessUpdateMessage() {
        showMessage("Status was updated with success!");
    }

    private void getValuesFromView() {
        editNotes = (EditText) findViewById(R.id.editNotes);
        editFinished = (CheckBox) findViewById(R.id.editFinished);
        editSelectedBook = (AutoCompleteTextView) findViewById(R.id.selectEditBook);
    }
}
