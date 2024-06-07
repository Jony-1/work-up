public class Loan {
    private int loanId;
    private int bookId;
    private int userId;
    private String loanDate;

    public Loan(int bookId, int userId, String loanDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.loanId = generateLoanId(); // Método para generar un ID único para cada préstamo
    }

    private int generateLoanId() {
        // Implementa la lógica para generar un ID único, por ejemplo, un contador estático o UUID.
        return (int) (Math.random() * 10000); // Ejemplo simple
    }

    public int getLoanId() {
        return loanId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getUserId() {
        return userId;
    }

    public String getLoanDate() {
        return loanDate;
    }
}
