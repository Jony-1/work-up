import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JMenuItem searchUserItem;
    private JMenuItem insertUserItem;
    private JMenuItem updateUserItem;
    private JMenuItem viewBooksItem;
    private JMenuItem viewUsersItem;
    private JMenuItem viewLoansItem;

    private JTable bookTable;
    private JTable userTable;

    private JPanel viewBooksPanel;
    private JPanel viewUsersPanel;
    private JPanel viewLoansPanel;
    private CardLayout cardLayout;
    private JPanel cards;
    private JPanel searchPanel;
    private JPanel searchUserPanel;
    private JPanel insertPanel;
    private JPanel insertUserPanel;
    private JPanel insertLoanPanel;
    private JButton searchButton;
    private JButton searchUserButton;
    private JButton insertButton;
    private JButton insertUserButton;
    private JButton insertLoanButton;
    private JTextField searchField;
    private JTextField searchUserField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField yearField;
    private JTextField usernameField;
    private JTextField lastnameField;
    private JTextField iduserField;
    private LibraryDatabase database;
    private UserDatabase userDatabase;

    public LibraryGUI() {
        frame = new JFrame("Work-up libreria");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Book search panel
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchButton = new JButton("Buscar libro");
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Book insert panel
        insertPanel = new JPanel();
        insertPanel.setLayout(new GridLayout(5, 2));
        titleField = new JTextField(10);
        authorField = new JTextField(10);
        genreField = new JTextField(10);
        yearField = new JTextField(10);
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

        // User search panel
        searchUserPanel = new JPanel();
        searchUserPanel.setLayout(new FlowLayout());
        searchUserButton = new JButton("Buscar usuario");
        searchUserField = new JTextField(20);
        searchUserPanel.add(searchUserField);
        searchUserPanel.add(searchUserButton);

        // User insert panel
        insertUserPanel = new JPanel();
        insertUserPanel.setLayout(new GridLayout(4, 2));
        usernameField = new JTextField(10);
        lastnameField = new JTextField(10);
        iduserField = new JTextField(10);
        insertUserButton = new JButton("Insertar usuario");
        insertUserPanel.add(new JLabel("Nombre de usuario: "));
        insertUserPanel.add(usernameField);
        insertUserPanel.add(new JLabel("Apellido de usuario: "));
        insertUserPanel.add(lastnameField);
        insertUserPanel.add(new JLabel("Id de usuario: "));
        insertUserPanel.add(iduserField);
        insertUserPanel.add(insertUserButton);

        // Loan insert panel
        insertLoanPanel = new JPanel();
        insertLoanPanel.setLayout(new GridLayout(3, 2));
        JTextField loanUserField = new JTextField(10);
        JTextField loanBookField = new JTextField(10);
        insertLoanButton = new JButton("Registrar préstamo");
        insertLoanPanel.add(new JLabel("ID de usuario: "));
        insertLoanPanel.add(loanUserField);
        insertLoanPanel.add(new JLabel("ID de libro: "));
        insertLoanPanel.add(loanBookField);
        insertLoanPanel.add(insertLoanButton);

        // Agregar paneles a cards
        cards.add(searchPanel, "Buscar libro");
        cards.add(insertPanel, "Insertar libro");
        cards.add(searchUserPanel, "Buscar usuario");
        cards.add(insertUserPanel, "Insertar usuario");
        cards.add(insertLoanPanel, "Registrar préstamo");

        panel.add(cards);
        frame.add(panel);

        // Crear bases de datos
        database = new LibraryDatabase();
        userDatabase = new UserDatabase();

        // Action listener para registro de préstamos
        insertLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loanUserId = loanUserField.getText();
                String loanBookId = loanBookField.getText();

                // Obtener usuario y libro correspondientes
                User loanUser = userDatabase.getUserById(loanUserId);
                Book loanBook = database.getBookById(loanBookId);

                if (loanUser != null && loanBook != null) {
                    // Crear préstamo y agregarlo a la base de datos
                    Loan newLoan = new Loan(loanUser, loanBook, Loan.getCurrentDate(), ""); // Fecha de devolución vacía inicialmente
                    LoanDatabase loanData = new LoanDatabase();
                    loanData.insertLoan(newLoan);
                    JOptionPane.showMessageDialog(frame, "Préstamo registrado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(frame, "ID de usuario o libro no válido");
                }
            }
        });

        // Crear menú y elementos
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Archivo");
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
        viewLoansItem = new JMenuItem("Ver préstamos");

        // Agregar elementos al menú
        fileMenu.add(exitItem);

        userMenu.add(searchUserItem);
        userMenu.add(insertUserItem);
        userMenu.add(updateUserItem);
        userMenu.add(viewUsersItem);

        viewMenu.add(searchItem);
        viewMenu.add(insertItem);
        viewMenu.add(updateItem);
        viewMenu.add(viewBooksItem);
        viewMenu.add(viewLoansItem);

        // Agregar menú al menú principal
        menuBar.add(fileMenu);
        menuBar.add(userMenu);
        menuBar.add(viewMenu);
        frame.setJMenuBar(menuBar);

        // Mostrar la ventana
        frame.setVisible(true);

        // Action listeners para los elementos del menú
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

        viewBooksItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListNode allBooks = database.getAllBooks();
                displayBooks(allBooks);
            }
        });

        viewLoansItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Registrar préstamo"); // Cambiar al panel de préstamos
            }
        });


        viewUsersItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListNodeUser allUsers = userDatabase.getAlluser();
                displayUsers(allUsers);
            }
        });
    }

    private void displayUsers(ListNodeUser users) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Username");
        model.addColumn("Last Name");
        model.addColumn("Id User");

        ListNodeUser current = users;
        while (current != null) {
            User user = current.getUser();
            model.addRow(new Object[]{user.getUsername(), user.getLastname(), user.getIdUser()});
            current = current.getNext();
        }

        // Crear o actualizar tabla de usuarios
        if (userTable == null) {
            userTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(userTable);
            viewUsersPanel = new JPanel(new BorderLayout());
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

        // Crear o actualizar tabla de libros
        if (bookTable == null) {
            bookTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(bookTable);
            viewBooksPanel = new JPanel(new BorderLayout());
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
