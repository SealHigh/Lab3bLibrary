
import FileHandler.BooksFileManager;
import Library.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Martin on 2016-09-18.
 */

/**
 * @author Timothy Holmsten
 * @author Martin Renstrom
 */
public class UserInterface {

    private CollectionOfBooks library;
    private Scanner scanner;
    private BooksFileManager BFM;
    private String fileName = "FileHandler/library.ser";


    public UserInterface() {
        library = new CollectionOfBooks();
        scanner = new Scanner(System.in);
        BFM = new BooksFileManager();
    }

    public void menu() {
        String answer;
        getLibrary();
        do {
            System.out.println("Add book(a), Search book (s), Remove Book (r), Print library (p), Quit program (q)");
            answer = scanInput(true);
            switch (answer) {
                case "a":
                    addBook();
                    break;
                case "s":
                    getBooks();
                    break;
                case "r":
                    removeBook();
                    break;//remover here
                case "p":
                    printLibrary();
                    break;
                default:
                    break;
            }
        } while (!answer.equals("q"));
        saveLibrary();
    }

    /**
     * Gets user input
     *
     * @param format Format the string to only first char if true
     * @return The string user typed, or a format version of it
     */
    private String scanInput(Boolean format) {
        if (!format)
            return scanner.nextLine();
        else
            return "" + scanner.nextLine().toLowerCase().charAt(0);
    }

    /**
     * Use BookFileManager to retrieve an already existing library
     * and handle possible exceptions.
     */
    private void getLibrary() {
        try {
            BFM.deSerializeFromFile(fileName, library);
        } catch (IOException e) {
            System.out.println("No library found creating a new one");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save object library to a file using BookFileManager
     * and handle possible exceptions
     */
    private void saveLibrary() {
        try {
            BFM.serializeToFile(fileName, library);
            System.out.println("Library saved, exiting...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeBook() {
        System.out.println(" Enter the ISBN of the book to remove: ");
        String isbn = scanInput(false);
        library.removeBookByISBN(isbn);
    }

    private void printLibrary() {
        ArrayList<Book> books = library.getBooks();
        Collections.sort(books, (book1, book2) -> book1.getISBN().compareTo(book2.getISBN()));
        for (Book book : books
                ) {
            System.out.println(book.toString());

        }
    }

    /**
     * Retrivie user input, and make sure its not empty or filled with blank
     * characters such as space or tab
     *
     * @param question
     * @return
     */
    private String getUserInput(String question) {
        String userString = "";
        String temp = "";
        while (temp.isEmpty()) {
            System.out.println(question);
            userString = scanInput(false);
            temp = userString.replaceAll("\\s", ""); //Remove blank character
        }
        return userString;
    }

    public void addBook() {
        String title = getUserInput("Enter a title for the book: ");
        String name = getUserInput("Enter an author of the book: ");
        ArrayList<Author> authors = new ArrayList<>();
        authors.add(new Author(name));
        String input = null;
        while (true) {
            input = getUserInput("Enter another author if there are any, else exit (q): ");
            if (input.equals("q"))
                break;
            else
                authors.add(new Author(input));
        }

        library.addBook(new Book(title, authors));
        System.out.format("'%s' has been added to the library \n", title);
    }

    public void getBooks() {
        System.out.println("Search by: Title (t), Author(a), ISBN(i)");
        String answer = scanInput(true);
        switch (answer) {
            case "t":
                getBooksByTitle();
                break;
            case "a":
                getBooksByAuthor();
                break;
            case "i":
                getBooksByISBN();
                break;
            default:
                break;
        }
    }

    public void getBooksByTitle() {
        String title = getUserInput("Enter a title for the book: ");
        ArrayList<Book> books = library.getBooksByTitle(title);
        for (Book book : books)
            System.out.println(book.toString());
    }

    public void getBooksByAuthor() {
        System.out.println("Enter the name of the author: ");
        ArrayList<Book> books = library.getBooksByAuthor(new Author(scanInput(false)));
        for (Book book : books)
            System.out.println(book.toString());
    }

    public void getBooksByISBN() {
        System.out.println("Enter an ISBN for the book: ");
        ArrayList<Book> books = library.getBooksByISBN(scanInput(false));
        for (Book book : books)
            System.out.println(book.toString());
    }

}
