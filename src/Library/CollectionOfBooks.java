package Library;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Martin on 2016-09-18.
 */
public class CollectionOfBooks {
    private ArrayList<Book> books;

    public CollectionOfBooks(ArrayList<Book> collectionOfBooks) {
        books = new ArrayList<>();
        books.addAll(collectionOfBooks);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public ArrayList<Book> getBooksByTitle(String title) {
        ArrayList<Book> booksByTitle = new ArrayList<>();

        for(Book book: books)
            if(book.getTitle().contains(title))
                booksByTitle.add(book);

        Collections.sort(booksByTitle, (book1, book2) -> book1.getTitle().compareTo(book2.getTitle()));

        return booksByTitle;
    }

    public ArrayList<Book> getBooksByAuthor(Author author) {
        ArrayList<Book> booksByAuthor = new ArrayList<>();

        for(Book book: books)
            if(book.getAuthors().contains(author))
                booksByAuthor.add(book);

        Collections.sort(booksByAuthor, (book1, book2) -> book1.getTitle().compareTo(book2.getTitle()));

        return booksByAuthor;
    }
}
