
import Library.BooksFileManager;
import Library.*;

import java.io.IOException;
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
    private CollectionOfAuthors libraryAuthors;
    private Scanner scanner;
    private BooksFileManager BFM;
    private String fileName = "Library/library.ser";


    public UserInterface() {
        library = new CollectionOfBooks();
        scanner = new Scanner(System.in);
        BFM = new BooksFileManager();
    }

    public void menu() {
        String answer;
        getLibrary();
        loadAuthors();
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
                    break;
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
            System.out.println("Could not save library");
            e.printStackTrace();
        }
    }

    private void loadAuthors() {
        ArrayList<Author> authors = new ArrayList<>();
        libraryAuthors = new CollectionOfAuthors(authors);
        for (Book book : library.getBooks())
            libraryAuthors.addAuthors(book.getAuthors());
    }

    private void removeBook() {
        String isbn = getUserInput("Enter the ISBN of the book to remove: ");
        library.removeBookByISBN(isbn);
    }

    private void printLibrary() {
        ArrayList<Book> books = library.getBooks();
        Collections.sort(books, new BookComparator());
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    /**
     * Retrieve user input, and make sure its not empty or filled with blank
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

    private void addBook() {
        String title = getUserInput("Enter a title for the book: ");
        String name = getUserInput("Enter an author of the book: ");

        Book book = new Book(title, new ArrayList<>());
        library.addBook(book);

        if (libraryAuthors.authorExist(new Author(name)))
            book.addAuthor(libraryAuthors.getAuthor(name));
        else
            book.addAuthor(new Author(name));

        while (true) {
            name = getUserInput("Enter another author if there are any, else exit (q): ");

            if (name.equals("q"))
                break;
            else if (libraryAuthors.authorExist(new Author(name)))
                book.addAuthor(libraryAuthors.getAuthor(name));
            else
                book.addAuthor(new Author(name));
        }

        libraryAuthors.addAuthors(book.getAuthors());
        System.out.format("'%s' has been added to the library \n", title);
    }

    private void getBooks() {
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

    private void getBooksByTitle() {
        String title = getUserInput("Enter a title for the book: ");
        ArrayList<Book> books = library.getBooksByTitle(title);
        for (Book book : books)
            System.out.println(book.toString());
    }

    private void getBooksByAuthor() {
        String author = getUserInput("Enter the name of the author: ");
        ArrayList<Book> books = library.getBooksByAuthor(new Author(author));
        for (Book book : books)
            System.out.println(book.toString());
    }

    private void getBooksByISBN() {
        String isbn = getUserInput("Enter an ISBN for the book: ");
        ArrayList<Book> books = library.getBooksByISBN(isbn);
        for (Book book : books)
            System.out.println(book.toString());
    }

}
