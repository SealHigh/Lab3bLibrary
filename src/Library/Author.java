package Library;

import java.io.Serializable;

/**
 * Created by Martin on 2016-09-18.
 */

/**
 * @author Timothy Holmsten
 * @author Martin Renstrom
 */
public class Author implements Serializable {

    private String name;

    /**
     * Initializing a new Author with a set name
     *
     * @param name Name of the author
     */
    public Author(String name) {
        this.name = name;
    }

    /**
     * Gets the author's name
     *
     * @return Author's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Author's name
     */
    @Override
    public String toString() {
        return name;
    }
}
