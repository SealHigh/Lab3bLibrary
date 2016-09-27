package Library;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Martin on 2016-09-18.
 */

/**
 * @author Timothy Holmsten
 * @author Martin Renstrom
 */
public class Book implements Serializable {

    private String isbn;
    private String title;
    private int edition;
    private double price;
    private ArrayList<Author> authors;

    public Book(String title, ArrayList<Author> authors) {
        this.title = title;
        this.authors = new ArrayList<>();
        this.authors.addAll(authors);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return isbn;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Edition: %d, Authors: %s, ISBN: %s, Price: %.2f",
                title,
                edition,
                authors,
                isbn,
                price);
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }
}
