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

    public Author(String name) {
        this.name = name;
    }

    public void setName(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
