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

        Book book = new Book();


        library.addBook(scanner.next());
    }

    public void getBooksByTitle(String title) {

    }

}
