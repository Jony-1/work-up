import java.text.SimpleDateFormat;
import java.util.Date;

public class ListNodeUser {
    private User user;
    private Book book;
    private String loanDate;
    private String returnDate;
    private ListNodeUser next;

    // Constructor
    public ListNodeUser(User user) {
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    // Getters and setters
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

    public ListNodeUser getNext() {
        return next;
    }

    public void setNext(ListNodeUser next) {
        this.next = next;
    }
}
