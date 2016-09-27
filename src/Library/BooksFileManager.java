package Library;

import Library.CollectionOfBooks;

import java.io.*;

/**
 * Created by timothy on 18/09/16.
 */

/**
 * @author Timothy Holmsten
 * @author Martin Renstrom
 */
public class BooksFileManager {

    /**
     * Saves all books in library to a file
     */
    public void serializeToFile(String filename, CollectionOfBooks library) throws IOException {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(library);
        } finally {
            try {
                if (out != null) out.close();
            } catch (Exception e) {
            }
        }
    }


    /**
     * Fills library with books from already existing file
     */
    @SuppressWarnings("unchecked")
    public void deSerializeFromFile(String filename, CollectionOfBooks library) throws IOException, ClassNotFoundException {

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            library.setBooks(((CollectionOfBooks) in.readObject()).getBooks());
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception e) {
            }
        }
    }
}
