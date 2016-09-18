import Library.Book;

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
    public void serializeToFile(String filename, ArrayList<Book> theBooks) throws IOException {

        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(
                    new FileOutputStream(filename));
            out.writeObject(theBooks);
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
    public void deSerializeFromFile(String filename, ArrayList<Book> theBooks) throws IOException, ClassNotFoundException {

        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(
                    new FileInputStream(filename));
            // readObject returns a reference of type Object, hence the down-cast
            theBooks = (ArrayList<Book>) in.readObject();
        }
        finally {
            try {
                if(in != null)	in.close();
            } catch(Exception e) {}
        }
    }
}
