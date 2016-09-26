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
     *
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

    /**
     * Removes a given book if the book exist from the books list
     *
     * @param book The book to remove
     */
    public void removeBook(Book book) {
        books.remove(book);
    }

    /**
     * Searches books for a specific ISBN of a book,
     * if the ISBN was found, remove the book that is associated with the ISBN
     *
     * @param isbn The ISBN to search for
     */
    public void removeBookByISBN(String isbn) {
        for (Book book : books)
            if (book.getISBN().equals(isbn)) {
                removeBook(book);
                break;
            }
    }

    /**
     * Set the books ArrayList to a specific ArrayList
     *
     * @param books The ArrayList to be set of books
     */
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    /**
     * Returns all the elements in the books ArrayList
     *
     * @return All elements in books
     */
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * Searches books that contains a part of a title,
     * the books that contained the title gets sorted using BookComparator's compare and then returned
     *
     * @param title What title to search for
     * @return A sorted ArrayList of books that contained the title
     */
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

    /**
     * Searches books that was written by a specific author
     * the books that was written by the author gets sorted using BookComparator's compare and then returned
     *
     * @param author The author that wrote the book
     * @return A sorted ArrayList of books that was written by the author
     */
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

    /**
     * Searches books that contains a part of an ISBN,
     * the books that contained the ISBN gets sorted using BookComparator's compare and then returned
     * @param isbn The ISBN to search for
     * @return A sorted ArrayList of books that contained the ISBN
     */
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
