package Library;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Martin on 2016-09-18.
 */
public class CollectionOfBooks {
    private ArrayList<Book> books;

    public CollectionOfBooks() {
        books = new ArrayList<>();
    }

    /**
     * Adds book with given title and a unique ISBN
     */
    public void addBook(Book book) {
        if(books.size() == 0) //First book gets ISBN 0 the rest gets lowest possible unique ISBN
            book.setIsbn("0");
        else {
            for (int i = 0; i < books.size() + 1; i++) {
                boolean exist = false;
                for (Book b: books) {
                    if (Integer.toString(i).equals(b.getISBN()))
                        exist = true;
                }
                if (!exist)
                    book.setIsbn(Integer.toString(i));
            }
        }
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

    public void removeBookByISBN(String isbn) {
        for(Book book: books)
            if(book.getISBN().equals(isbn)) {
                removeBook(book);
                break;
            }
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

    public ArrayList<Book> getBooksByTitle(String title) {
        ArrayList<Book> booksByTitle = new ArrayList<>();

        for(Book book: books)
            if(book.getTitle().toLowerCase().trim().contains(title))
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
            if(book.getISBN().toLowerCase().trim().contains(isbn))
                booksByISBN.add(book);
        }

        Collections.sort(booksByISBN, (book1, book2) -> book1.getISBN().compareTo(book2.getISBN()));

        return booksByISBN;
    }

    public void serializeToFile(String filename) throws IOException {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(
                    new FileOutputStream(filename));
            out.writeObject(books);
        }
        finally {
            try {
                if(out != null)	out.close();
            } catch(Exception e) {e.printStackTrace();}
        }
    }

    @SuppressWarnings("unchecked")
    public void deSerializeFromFile(String filename) throws IOException, ClassNotFoundException {

        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(
                    new FileInputStream(filename));
            // readObject returns a reference of type Object, hence the down-cast
            books = (ArrayList<Book>) in.readObject();
        }
        finally {
            try {
                if(in != null)	in.close();
            } catch(Exception e) {e.printStackTrace();}
        }
    }
}
