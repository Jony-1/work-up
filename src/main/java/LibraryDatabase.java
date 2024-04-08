import java.util.ArrayList;

class LibraryDatabase {
    private ArrayList<Book> books;

    public LibraryDatabase() {
        books = new ArrayList<>();
    }

    public void insertBook(Book book) {
        books.add(book);
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public ArrayList<Book> searchBooks(String searchTerm) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    book.getGenre().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    String.valueOf(book.getYear()).equals(searchTerm)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
}
