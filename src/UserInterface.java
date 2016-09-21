
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
public class UserInterface {

    private CollectionOfBooks library;
    private Scanner scanner;
    private BooksFileManager BFM;
    private String fileName = "FileHandler/library.txt";

    public UserInterface(){
        library = new CollectionOfBooks();
        scanner = new Scanner(System.in);
        BFM = new BooksFileManager();
    }

    public void menu() {


        try {
            BFM.deSerializeFromFile(fileName, library);
        }
        catch (IOException e) {
            System.out.println("No library found creating a new one");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        boolean running =true;
            while (running){
                System.out.println("Add book(a), Search book (s), Remove Book (r), Print library (p), Quit program (q)");
                String answer = scanInput();
                switch (answer) {
                    case "a": addBook(); break;
                    case "s": getBooks();break;
                    case "r": removeBook(); break;//remover here
                    case "p": printLibrary(); break;
                    case "q": running = false; break;
                    default:
                            break;
                }
            }

        try {
            BFM.serializeToFile(fileName, library);
            System.out.println("Library saved, exiting...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String scanInput(){
            while (!scanner.hasNextLine()) {
               return scanner.nextLine();
            }
        return scanner.nextLine();

    }

    private void removeBook() {
        System.out.println(" Enter the ISBN of the book to remove: ");
        String isbn = scanInput();
        library.removeBookByISBN(isbn);
    }

    private void printLibrary(){
        ArrayList<Book> books = library.getBooks();
        Collections.sort(books, (book1, book2) -> book1.getISBN().compareTo(book2.getISBN()));
        for (Book book: books
                ) {
            System.out.println(book.toString());

        }
    }

    /**
     * Retrivie user input, and make sure its not empty or filled with blank
     * characters such as space or tab
     * @param question
     * @return
     */
    private String getUserInput(String question){
        String userString = "";
        String temp = "";
        while (temp.isEmpty()){
            System.out.println(question);
            userString = scanInput();
            temp = userString.replaceAll("\\s",""); //Remove blank character
        }
        return userString;
    }

    public void addBook() {
        String title = getUserInput("Enter a title for the book: ");
        String name = getUserInput("Enter an author of the book: ");
        ArrayList<Author> authors = new ArrayList<>();
        authors.add(new Author(name));
        String input = null;
        while(true) {
            input = getUserInput("Enter another author if there are any, else exit (q): ");
            if(input.equals("q"))
                break;
            else
                authors.add(new Author(input));
        }

        library.addBook(new Book(title, authors));
        System.out.format("'%s' has been added to the library \n", title);
    }

    public void getBooks() {
        System.out.println("Search by: Title(1), Author(2), ISBN(3)");
        String answer = scanInput();
        switch (answer) {
            case "1": getBooksByTitle(); break;
            case "2": getBooksByAuthor(); break;
            case "3": getBooksByISBN(); break;
            default: break;
        }
    }

    public void getBooksByTitle() {
        String title = getUserInput("Enter a title for the book: ");
        ArrayList<Book> books = library.getBooksByTitle(title);
        for(Book book: books)
            System.out.println(book.toString());
    }

    public void getBooksByAuthor() {
        System.out.println("Enter the name of the author: ");
        ArrayList<Book> books = library.getBooksByAuthor(new Author(scanInput()));
        for(Book book: books)
            System.out.println(book.toString());
    }

    public void getBooksByISBN() {
        System.out.println("Enter an ISBN for the book: ");
        ArrayList<Book> books = library.getBooksByISBN(scanInput());
        for(Book book: books)
            System.out.println(book.toString());
    }

}
