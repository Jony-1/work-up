import java.util.Date;

class Book {

    private String id;
    private String title;
    private String author;
    private String genre;
    private Date year;

    public Book(String id, String title, String author, String genre, Date year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getYear() {
        return year;
    }

    public String getId() {
        return id;
    }


}
