import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;


public class LibraryGUI {
    private JFrame frame;
    private JPanel panel;
    private JMenu loanMenu;

    private JMenuItem returnItem;


    public Graph graph;

    private JMenuItem searchLoanItem;
    private JMenuItem insertLoanItem;
    private JMenuItem deleteLoanItem;
    private JMenuItem viewLoansItem;
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
    private JTable loanTable; // Tabla para mostrar préstamos

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
    private JTextField bookId;
    private JPanel returnPanel; // Nuevo panel para devoluciones
    private JTextField authorField;
    private JTextField genreField;
    private JTextField yearField;

    private JTextField loanIdField; // Campo de texto para ID de préstamo
    private JTextField usernameField;
    private JButton returnButton; // Nuevo botón para devoluciones
    private JTextField lastnameField;
    private JTextField iduserField;
    private LibraryDatabase database;
    private UserDatabase userDatabase;
    private LoanDatabase loanDatabase;


    public LibraryGUI() {


        frame = new JFrame("Work-up libreria");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Panel de búsqueda de libros
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchButton = new JButton("Buscar libro");
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Panel de inserción de libros
        insertPanel = new JPanel();
        insertPanel.setLayout(new GridLayout(6, 2));
        titleField = new JTextField(10);
        authorField = new JTextField(10);
        genreField = new JTextField(10);
        yearField = new JTextField(10);
        insertButton = new JButton("Insertar libro");
        bookId = new JTextField(10);
        insertPanel.add(new JLabel("ID del libro: "));
        insertPanel.add(bookId);
        insertPanel.add(new JLabel("Título: "));
        insertPanel.add(titleField);
        insertPanel.add(new JLabel("Autor: "));
        insertPanel.add(authorField);
        insertPanel.add(new JLabel("Género: "));
        insertPanel.add(genreField);
        insertPanel.add(new JLabel("Año: "));
        insertPanel.add(yearField);
        insertPanel.add(insertButton);

        // Panel de búsqueda de usuarios
        searchUserPanel = new JPanel();
        searchUserPanel.setLayout(new FlowLayout());
        searchUserButton = new JButton("Buscar usuario");
        searchUserField = new JTextField(20);
        searchUserPanel.add(searchUserField);
        searchUserPanel.add(searchUserButton);

        // Panel de inserción de usuarios
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
        insertUserPanel.add(new JLabel("ID de usuario: "));
        insertUserPanel.add(iduserField);
        insertUserPanel.add(insertUserButton);

        // Panel de inserción de préstamos
        insertLoanPanel = new JPanel();
        insertLoanPanel.setLayout(new GridLayout(4, 2));
        JTextField loanUserField = new JTextField(10);
        JTextField loanBookField = new JTextField(10);
        insertLoanButton = new JButton("Registrar préstamo");
        insertLoanPanel.add(new JLabel("ID de usuario: "));
        insertLoanPanel.add(loanUserField);
        insertLoanPanel.add(new JLabel("ID de libro: "));
        insertLoanPanel.add(loanBookField);
        insertLoanPanel.add(insertLoanButton);



        // Panel de devolución de préstamos
        returnPanel = new JPanel();
        returnPanel.setLayout(new GridLayout(2, 2));
        loanIdField = new JTextField(10);
        returnButton = new JButton("Devolver libro");
        returnPanel.add(new JLabel("ID del préstamo: "));
        returnPanel.add(loanIdField);
        returnPanel.add(returnButton);


        // Agregar paneles a cards
        cards.add(searchPanel, "Buscar libro");
        cards.add(insertPanel, "Insertar libro");
        cards.add(searchUserPanel, "Buscar usuario");
        cards.add(insertUserPanel, "Insertar usuario");
        cards.add(insertLoanPanel, "Registrar préstamo");
        cards.add(returnPanel, "Devolver préstamo");

        panel.add(cards);
        frame.add(panel);

        // Crear bases de datos
        database = new LibraryDatabase();
        userDatabase = new UserDatabase();
        loanDatabase = new LoanDatabase();

        insertLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loanUserId = loanUserField.getText();
                String loanBookId = loanBookField.getText();

                // Obtener usuario y libro correspondientes
                User loanUser = userDatabase.getUserById(loanUserId);
                Book loanBook = database.getBookById(loanBookId); // Renombrar database a libraryDatabase

                if (loanUser != null && loanBook != null) {
                    // Obtener el ID del nodo del usuario y del libro
                    String userNodeId = loanUser.getIdUser();
                    String bookNodeId = loanBook.getId();

                    // Buscar la ruta más corta entre el usuario y el libro
                    List<String> shortestPath = graph.getShortestPath(userNodeId, bookNodeId);

                    if (!shortestPath.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Se encontró una ruta entre el usuario y el libro.");

                        // Crear un mensaje para mostrar la ruta encontrada
                        StringBuilder message = new StringBuilder();
                        message.append("Ruta encontrada: ");
                        for (String node : shortestPath) {
                            message.append(node).append(" -> ");
                        }
                        // Eliminar el último " -> "
                        if (message.length() > 4) {
                            message.delete(message.length() - 4, message.length());
                        }

                        // Mostrar el mensaje
                        JOptionPane.showMessageDialog(frame, message.toString());

                        // Continuar con la lógica de registro del préstamo
                        String currentDate = LocalDate.now().toString(); // Obtener la fecha actual
                        Loan newLoan = new Loan(loanUser, loanBook, currentDate); // Crear un objeto Loan con los datos
                        // Insertar el préstamo en la base de datos
                        loanDatabase.insertLoan(newLoan);
                        JOptionPane.showMessageDialog(frame, "Préstamo registrado exitosamente");

                        // Cambiar a la vista de préstamos
                        cardLayout.show(cards, "Consultar préstamos");
                    } else {
                        JOptionPane.showMessageDialog(frame, "No se encontró una ruta entre el usuario y el libro.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "ID de usuario o libro no válido");
                }
            }
        });



        // Action listener para devolución de préstamos
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loanId = loanIdField.getText();

                // Realizar la devolución del préstamo con el ID proporcionado
                boolean success = loanDatabase.returnLoan(loanId);

                JOptionPane.showMessageDialog(frame, "Se encontró una ruta entre el usuario y el libro.");


                if (success) {
                    JOptionPane.showMessageDialog(frame, "Devolución exitosa");
                } else {
                    JOptionPane.showMessageDialog(frame, "No se pudo encontrar el préstamo con ese ID");
                }
            }
        });


        // Action listener para la inserción de libros
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto
                String bookIdText = bookId.getText();
                String title = titleField.getText();
                String author = authorField.getText();
                String genre = genreField.getText();
                int year = Integer.parseInt(yearField.getText());

                // Crear un nuevo libro
                Book newBook = new Book(bookIdText, title, author, genre, year);

                // Insertar el nuevo libro en la base de datos
                database.insertBook(newBook);

                // Limpiar los campos de texto
                bookId.setText("");
                titleField.setText("");
                authorField.setText("");
                genreField.setText("");
                yearField.setText("");

                JOptionPane.showMessageDialog(frame, "Libro insertado exitosamente");
            }
        });

        // Action listener para la inserción de usuarios
        insertUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto
                String username = usernameField.getText();
                String lastname = lastnameField.getText();
                String id = iduserField.getText();

                // Crear un nuevo usuario
                User newUser = new User(id, username, lastname);

                // Insertar el nuevo usuario en la base de datos
                userDatabase.insertUser(newUser);

                // Limpiar los campos de texto
                usernameField.setText("");
                lastnameField.setText("");
                iduserField.setText("");

                JOptionPane.showMessageDialog(frame, "Usuario insertado exitosamente");
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

        loanMenu = new JMenu("Préstamos");
        searchLoanItem = new JMenuItem("Buscar préstamo");
        insertLoanItem = new JMenuItem("Insertar préstamo");
        deleteLoanItem = new JMenuItem("Eliminar préstamo");
        viewLoansItem = new JMenuItem("Ver préstamos ");

        // Agregar elementos al menú
        fileMenu.add(exitItem);


        fileMenu.add(exitItem);
        viewMenu.add(searchItem);
        viewMenu.add(insertItem);
        viewMenu.add(updateItem);
        viewMenu.add(viewBooksItem);

        userMenu.add(searchUserItem);
        userMenu.add(insertUserItem);
        userMenu.add(updateUserItem);
        userMenu.add(viewUsersItem);

        returnItem = new JMenuItem("Devolver préstamo");



        fileMenu.add(exitItem);
        loanMenu.add(searchLoanItem);
        loanMenu.add(insertLoanItem);
        loanMenu.add(deleteLoanItem);
        loanMenu.add(viewLoansItem);
        loanMenu.add(returnItem);


        menuBar.add(fileMenu);
        menuBar.add(userMenu);
        menuBar.add(viewMenu);
        menuBar.add(loanMenu);

        frame.setJMenuBar(menuBar);

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
                // Obtener todos los préstamos de la base de datos
                ListNodeLoan allLoans = loanDatabase.getAllLoans();

                // Mostrar los préstamos en la tabla correspondiente
                displayLoans(allLoans);
            }
        });

        insertLoanItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar a la vista de inserción de préstamos
                cardLayout.show(cards, "Registrar préstamo");
            }
        });
        returnItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Devolver préstamo");
            }
        });

        viewUsersItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListNodeUser allUsers = userDatabase.getAllUsers();
                displayUsers(allUsers);
            }
        });

        // Mostrar GUI
        frame.setVisible(true);
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
        model.addColumn("ID");
        model.addColumn("Título");
        model.addColumn("Autor");
        model.addColumn("Género");
        model.addColumn("Año");

        ListNode current = books;
        while (current != null) {
            Book book = current.getBook();
            model.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getYear()});
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





    private void displayLoans(ListNodeLoan loans) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("User ID");
        model.addColumn("Book ID");
        model.addColumn("Loan Date");
        model.addColumn("Return Date");

        ListNodeLoan current = loans;
        while (current != null) {
            if (current.getLoan() != null) {
                Loan loan = current.getLoan();
                model.addRow(new Object[]{loan.getUser().getId(), loan.getBook().getId(), loan.getLoanDate(), loan.getReturnDate()});
            }
            current = current.getNext();
        }

        // Crear o actualizar tabla de préstamos
        if (loanTable == null) {
            loanTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(loanTable);
            viewLoansPanel = new JPanel(new BorderLayout());
            viewLoansPanel.add(scrollPane, BorderLayout.CENTER);
            cards.add(viewLoansPanel, "Consultar préstamos");
        } else {
            loanTable.setModel(model);
        }

        cardLayout.show(cards, "Consultar préstamos");
    }

    public static void main(String[] args) {

        LibraryGUI libraryGUI = new LibraryGUI();

        // Inicializar la base de datos de usuarios
        UserDatabase userDatabase = new UserDatabase();
        userDatabase.initializeUsers();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryGUI();

            }
        });
    }

    public void showGUI() {

    }
}
