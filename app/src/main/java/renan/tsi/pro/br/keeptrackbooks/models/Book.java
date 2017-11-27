package renan.tsi.pro.br.keeptrackbooks.models;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteBookDatabase;
import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteStatusDatabase;

/**
 * Created by renan on 20/11/17.
 */

public class Book {

    private int _id;
    private String title;
    private int numberPages;
    private Category category;
    private static SQLiteBookDatabase dao;

    public Book(int _id, String title, int numberPages, Category category) {
        this._id = _id;
        this.title = title;
        this.numberPages = numberPages;
        this.category = category;
    }

    public Book(String title, int numberPages,Category category) {
        this.title = title;
        this.numberPages = numberPages;
        this.category = category;
    }

    public int getId() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return title;
    }

    public static Book find(int id, Context context) {
        dao = new SQLiteBookDatabase(context);
        return dao.find(id);
    }

    public static List<Book> all(Context context) {
        dao = new SQLiteBookDatabase(context);
        return dao.all();
    }

}
