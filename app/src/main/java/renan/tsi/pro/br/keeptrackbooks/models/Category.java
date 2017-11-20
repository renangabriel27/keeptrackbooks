package renan.tsi.pro.br.keeptrackbooks.models;

/**
 * Created by renan on 20/11/17.
 */

public class Category {

    private String name;
    private int _id;

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
        return "Id: " + _id + " / Nome: "+ name;
    }
}
