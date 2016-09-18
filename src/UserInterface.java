
import Library.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Martin on 2016-09-18.
 */
public class UserInterface {

    private CollectionOfBooks library;
    private Scanner scanner;

    public UserInterface(){
        library = new CollectionOfBooks();
        scanner = new Scanner(System.in);
    }

    public void menu() {
            while (true){
                System.out.println("Add book(a), Search book (s), Remove Book (r), Print library (p)");
                String answer = getInput();
                switch (answer) {
                    case "a": addBook(); break;
                    case "s": getBooks();
                    case "r": removeBook(); break;//remover here
                    case "p": printLibrary(); break;
                    default:
                            break;
                }
            }
    }

    private String getInput(){
            while (!scanner.hasNext()) {
               return scanner.nextLine();
            }

        return scanner.next();

    }

    public void removeBook() {
        Book book = library.getBook(0);
        if(book != null)
            library.removeBook(book);
    }

    public void printLibrary(){
        library.printLibrary();
    }

    public void addBook() {
        System.out.println("Enter a title for the book: ");
        String title = getInput();

        library.addBook(new Book(title));
        System.out.println("'"+title+"'"+" has been added to the library");
    }

    public void getBooks() {
        System.out.println("Search by: Title(1), Author(2), ISBN(3)");
        String answer = getInput();
        switch (answer) {
            case "1": getBooksByTitle(); break;
            case "2": getBooksByAuthor(); break;
            case "3": getBooksByISBN(); break;
            default: break;
        }
    }

    public void getBooksByTitle() {
        System.out.println("Enter a title for the book: ");
        ArrayList<Book> books = library.getBooksByTitle(getInput());
        for(Book book: books)
            System.out.println(book.toString());
    }

    public void getBooksByAuthor() {
        System.out.println("Enter the name of the author: ");
        ArrayList<Book> books = library.getBooksByAuthor(new Author(getInput()));
        for(Book book: books)
            System.out.println(book.toString());
    }

    public void getBooksByISBN() {
        System.out.println("Enter an ISBN for the book: ");
        ArrayList<Book> books = library.getBooksByISBN(getInput());
        for(Book book: books)
            System.out.println(book.toString());
    }

}
