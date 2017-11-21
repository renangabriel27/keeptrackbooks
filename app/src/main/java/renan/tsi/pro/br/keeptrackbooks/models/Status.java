package renan.tsi.pro.br.keeptrackbooks.models;

/**
 * Created by renan on 20/11/17.
 */

public class Status {

    private int _id;
    private int bookId;
    private int status;
    private String notes;

    public Status(int _id, int bookId, int status, String notes) {
        this._id = _id;
        this.bookId = bookId;
        this.status = status;
        this.notes = notes;
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

    @Override
    public String toString() {
        return "Title: "+ bookId+ " / STATUS: " + status ;
    }
}
