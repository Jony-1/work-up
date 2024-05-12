import java.text.SimpleDateFormat;
import java.util.Date;

class Loan {
    private User user;
    private Book book;
    private String loanDate;
    private String returnDate;

    public Loan(User user, Book book, String loanDate, String returnDate) {
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    // Getters y Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    public static String getCurrentDate() {
        // Obtener la fecha actual
        Date currentDate = new Date();
        // Formatear la fecha como una cadena de texto en el formato deseado
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(currentDate);
    }


    public int compareTo(Loan loan) {

        return 0;
    }
}


