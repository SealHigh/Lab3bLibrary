package Library;

import java.io.Serializable;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Martin on 2016-09-18.
 */

/**
 * @author Timothy Holmsten
 * @author Martin Renstrom
 */
public class CollectionOfBooks implements Serializable {

    private ArrayList<Book> books;

    public CollectionOfBooks() {
        books = new ArrayList<>();
    }

    /**
     * This method will generate an ISBN to the book so the book will always have an unique ISBN
     * @param book The book to add
     */
    public void addBook(Book book) {
        if (books.size() == 0) //First book gets ISBN 0 the rest gets lowest possible unique ISBN
            book.setISBN("0");
        else {
            for (int i = 0; i < books.size() + 1; i++) {
                boolean exist = false;
                for (Book b : books) {
                    if (Integer.toString(i).equals(b.getISBN()))
                        exist = true;
                }
                if (!exist)
                    book.setISBN(Integer.toString(i));
            }
        }

        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void removeBookByISBN(String isbn) {
        for (Book book : books)
            if (book.getISBN().equals(isbn)) {
                removeBook(book);
                break;
            }
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Book> getBooksByTitle(String title) {
        ArrayList<Book> booksByTitle = new ArrayList<>();

        for (Book book : books)
            if (book.getTitle().toLowerCase().trim().contains(title))
                booksByTitle.add(book);

        Comparator comparator = new BookComparator();

        Collections.sort(booksByTitle, comparator);

        return booksByTitle;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Book> getBooksByAuthor(Author author) {
        ArrayList<Book> booksByAuthor = new ArrayList<>();

        for (Book book : books)
            if (book.getAuthors().contains(author))
                booksByAuthor.add(book);

        Comparator comparator = new BookComparator();

        Collections.sort(booksByAuthor, comparator);

        return booksByAuthor;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Book> getBooksByISBN(String isbn) {
        ArrayList<Book> booksByISBN = new ArrayList<>();

        for (Book book : books) {
            if (book.getISBN().toLowerCase().trim().contains(isbn))
                booksByISBN.add(book);
        }

        Comparator comparator = new BookComparator();

        Collections.sort(booksByISBN, comparator);

        return booksByISBN;
    }

    @Override
    public String toString() {
        return books.toString();
    }
}
