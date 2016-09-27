package Library;

import java.util.ArrayList;

/**
 * Created by timothy on 25/09/16.
 *
 * @author Timothy Holmsten
 * @author Martin Renstrom
 */

/**
 * This class is used to make sure we don't create unnecessary authors
 */
public class CollectionOfAuthors {
    private ArrayList<Author> authors;

    /**
     * Initializing a new CollectionOfAuthors and sets authors to a new ArrayList
     */
    public CollectionOfAuthors() {
        this.authors = new ArrayList<>();
    }

    /**
     * Initializing a new CollectionOfAuthors, creates a new ArrayList and adds authors to that ArrayList
     *
     * @param authors The authors to be added
     */
    public CollectionOfAuthors(ArrayList<Author> authors) {
        this.authors = new ArrayList<>();
        addAuthors(authors);
    }

    /**
     * Checks if the author exist, if not, add that author to the ArrayList, else nothing happens
     *
     * @param author The author to be added
     */
    public void addAuthor(Author author) {
        if (!authorExist(author))
            authors.add(author);
    }

    /**
     * Iterating authors, checks if the author already exists, if not, add that author to the ArrayList, else nothing happens
     *
     * @param authors The list to iterate
     */
    public void addAuthors(ArrayList<Author> authors) {
        for (Author author : authors)
            if (!authorExist(author))
                this.authors.add(author);
    }

    /**
     * Returns the ArrayList authors
     *
     * @return ArrayList<Author>
     */
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    /**
     * Iterating authors and checks if there is another author with same name,
     * if it is return that author, else return null
     * @param name Name of the author
     * @return Author if found, else null
     */
    public Author getAuthor(String name) {
        for (Author author : authors)
            if (author.getName().equals(name))
                return author;
        return null;
    }

    /**
     * Used to check if the author already exists by iterating authors and see if they have the same name
     * @param author Author to check if it already exist
     * @return true if found, false if not found
     */
    public boolean authorExist(Author author) {
        for (Author existingAuthor : authors)
            if (existingAuthor.getName().equals(author.getName()))
                return true;
        return false;
    }

    @Override
    public String toString() {
        return authors.toString();
    }
}
