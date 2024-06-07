public class Book {
    private String title;
    private String author;
    private String genre;
    private int year;
    private int bookId;

    public Book(String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.bookId = generateBookId(); // Método para generar un ID único para cada libro
    }

    private int generateBookId() {
        // Implementa la lógica para generar un ID único
        return (int) (Math.random() * 10000); // Ejemplo simple
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int getBookId() {
        return bookId;
    }
}
