import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class LibraryGUI {
    private JFrame frame;
    private JPanel panel;
    private JPanel panelUser;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu viewMenu;
    private JMenu userMenu;
    private JMenuItem exitItem;
    private JMenuItem searchItem;
    private JMenuItem insertItem;
    private JMenuItem updateItem;
    private JMenuItem searchUserItem;
    private JMenuItem insertUserItem;
    private JMenuItem updateUserItem;
    private JMenuItem viewBooksItem;
    private JMenuItem viewUsersItem;

    private JTable bookTable;
    private JTable userTable;

    private JPanel viewBooksPanel;
    private JPanel viewUsersPanel;
    private CardLayout cardLayout;
    private JPanel cards;
    private JPanel cardsUser;
    private JPanel searchPanel;
    private JPanel searchUserPanel;

    private JPanel insertPanel;
    private JPanel insertUserPanel;
    private JPanel updatePanel;
    private JPanel updateUserPanel;
    private JButton searchButton;
    private JButton searchUserButton;

    private JTextField searchField;
    private JTextField searchUserField;
    private JButton insertButton;
    private JButton insertUserButton;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField yearField;
    private JTextField usernameField;
    private JTextField lastnameField;
    private LibraryDatabase database;
    private UserDatabase userdatabase;

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

        //user
        searchUserPanel = new JPanel();
        searchUserPanel.setLayout(new FlowLayout());
        searchUserButton = new JButton("Buscar usuario");
        searchUserField = new JTextField(20);
        searchUserPanel.add(searchUserField);
        searchUserPanel.add(searchUserButton);

        updateUserPanel = new JPanel();
        updateUserPanel.setLayout(new GridLayout(2, 2));

        insertUserPanel = new JPanel();
        insertUserPanel.setLayout(new GridLayout(5, 6));
        usernameField = new JTextField(1);
        lastnameField = new JTextField(1);

        insertUserButton = new JButton("Insertar usuario");
        insertUserPanel.add(new JLabel("Nombre de usuario: "));
        insertUserPanel.add(usernameField);
        insertUserPanel.add(new JLabel("Apellido de usuario: "));
        insertUserPanel.add(lastnameField);
        insertUserPanel.add(insertUserButton);

        cards.add(searchUserPanel, "Buscar usuario");
        cards.add(insertUserButton, "Insertar usuario");

        database = new LibraryDatabase();
        userdatabase = new UserDatabase();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                ListNode foundBooks = database.searchBooks(searchTerm);
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

       /*
        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchUserTerm = searchUserField.getText();
                ListNode foundUsers = userdatabase.searchUsers(searchUserTerm);
                displayUsers(foundUsers);
            }
        });


        insertUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String lastname = lastnameField.getText();


                User newUser = new User(username, lastname);
                userdatabase.insertUser(newUser);

                JOptionPane.showMessageDialog(frame, "Usuario insertado exitosamente");
            }
        });

        */

        // Menús
        menuBar = new JMenuBar();
        fileMenu = new JMenu("ayuda");
        exitItem = new JMenuItem("Salir");

        userMenu = new JMenu("Usuario");
        searchUserItem = new JMenuItem("Buscar usuario");
        insertUserItem = new JMenuItem("Insertar usuario");
        updateUserItem = new JMenuItem("Actualizar usuario");
        viewUsersItem = new JMenuItem("Ver Usuarios");

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

        userMenu.add(searchUserItem);
        userMenu.add(insertUserItem);
        userMenu.add(updateUserItem);
        userMenu.add(viewUsersItem);

        menuBar.add(fileMenu);
        menuBar.add(userMenu);
        menuBar.add(viewMenu);
        frame.setJMenuBar(menuBar);

        //user
        searchUserItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Buscar usuario");
            }
        });

        insertUserItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Insertar usuario");
            }
        });

        //libro
        insertItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Insertar libro");
            }
        });

        searchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Buscar libro");
            }
        });



        //vista libro
        viewBooksItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListNode allBooks = database.getAllBooks();
                displayBooks(allBooks);
            }
        });

    }


    private void displayUsers(ArrayList<User> users) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Username");
        model.addColumn("Last Name");

        for (User user : users) {
            model.addRow(new Object[]{user.getUsername(), user.getLastname()});
        }

        if (userTable == null) {
            userTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(userTable);
            viewUsersPanel = new JPanel();
            viewUsersPanel.setLayout(new BorderLayout());
            viewUsersPanel.add(scrollPane, BorderLayout.CENTER);
            cards.add(viewUsersPanel, "Consultar usuarios");
        } else {
            userTable.setModel(model);
        }

        cardLayout.show(cards, "Consultar usuarios");
    }


    private void displayBooks(ListNode books) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Título");
        model.addColumn("Autor");
        model.addColumn("Género");
        model.addColumn("Año");

        ListNode current = books;
        while (current != null) {
            Book book = current.getBook();
            model.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getGenre(), book.getYear()});
            current = current.getNext();
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



    public static void main(String[] args) {
        LibraryGUI libraryGUI = new LibraryGUI();
        libraryGUI.showGUI();
    }


    public void showGUI() {
        frame.setVisible(true);
    }


}
