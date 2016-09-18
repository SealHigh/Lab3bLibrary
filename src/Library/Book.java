package Library;

import java.util.ArrayList;

/**
 * Created by Martin on 2016-09-18.
 */
public class Book {

    private String isbn;
    private String title;
    private int edition;
    private double price;
    private ArrayList<Author> authors;

    public Book(String title){
        this.title = title;
        authors = new ArrayList<>();
    }

    public void addAuthor(String name){
        authors.add(new Author(name));
    }

    public ArrayList getAuthors(){
        return authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
