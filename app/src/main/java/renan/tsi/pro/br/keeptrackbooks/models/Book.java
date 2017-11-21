package renan.tsi.pro.br.keeptrackbooks.models;

/**
 * Created by renan on 20/11/17.
 */

public class Book {

    private int _id;
    private String title;
    private int numberPages;
    private int categoryId;

    public Book(int _id, String title, int numberPages, int categoryId) {
        this._id = _id;
        this.title = title;
        this.numberPages = numberPages;
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

    @Override
    public String toString() {
        return "Id: " + _id + " / Title: "+ title + " / Number of pages: " + numberPages + " / Category " + categoryId;
    }
}
