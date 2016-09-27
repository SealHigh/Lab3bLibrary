package Library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Martin on 2016-09-18.
 */

/**
 * @author Timothy Holmsten
 * @author Martin Renstrom
 */
public class Book implements Serializable, Comparable<Book> {

    private String isbn;
    private String title;
    private int edition;
    private double price;
    private ArrayList<Author> authors;

    public Book(String title, ArrayList<Author> authors, int edition, double price) {
        this.title = title;
        this.authors = new ArrayList<>();
        this.authors.addAll(authors);
        this.edition = edition;
        this.price = price;
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


    /**
     * Compare each book by name, if same name sort by isbn
     * @param book
     * @return
     */
    @Override
    public int compareTo(Book book) {
        if(this.title.compareTo(book.getTitle()) == 0)
            return this.isbn.compareTo(book.getISBN());
        return this.title.compareTo(book.getTitle());
    }

}
