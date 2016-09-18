package Library;

/**
 * Created by Martin on 2016-09-18.
 */
public class Author {

    private String name;

    public Author(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
