package Library;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Martin on 2016-09-18.
 */
public class CollectionOfBooks {
    private ArrayList<Book> books;

    public CollectionOfBooks() {
        books = new ArrayList<>();
//        books.addAll(collectionOfBooks);
    }

    public void addBook(Book book) {
        try {
            books.add(book);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void printLibrary(){
        for (Book book: books
             ) {
            System.out.println(book.toString());

        }
    }

    public Book getBook(int i){
        try {
            return books.get(i);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return null;
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

    public ArrayList<Book> getBooksByISBN(String isbn) {
        ArrayList<Book> booksByISBN = new ArrayList<>();

        for(Book book: books) {
            if(book.getISBN().contains(isbn))
                booksByISBN.add(book);
        }

        Collections.sort(booksByISBN, (book1, book2) -> book1.getISBN().compareTo(book2.getISBN()));

        return booksByISBN;
    }
}
