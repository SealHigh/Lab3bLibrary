package Library;

import java.util.ArrayList;

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
        ArrayList<Book> booksByTitle;

        for(Book book: books)
            if(book.getTitle().contains(title))
        return null;
    }
}
