package renan.tsi.pro.br.keeptrackbooks.models;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.List;

import renan.tsi.pro.br.keeptrackbooks.dao.SQLiteStatusDatabase;

/**
 * Created by renan on 20/11/17.
 */

public class Status extends Application {

    private int _id;
    private Book book;
    private int bookId;
    private int status;
    private String notes;
    private static SQLiteStatusDatabase dao;

    public Status(int _id, int bookId, int status, String notes) {
        this._id = _id;
        this.bookId = bookId;
        this.status = status;
        this.notes = notes;
    }

    public Status(int _id, Book book, int status, String notes, int bookId) {
        this._id = _id;
        this.book = book;
        this.status = status;
        this.notes = notes;
        this.bookId = bookId;
    }

    public Status(Book book, int status, String notes, int bookId) {
        this.book = book;
        this.status = status;
        this.notes = notes;
        this.bookId = bookId;
    }

    public Status(int bookId, int status, String notes) {
        this.bookId = bookId;
        this.status = status;
        this.notes = notes;
    }

    public int getId() {
        return _id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatusFormated(int status) {
        return (status == 1) ? "Done" : "In progress";
    }

    @Override
    public String toString() {
        return "Book " + book.getTitle() + " Status[" + getStatusFormated(status) + "]" + notes ;
    }

    public static List<Status> all(Context context) {
        dao = new SQLiteStatusDatabase(context);
        return dao.all();
    }
}
