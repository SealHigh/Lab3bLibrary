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

/**
 * This class is used to create book that are stored in CollectionOfBooks
 */
public class Book implements Serializable, Comparable<Book> {

    private String isbn;
    private String title;
    private int edition;
    private double price;
    private ArrayList<Author> authors;

    /**
     * Initialize everything for book except ISBN
     *
     * @param title   Title of  the book
     * @param authors Authors of the book
     * @param edition Edition of the book
     * @param price   Price of the book
     */
    public Book(String title, ArrayList<Author> authors, int edition, double price) {
        this.title = title;
        this.authors = new ArrayList<>();
        this.authors.addAll(authors);
        this.edition = edition;
        this.price = price;
    }

    /**
     * Adds an author to the book
     *
     * @param author Author to add
     */
    public void addAuthor(Author author) {
        authors.add(author);
    }

    /**
     * Returns an ArrayList<Author> which represents the book's authors
     *
     * @return ArrayList<Author> of the book's authors
     */
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    /**
     * Gets the title of the book
     *
     * @return Title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the ISBN of the book
     *
     * @return ISBN of the book
     */
    public String getISBN() {
        return isbn;
    }

    /**
     * @return Returns a formatted string that represents the books title, edition, authors, ISBN and price in that order
     */
    @Override
    public String toString() {
        return String.format("Title: %s, Edition: %d, Authors: %s, ISBN: %s, Price: %.2f",
                title,
                edition,
                authors,
                isbn,
                price);
    }

    /**
     * Sets the book ISBN
     * @param isbn The ISBN that will be set to the book
     */
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }


    /**
     * Compare each book by name, if same name sort by isbn
     *
     * @param book The other book to compare
     * @return
     */
    @Override
    public int compareTo(Book book) {
        if (this.title.compareTo(book.getTitle()) == 0)
            return this.isbn.compareTo(book.getISBN());
        return this.title.compareTo(book.getTitle());
    }

}
