import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan {
    private User user;
    private Book book;
    private String loanDate;
    private String returnDate;
    private String id; // Cambiado a String

    public Loan(User user, Book book, String loanDate) {
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = ""; // Por defecto, no se establece una fecha de devolución
        this.id = getUser().getIdUser();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }
}
