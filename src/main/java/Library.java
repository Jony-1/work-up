import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Library {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUI gui = new LibraryGUI();
            gui.showGUI();
        });
    }
}

class LibraryGUI {
    private JFrame frame;
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu viewMenu;
    private JMenuItem exitItem;
    private JMenuItem searchItem;
    private JMenuItem insertItem;
    private JMenuItem updatetItem;
    private CardLayout cardLayout;
    private JPanel cards;
    private JPanel searchPanel;
    private JPanel insertPanel;
    private JPanel updatePanel;
    private JButton searchButton;
    private JTextField searchField;
    private JButton insertButton;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField yearField;
    private LibraryDatabase database;

    public LibraryGUI() {
        frame = new JFrame("Work-up libreria");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchButton = new JButton("Buscar libro");
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        updatePanel = new JPanel();
        updatePanel.setLayout(new GridLayout( 2, 2));


        insertPanel = new JPanel();
        insertPanel.setLayout(new GridLayout(4, 2));
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        genreField = new JTextField(20);
        yearField = new JTextField(20);
        insertButton = new JButton("Insertar libro");
        insertPanel.add(new JLabel("Título: "));
        insertPanel.add(titleField);
        insertPanel.add(new JLabel("Autor: "));
        insertPanel.add(authorField);
        insertPanel.add(new JLabel("Género: "));
        insertPanel.add(genreField);
        insertPanel.add(new JLabel("Año: "));
        insertPanel.add(yearField);
        insertPanel.add(insertButton);

        cards.add(searchPanel, "Buscar libro");
        cards.add(insertPanel, "Insertar libro");
        cards.add(updatePanel, "Actualizar libro");

        panel.add(cards);
        frame.add(panel);

        database = new LibraryDatabase();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar la lógica de búsqueda de libros aquí
                JOptionPane.showMessageDialog(frame, "Función de búsqueda aún no implementada");
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String genre = genreField.getText();
                int year = Integer.parseInt(yearField.getText());

                Book newBook = new Book(title, author, genre, year);
                database.insertBook(newBook);

                // Limpiar los campos de entrada después de la inserción
                titleField.setText("");
                authorField.setText("");
                genreField.setText("");
                yearField.setText("");

                // Proporcionar retroalimentación al usuario, por ejemplo, mostrar un diálogo de mensaje
                JOptionPane.showMessageDialog(frame, "Libro insertado exitosamente");
            }
        });

        menuBar = new JMenuBar();
        fileMenu = new JMenu("Archivo");
        viewMenu = new JMenu("Ver");
        exitItem = new JMenuItem("Salir");
        searchItem = new JMenuItem("Buscar libro");
        insertItem = new JMenuItem("Insertar libro");
        updatetItem = new JMenuItem("actualizar libro");
        fileMenu.add(exitItem);
        viewMenu.add(searchItem);
        viewMenu.add(insertItem);
        viewMenu.add(updatetItem);
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        frame.setJMenuBar(menuBar);

        searchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Buscar libro");
            }
        });

        insertItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Insertar libro");
            }
        });
    }

    public void showGUI() {
        frame.setVisible(true);
    }
}

class Book {
    private String title;
    private String author;
    private String genre;
    private int year;

    public Book(String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    // Getters and setters
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

class LibraryDatabase {
    private Book[] books;
    private int bookCount;

    public LibraryDatabase() {
        books = new Book[100]; // Suponiendo un máximo de 100 libros inicialmente
        bookCount = 0;
    }

    public void insertBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount] = book;
            bookCount++;
        } else {
            // Manejar el caso cuando el arreglo está lleno, por ejemplo, redimensionar el arreglo
        }
    }

    public Book[] getBooks() {
        return books;
    }
}
