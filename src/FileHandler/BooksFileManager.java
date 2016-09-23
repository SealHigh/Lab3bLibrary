package FileHandler;

import Library.Book;
import Library.CollectionOfBooks;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by timothy on 18/09/16.
 */
public class BooksFileManager {

    /**
     * Call this method before the application exits, to store the books,
     * in serialized form, on file the specified file.
     */
    public void serializeToFile(String filename, CollectionOfBooks library) throws IOException {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            //out.writeObject(library.getBooks());
            out.writeObject(library);
        }
        finally {
            try {
                if(out != null)	out.close();
            } catch(Exception e) {}
        }
    }


    /**
     * Call this method at startup of the application, to deserialize the books
     * from file the specified file.
     */
    @SuppressWarnings("unchecked")
    public void deSerializeFromFile(String filename, CollectionOfBooks library) throws IOException, ClassNotFoundException {

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            //library.setBooks((ArrayList<Book>) in.readObject());
            library.setBooks(((CollectionOfBooks) in.readObject()).getBooks());
        }
        finally {
            try {
                if(in != null)	in.close();
            } catch(Exception e) {}
        }
    }
}
