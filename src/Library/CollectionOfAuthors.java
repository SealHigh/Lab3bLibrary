package Library;

import java.util.ArrayList;

/**
 * Created by timothy on 25/09/16.
 */
/**
 * @author Timothy Holmsten
 * @author Martin Renstrom
 */
public class CollectionOfAuthors {
    private ArrayList<Author> authors;

    public CollectionOfAuthors(ArrayList<Author> authors) {
        if (authors.size() > 0)
            addAuthors(authors);
        else
            this.authors = new ArrayList<>();
    }

    public void addAuthor(Author author) {
        if (!authorExist(author))
            authors.add(author);
    }

    public void addAuthors(ArrayList<Author> authors) {
        for (Author author : authors)
            if (!authorExist(author))
                this.authors.add(author);
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public Author getAuthor(String name) {
        for (Author author : authors)
            if (author.getName().equals(name))
                return author;
        return null;
    }

    public boolean authorExist(Author author) {
        for (Author existingAuthor : authors)
            if (existingAuthor.getName().equals(author.getName())) {
                System.out.println(existingAuthor.toString());
                return true;
            }
        return false;
    }

    @Override
    public String toString() {
        return authors.toString();
    }
}
