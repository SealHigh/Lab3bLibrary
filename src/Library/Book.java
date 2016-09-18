package Library;

import java.util.ArrayList;

/**
 * Created by Martin on 2016-09-18.
 */
public class Book {

    private String isbn;
    private String title;
    private int edition;
    private double price;
    private ArrayList<Author> authors;

    public Book(){
        authors = new ArrayList<>();
    }

    public void addAuthor(String name){
        authors.add(new Author(name));
    }

    public ArrayList getAuthors(){
        return authors;
    }

}
