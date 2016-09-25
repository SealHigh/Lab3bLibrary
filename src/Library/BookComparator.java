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
        int cmpPoints = 0;
        cmpPoints += book1.getISBN().compareTo(book2.getISBN());
        cmpPoints += book1.getTitle().compareTo(book2.getTitle());
        return cmpPoints;
    }
}
