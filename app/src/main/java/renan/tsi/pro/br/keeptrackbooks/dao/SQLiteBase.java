package renan.tsi.pro.br.keeptrackbooks.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by renan on 27/11/17.
 */

public class SQLiteBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "keeptrackbook";
    private static final int DATABASE_VERSION = 6;
    protected static Context ctx;

    public SQLiteBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE categories (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");

        db.execSQL("CREATE TABLE books (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "number_pages INTEGER," +
                "category_id INTEGER," +
                "FOREIGN KEY (category_id) REFERENCES categories(id))");

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

        db.execSQL("DROP TABLE IF EXISTS categories");
        db.execSQL("DROP TABLE IF EXISTS status");
        db.execSQL("DROP TABLE IF EXISTS books");

        onCreate(db);
    }
}
