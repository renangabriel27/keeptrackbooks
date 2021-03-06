package renan.tsi.pro.br.keeptrackbooks.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import renan.tsi.pro.br.keeptrackbooks.models.Category;

public class SQLiteCategoryDatabase extends SQLiteBase {

    public SQLiteCategoryDatabase(Context context) {
        super(context);
    }

    public void create(Category c) {
        ContentValues values = new ContentValues();
        values.put("name", c.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("categories", null, values);

        db.close();
    }

    public Category find(int id) {
        Category result = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("categories", new String[] { "id", "name" }, "id=?",
                new String[] { String.valueOf(id) }, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            result = new Category(cursor.getInt(0), cursor.getString(1));
        }

        db.close();

        return result;
    }

    public Category findByName(String name) {
        Category result = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("categories", new String[] { "id", "name" }, "name=?",
                new String[] { String.valueOf(name) }, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            result = new Category(cursor.getInt(0), cursor.getString(1));
        }

        db.close();

        return result;
    }

    public void delete(Category c) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("categories", "id = ?",
                new String[] { String.valueOf(c.getId()) });
        db.close();
    }

    public void update(Category c) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", c.getName());

        db.update("categories", values, "id = ?",
                new String[] { String.valueOf(c.getId()) });

        db.close();
    }

    public List<Category> all() {
        List<Category> result = new ArrayList<Category>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT  * FROM categories", null);

        if (cursor.moveToFirst()) {
            do {
                result.add(new Category(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        db.close();

        return result;
    }

    public int count() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM categories", null);

        cursor.moveToFirst();
        int count = cursor.getInt(0);

        db.close();

        return count;
    }

}