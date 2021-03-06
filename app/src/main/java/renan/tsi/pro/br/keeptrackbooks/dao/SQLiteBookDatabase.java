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

public class SQLiteBookDatabase extends SQLiteBase {

    public SQLiteBookDatabase(Context ctx) {
        super(ctx);
    }

    public void create(Book b) {
        ContentValues values = new ContentValues();
        values.put("title", b.getTitle());
        values.put("number_pages", b.getNumberPages());
        values.put("category_id", b.getCategory().getId());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("books", null, values);

        db.close();
    }

    public Book find(int id) {
        Book result = null;
        SQLiteCategoryDatabase dbCategory = new SQLiteCategoryDatabase(this.ctx);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("books", new String[] { "id", "title", "number_pages", "category_id" }, "id=?",
                new String[] { String.valueOf(id) }, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Category category = dbCategory.find(cursor.getInt(3));
            result = new Book(cursor.getInt(0),
                                  cursor.getString(1),
                                  cursor.getInt(2),
                                  category);
        }

        db.close();

        return result;
    }

    public Book findByTitle(String title) {
        Book result = null;
        SQLiteCategoryDatabase dbCategory = new SQLiteCategoryDatabase(this.ctx);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("books", new String[] { "id", "title", "number_pages", "category_id" }, "title=?",
                new String[] { String.valueOf(title) }, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Category category = dbCategory.find(cursor.getInt(3));
            result = new Book(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    category);
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
        values.put("category_id", b.getCategory().getId());

        db.update("books", values, "id = ?",
                new String[] { String.valueOf(b.getId()) });

        db.close();
    }

    public List<Book> all() {
        List<Book> result = new ArrayList<Book>();
        SQLiteCategoryDatabase dbCategory = new SQLiteCategoryDatabase(this.ctx);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT  * FROM books", null);

        if (cursor.moveToFirst()) {
            do {
                Category category = dbCategory.find(cursor.getInt(3));
                result.add(new Book(cursor.getInt(0),
                                    cursor.getString(1),
                                    cursor.getInt(2),
                                    category));
            } while (cursor.moveToNext());
        }

        db.close();

        return result;
    }

    public boolean hasBookWithCategory(int categoryId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("books", new String[] { "id"}, "category_id = ?",
                new String[] { String.valueOf(categoryId) }, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        return false;
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