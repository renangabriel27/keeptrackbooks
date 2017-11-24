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
import renan.tsi.pro.br.keeptrackbooks.models.Status;

public class SQLiteStatusDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "keeptrackbook";
    private static final int DATABASE_VERSION = 4;
    private Context ctx;

    public SQLiteStatusDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE status (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "book_id INTEGER," +
                "status INTEGER," +
                "notes TEXT," +
                "FOREIGN KEY (book_id) REFERENCES books(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("PDMLog", "Upgrading database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS status");
        onCreate(db);
    }

    // ========== CRUD Operations

    public void create(Status s) {
        ContentValues values = new ContentValues();
        values.put("book_id", s.getBookId());
        values.put("status", s.getStatus());
        values.put("notes", s.getNotes());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("status", null, values);

        db.close();
    }

    public Status find(int id) {
        Status result = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("status", new String[] { "id", "book_id", "status", "notes" }, "id=?",
                new String[] { String.valueOf(id) }, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            result = new Status(cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getInt(2),
                                cursor.getString(3));
        }

        db.close();

        return result;
    }

    public void delete(Status s) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("status", "id = ?",
                new String[] { String.valueOf(s.getId()) });
        db.close();
    }

    public void update(Status s) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("book_id", s.getBookId());
        values.put("status", s.getStatus());
        values.put("notes", s.getNotes());

        db.update("status", values, "id = ?",
                new String[] { String.valueOf(s.getId()) });

        db.close();
    }

    public List<Status> all() {
        SQLiteBookDatabase dbBook = new SQLiteBookDatabase(this.ctx);


        List<Status> result = new ArrayList<Status>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT  * FROM status", null);

        if (cursor.moveToFirst()) {
            do {
                Book book = dbBook.find(cursor.getInt(1));

                result.add(new Status(cursor.getInt(0),
                                      book,
                                      cursor.getInt(2),
                                      cursor.getString(3),
                                      cursor.getInt(1)));
            } while (cursor.moveToNext());
        }

        db.close();

        return result;
    }

    public int count() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM status", null);

        cursor.moveToFirst();
        int count = cursor.getInt(0);

        db.close();

        return count;
    }

}