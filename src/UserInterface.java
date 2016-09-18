import Library.Book;
import Library.CollectionOfBooks;

import java.util.Scanner;

/**
 * Created by Martin on 2016-09-18.
 */
public class UserInterface {

    private CollectionOfBooks library;
    private Scanner scanner;
    public void menu() {

    }

    public void addBook() {



        System.out.println("Enter a title for the book: ");
        Book book = new Book(scanner.next()); //Creates a book with the title
        library.addBook(book);
    }

    public void getBooksByTitle(String title) {

    }

}
