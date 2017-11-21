package renan.tsi.pro.br.keeptrackbooks.models;

import android.content.Context;

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
    private int categoryId;
    private Category category;
    private static SQLiteBookDatabase dao;

    public Book(int _id, String title, int numberPages, int categoryId) {
        this._id = _id;
        this.title = title;
        this.numberPages = numberPages;
        this.categoryId = categoryId;
    }

    public Book(int _id, String title, int numberPages, Category category, int categoryId) {
        this._id = _id;
        this.title = title;
        this.numberPages = numberPages;
        this.category = category;
        this.categoryId = categoryId;
    }

    public Book(String title, int numberPages, int categoryId) {
        this.title = title;
        this.numberPages = numberPages;
        this.categoryId = categoryId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return title + " [" + numberPages + " pgs] - " + categoryId;
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
