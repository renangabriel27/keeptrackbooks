package renan.tsi.pro.br.keeptrackbooks.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import renan.tsi.pro.br.keeptrackbooks.models.Book;
import renan.tsi.pro.br.keeptrackbooks.models.Category;

public class SQLiteBookDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "keeptrackbooks";
    private static final int DATABASE_VERSION = 2;

    public SQLiteBookDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE books (" +
                                   "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                   "title TEXT," +
                                   "number_pages INTEGER," +
                                   "category_id INTEGER," +
                                   "FOREIGN KEY (category_id) REFERENCES categories(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("PDMLog", "Upgrading database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }

    // ========== CRUD Operations

    public void create(Book b) {
        ContentValues values = new ContentValues();
        values.put("title", b.getTitle());
        values.put("number_pages", b.getNumberPages());
        values.put("category_id", b.getCategoryId());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("books", null, values);

        db.close();
    }

    public Book find(int id) {
        Book result = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("books", new String[] { "id", "title", "number_pages", "category_id" }, "id=?",
                new String[] { String.valueOf(id) }, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            result = new Book(cursor.getInt(0),
                                  cursor.getString(1),
                                  cursor.getInt(2),
                                  cursor.getInt(3));
        }

        db.close();

        return result;
    }

    public void delete(Book b) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("books", "id = ?",
                new String[] { String.valueOf(b.getId()) });
        db.close();
    }

    public void update(Book b) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", b.getTitle());
        values.put("number_pages", b.getNumberPages());
        values.put("category_id", b.getCategoryId());

        db.update("books", values, "id = ?",
                new String[] { String.valueOf(b.getId()) });

        db.close();
    }

    public List<Book> all() {
        List<Book> result = new ArrayList<Book>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM books", null);

        if (cursor.moveToFirst()) {
            do {
                result.add(new Book(cursor.getInt(0),
                                    cursor.getString(1),
                                    cursor.getInt(2),
                                    cursor.getInt(3)));
            } while (cursor.moveToNext());
        }

        db.close();

        return result;
    }

    public int count() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM books", null);

        cursor.moveToFirst();
        int count = cursor.getInt(0);

        db.close();

        return count;
    }

}