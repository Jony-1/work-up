import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class LibraryGUI {
    private JFrame frame;
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu viewMenu;
    private JMenu userMenu;
    private JMenuItem exitItem;
    private JMenuItem searchItem;
    private JMenuItem insertItem;
    private JMenuItem updateItem;
    private JMenuItem insertUserItem;
    private JMenuItem updateUserItem;
    private JMenuItem deleteUserItem;
    private JMenuItem viewBooksItem;

    private JTable bookTable;

    private JPanel viewBooksPanel;
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
        frame.setSize(600, 400);
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
        updatePanel.setLayout(new GridLayout(2, 2));

        insertPanel = new JPanel();
        insertPanel.setLayout(new GridLayout(5, 6));
        titleField = new JTextField(1);
        authorField = new JTextField(1);
        genreField = new JTextField(1);
        yearField = new JTextField(1);
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
                String searchTerm = searchField.getText();
                ArrayList<Book> foundBooks = database.searchBooks(searchTerm);
                displayBooks(foundBooks);
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

                JOptionPane.showMessageDialog(frame, "Libro insertado exitosamente");
            }
        });

        // Menús
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Archivo");
        exitItem = new JMenuItem("Salir");

        userMenu = new JMenu("Usuario");
        insertUserItem = new JMenuItem("Insertar usuario");
        updateUserItem = new JMenuItem("Actualizar usuario");
        deleteUserItem = new JMenuItem("Eliminar usuario");

        viewMenu = new JMenu("Libros");
        searchItem = new JMenuItem("Buscar libro");
        insertItem = new JMenuItem("Insertar libro");
        updateItem = new JMenuItem("Actualizar libro");
        viewBooksItem = new JMenuItem("Ver libros");

        fileMenu.add(exitItem);
        viewMenu.add(searchItem);
        viewMenu.add(insertItem);
        viewMenu.add(updateItem);
        viewMenu.add(viewBooksItem);

        userMenu.add(insertUserItem);
        userMenu.add(updateUserItem);
        userMenu.add(deleteUserItem);

        menuBar.add(fileMenu);
        menuBar.add(userMenu);
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

        viewBooksItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Book> allBooks = database.getAllBooks();
                displayBooks(allBooks);
            }
        });
    }

    private void displayBooks(ArrayList<Book> books) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Título");
        model.addColumn("Autor");
        model.addColumn("Género");
        model.addColumn("Año");

        for (Book book : books) {
            model.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getGenre(), book.getYear()});
        }

        if (bookTable == null) {
            bookTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(bookTable);
            viewBooksPanel = new JPanel();
            viewBooksPanel.setLayout(new BorderLayout());
            viewBooksPanel.add(scrollPane, BorderLayout.CENTER);
            cards.add(viewBooksPanel, "Consultar libros");
        } else {
            bookTable.setModel(model);
        }

        cardLayout.show(cards, "Consultar libros");
    }

    public void showGUI() {
        frame.setVisible(true);
    }
}


