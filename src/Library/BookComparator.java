package Library;

import java.util.Comparator;

/**
 * Created by timothy on 23/09/16.
 */
/**
 * @author Timothy Holmsten
 * @author Martin Renstrom
 */
public class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        if(book1.getTitle().compareTo(book2.getTitle()) == 0)
            return book1.getISBN().compareTo(book2.getISBN());
        return book1.getTitle().compareTo(book2.getTitle());
    }
}
