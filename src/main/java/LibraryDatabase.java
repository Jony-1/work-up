import java.util.List;

public class LibraryDatabase {
    private ListNode head;
    private Graph userGraph; // Grafo para usuarios
    private Graph bookGraph; // Grafo para libros

    public LibraryDatabase() {
        this.head = null;
        this.userGraph = new Graph();
        this.bookGraph = new Graph();
    }

    public void insertBook(Book book) {
        ListNode newNode = new ListNode(book);
        if (head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }

        // Agregar conexiones al grafo de libros
        String[] genres = book.getGenre().split(", ");
        for (String genre : genres) {
            bookGraph.addEdge(book.getTitle(), genre, 1); // Utilizar el título del libro como fuente
        }
    }


    public void initializeBooks() {
        // Aquí puedes agregar libros predefinidos
        Book book1 = new Book("1", "El principito", "Antoine de Saint-Exupéry", "Fábula", 1943);
        Book book2 = new Book("2", "Cien años de soledad", "Gabriel García Márquez", "Realismo mágico", 1967);

        // Insertar los libros en la base de datos
        insertBook(book1);
        insertBook(book2);
        // Puedes agregar más libros según sea necesario
    }

    public Book getBookById(String id) {
        ListNode current = head;
        while (current != null) {
            if (current.getBook().getId().equals(id)) {
                return current.getBook();
            }
            current = current.getNext();
        }
        return null;
    }

    public ListNode getAllBooks() {
        return head;
    }

    // Método para encontrar libros por género utilizando el grafo
    public List<String> findBooksByGenre(String genre) {
        return bookGraph.getShortestPath(genre, null);
    }


}