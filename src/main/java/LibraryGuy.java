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
    private JMenu loanMenu;
    private JMenuItem exitItem;
    private JMenuItem searchItem;
    private JMenuItem insertItem;
    private JMenuItem updateItem;

    private JMenuItem searchUserItem;
    private JMenuItem insertUserItem;
    private JMenuItem updateUserItem;
    private JMenuItem viewBooksItem;
    private JMenuItem viewUsersItem;

    private JMenuItem searchLoanItem;
    private JMenuItem insertLoanItem;
    private JMenuItem deleteLoanItem;
    private JMenuItem viewLoansItem;


    private JTable bookTable;
    private JTable userTable;
    private JTable loanTable;


    private JPanel viewBooksPanel;
    private JPanel viewUsersPanel;

    private JPanel viewLoansPanel;
    private JPanel deleteLoanPanel;

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

    private JButton insertLoanButton;
    private JButton deleteLoanButton;


    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField yearField;
    private JTextField usernameField;
    private JTextField lastnameField;

    private JTextField loanIdField;
    private JTextField loanDateField;
    private JTextField bookIdField;
    private JTextField userIdField;

    private LibraryDatabase database;
    private UserDatabase userdatabase;
    private LoanDatabase loanDatabase;


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

        database = new LibraryDatabase();
        userdatabase = new UserDatabase();
        loanDatabase = new LoanDatabase();

        // Panel de insertar préstamo
        JPanel insertLoanPanel = new JPanel();
        insertLoanPanel.setLayout(new GridLayout(4, 2));
        bookIdField = new JTextField(1);
        userIdField = new JTextField(1);
        loanDateField = new JTextField(1);
        insertLoanButton = new JButton("Insertar préstamo");

        insertLoanPanel.add(new JLabel("ID del libro: "));
        insertLoanPanel.add(bookIdField);
        insertLoanPanel.add(new JLabel("ID del usuario: "));
        insertLoanPanel.add(userIdField);
        insertLoanPanel.add(new JLabel("Fecha de préstamo: "));
        insertLoanPanel.add(loanDateField);
        insertLoanPanel.add(insertLoanButton);

        cards.add(insertLoanPanel, "Insertar préstamo");

        // Panel de buscar préstamo
        JPanel searchLoanPanel = new JPanel();
        searchLoanPanel.setLayout(new FlowLayout());
        JTextField searchLoanField = new JTextField(20);
        JButton searchLoanButton = new JButton("Buscar préstamo");
        searchLoanPanel.add(new JLabel("ID del préstamo: "));
        searchLoanPanel.add(searchLoanField);
        searchLoanPanel.add(searchLoanButton);

        cards.add(searchLoanPanel, "Buscar préstamo");

        // Panel de eliminar préstamo
        JPanel deleteLoanPanel = new JPanel();
        deleteLoanPanel.setLayout(new FlowLayout());
        loanIdField = new JTextField(20);
        deleteLoanButton = new JButton("Eliminar préstamo");
        deleteLoanPanel.add(new JLabel("ID del préstamo a eliminar: "));
        deleteLoanPanel.add(loanIdField);
        deleteLoanPanel.add(deleteLoanButton);

        cards.add(deleteLoanPanel, "Eliminar préstamo");

        panel.add(cards);
        frame.add(panel);


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

        loanMenu = new JMenu("Préstamos");
        searchLoanItem = new JMenuItem("Buscar préstamo");
        insertLoanItem = new JMenuItem("Insertar préstamo");
        deleteLoanItem = new JMenuItem("Eliminar préstamo");
        viewLoansItem = new JMenuItem("Ver préstamos");


        fileMenu.add(exitItem);
        viewMenu.add(searchItem);
        viewMenu.add(insertItem);
        viewMenu.add(updateItem);
        viewMenu.add(viewBooksItem);

        userMenu.add(searchUserItem);
        userMenu.add(insertUserItem);
        userMenu.add(updateUserItem);
        userMenu.add(viewUsersItem);

        fileMenu.add(exitItem);
        loanMenu.add(searchLoanItem);
        loanMenu.add(insertLoanItem);
        loanMenu.add(deleteLoanItem);
        loanMenu.add(viewLoansItem);


        menuBar.add(fileMenu);
        menuBar.add(userMenu);
        menuBar.add(viewMenu);
        menuBar.add(loanMenu);

        frame.setJMenuBar(menuBar);

        // Listeners del menú de loan
        insertLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookId = Integer.parseInt(bookIdField.getText());
                int userId = Integer.parseInt(userIdField.getText());
                String loanDate = loanDateField.getText();

                Loan newLoan = new Loan(bookId, userId, loanDate);
                loanDatabase.insertLoan(newLoan);

                JOptionPane.showMessageDialog(frame, "Préstamo insertado exitosamente");
            }
        });

        searchLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchLoanField.getText();
                ArrayList<Loan> foundLoans = loanDatabase.searchLoans(searchTerm);
                displayLoans(foundLoans);
            }
        });

        deleteLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int loanId = Integer.parseInt(loanIdField.getText());
                boolean deleted = loanDatabase.deleteLoan(loanId);

                if (deleted) {
                    JOptionPane.showMessageDialog(frame, "Préstamo eliminado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(frame, "Préstamo no encontrado");
                }
            }
        });


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

        //vista usuario
        viewUsersItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> allUsers = userdatabase.getAllUsers();
                displayUsers(allUsers);
            }
        });

        //vista libro
        viewBooksItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Book> allBooks = database.getAllBooks();
                displayBooks(allBooks);
            }
        });

        //vista prestamo
        viewLoansItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Loan> allLoans = loanDatabase.getAllLoans();
                displayLoans(allLoans);
            }
        });
    }

    private void displayUsers(ArrayList<User> users) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Username");
        model.addColumn("Last name");

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

    private void displayLoans(ArrayList<Loan> loans) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID del Préstamo");
        model.addColumn("ID del Libro");
        model.addColumn("ID del Usuario");
        model.addColumn("Fecha de Préstamo");

        for (Loan loan : loans) {
            model.addRow(new Object[]{loan.getLoanId(), loan.getBookId(), loan.getUserId(), loan.getLoanDate()});
        }

        if (loanTable == null) {
            loanTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(loanTable);
            viewLoansPanel = new JPanel();
            viewLoansPanel.setLayout(new BorderLayout());
            viewLoansPanel.add(scrollPane, BorderLayout.CENTER);
            cards.add(viewLoansPanel, "Consultar préstamos");
        } else {
            loanTable.setModel(model);
        }

        cardLayout.show(cards, "Consultar préstamos");
    }

    public void showGUI() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        LibraryGUI gui = new LibraryGUI();
        gui.showGUI();
    }
}




