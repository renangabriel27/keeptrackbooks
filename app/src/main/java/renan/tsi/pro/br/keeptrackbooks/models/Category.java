package renan.tsi.pro.br.keeptrackbooks.models;

import android.content.Context;

import java.util.List;

import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteBookDatabase;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteCategoryDatabase;

/**
 * Created by renan on 20/11/17.
 */

public class Category {

    private String name;
    private int _id;
    private static SQLiteCategoryDatabase dao;

    public Category(String name) {
        this.name = name;
    }

    public Category(int _id, String name) {
        this.name = name;
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return _id;
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<Category> all(Context context) {
        dao = new SQLiteCategoryDatabase(context);
        return dao.all();
    }
}
