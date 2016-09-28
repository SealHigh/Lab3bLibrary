
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
            answer = scanString(true);
            switch (answer) {
                case "a":
                    addBook();
                    break;
                case "s":
                    searchBooks();
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


    private void getLibrary() {
        try {
            BFM.deSerializeFromFile(fileName, library);
        } catch (IOException e) {
            System.out.println("No library found creating a new one");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
        String isbn = getUserString("Enter the ISBN of the book to remove: ");
        library.removeBookByISBN(isbn);
    }


    private void printLibrary() {
        ArrayList<Book> books = library.getBooks();
        Collections.sort(books);
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }


    private String scanString(Boolean format) {
        if (!format)
            return scanner.nextLine();
        else
            return "" + scanner.nextLine().toLowerCase().charAt(0);
    }

    private String getUserString(String question) {
        String userString = "";
        String temp = "";
        while (temp.isEmpty()) {
            System.out.println(question);
            userString = scanString(false);
            temp = userString.replaceAll("\\s", ""); //Remove blank character
        }
        return userString;
    }

    private int getUserInt(String question) {
        System.out.println(question);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Int, please!");
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    private double getUserDouble(String question) {
        System.out.println(question);
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.println("Double, please!");
        }
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }

    private void addBook() {
        String title = getUserString("Enter a title for the book: ");
        String name = getUserString("Enter an author of the book: ");
        Book tmpBook = new Book(title, new ArrayList<>(), 0, 0);

        //library.addBook(book);
        if (libraryAuthors.authorExist(new Author(name)))
            tmpBook.addAuthor(libraryAuthors.getAuthor(name));
        else
            tmpBook.addAuthor(new Author(name));

        while (true) {
            name = getUserString("Enter another author if there are any, else exit (q): ");
            if (name.equals("q"))
                break;
            else if (libraryAuthors.authorExist(new Author(name)))
                tmpBook.addAuthor(libraryAuthors.getAuthor(name));
            else
                tmpBook.addAuthor(new Author(name));
        }

        int edition = getUserInt("Enter an edition for the book: ");
        double price = getUserDouble("Enter a price for the book: ");
        Book book = new Book(tmpBook.getTitle(), tmpBook.getAuthors(), edition, price);
        library.addBook(book);

        libraryAuthors.addAuthors(tmpBook.getAuthors());
        System.out.format("'%s' has been added to the library \n", title);
    }


    private void searchBooks() {
        System.out.println("Search by: Title(t), Author(a), ISBN(i)");
        String answer = scanString(true);
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
        String title = getUserString("Enter a title for the book: ");
        ArrayList<Book> books = library.getBooksByTitle(title);
        for (Book book : books)
            System.out.println(book.toString());
    }

    private void getBooksByAuthor() {
        String author = getUserString("Enter the name of the author: ");
        ArrayList<Book> books = library.getBooksByAuthor(new Author(author));
        for (Book book : books)
            System.out.println(book.toString());
    }

    private void getBooksByISBN() {
        String isbn = getUserString("Enter an ISBN for the book: ");
        ArrayList<Book> books = library.getBooksByISBN(isbn);
        for (Book book : books)
            System.out.println(book.toString());
    }
}
