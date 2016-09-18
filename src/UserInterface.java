import Library.Book;
import Library.CollectionOfBooks;

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
                System.out.println("Add book(a), Search book (s), Remove Book (r)");
                String answer = getInput();
                switch (answer) {
                    case "a": addBook(); break;
                    case "s": getBooksByTitle();
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

    public void addBook() {
        System.out.println("Enter a title for the book: ");
        String title = getInput();

        library.addBook(new Book(title));
        System.out.println("'"+title+"'"+" has been added to the library");
    }

    public void getBooksByTitle() {
        System.out.println("Enter a title for the book: ");
        String title = getInput();

    }

}
