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
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteStatusDatabase;
import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;
import renan.tsi.pro.br.keeptrackbooks.models.Status;

public class NewStatusActivity extends MainActivity {

    private int idBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_status);

        backToMain();
        setAutoCompleteForBooks();
        saveStatus();
    }

    protected void setAutoCompleteForBooks() {
        final ArrayAdapter<Book> bookAdapter = new ArrayAdapter<Book>(this,
                android.R.layout.simple_dropdown_item_1line, getBooks());

        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.selectBook);

        textView.setAdapter(bookAdapter);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setIdForBook(bookAdapter.getItem(position).getId());
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

    protected void setIdForBook(int id) {
        this.idBook = id;
    }

    protected void saveStatus() {
        final Button createStatusBtn = (Button) findViewById(R.id.createStatusBtn);

        createStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText notes = (EditText) findViewById(R.id.notes);
                boolean finishedIsChecked = ((CheckBox) findViewById(R.id.finished)).isChecked();
                AutoCompleteTextView textView = (AutoCompleteTextView)
                        findViewById(R.id.selectBook);

                if (fieldIsEmpty(notes) || fieldIsEmpty(textView)){
                    Toast.makeText(getApplicationContext(), "The field(s) can't be empty!", Toast.LENGTH_LONG).show();
                } else if(idBook == 0) {
                    Toast.makeText(getApplicationContext(), "Book invalid!", Toast.LENGTH_LONG).show();
                } else {
                    String statusNotes = notes.getText().toString();
                    int bookNumberPages = 0;
                    SQLiteBookDatabase dbBook = new SQLiteBookDatabase(getApplicationContext());
                    Book b = dbBook.find(idBook);
                    Status status;

                    if(finishedIsChecked) {
                        status = new Status(b, 1, statusNotes);
                    } else {
                        status = new Status(b, 0, statusNotes);
                    }

                    SQLiteStatusDatabase db = new SQLiteStatusDatabase(getApplicationContext());
                    db.create(status);
                    Toast.makeText(getBaseContext(), "Status was created with success!", Toast.LENGTH_LONG).show();
                    changeActivity(getBaseContext(), MainActivity.class);
                }
            }
        });
    }
}
