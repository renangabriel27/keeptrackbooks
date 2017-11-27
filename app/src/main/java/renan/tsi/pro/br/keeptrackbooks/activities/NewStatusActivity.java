package renan.tsi.pro.br.keeptrackbooks.activities;

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
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteCategoryDatabase;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteStatusDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;
import renan.tsi.pro.br.keeptrackbooks.models.Status;

public class NewStatusActivity extends MainActivity {

    private ArrayAdapter<Book> bookAdapter;
    private EditText notes;
    private boolean finishedIsChecked;
    private int bookNumberPages = 0;
    private AutoCompleteTextView selectBook;
    private Status status;

    private SQLiteBookDatabase dbBook;
    private SQLiteStatusDatabase dbStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_status);

        changeToMain();
        setAutoCompleteForBooks();
        saveStatus();
    }

    protected void setAutoCompleteForBooks() {
        bookAdapter = new ArrayAdapter<Book>(this,
                android.R.layout.simple_dropdown_item_1line, getBooks());

        selectBook = (AutoCompleteTextView) findViewById(R.id.selectBook);

        selectBook.setAdapter(bookAdapter);
        selectBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setBook(bookAdapter, position);
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

    protected void saveStatus() {
        final Button createStatusBtn = (Button) findViewById(R.id.createStatusBtn);

        createStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromView();
                validateAndCreate();
            }
        });
    }

    private void validateAndCreate() {
        if (fieldIsEmpty(notes) || fieldIsEmpty(selectBook)){
            showMessageWhenFieldsEmpty();
        } else if(getBook().getId() == 0) {
            showMessage("Invalid book!");
        } else {
            createStatus();
        }
    }

    private void createStatus() {
        String statusNotes = notes.getText().toString();

        dbStatus = new SQLiteStatusDatabase(getApplicationContext());

        if(finishedIsChecked) {
            status = new Status(getBook(), 1, statusNotes);
        } else {
            status = new Status(getBook(), 0, statusNotes);
        }

        dbStatus.create(status);
        showSuccessMessage();
        changeActivity(getBaseContext(), MainActivity.class);
    }

    private void getValuesFromView() {
        notes = (EditText) findViewById(R.id.notes);
        finishedIsChecked = ((CheckBox) findViewById(R.id.finished)).isChecked();
        selectBook = (AutoCompleteTextView) findViewById(R.id.selectBook);
    }

    private void showSuccessMessage() {
        showMessage("Status was created with success!");
    }
}
