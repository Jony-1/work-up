import java.util.ArrayList;

public class LoanDatabase {
    private ArrayList<Loan> loans;

    public LoanDatabase() {
        loans = new ArrayList<>();
    }

    // Método para insertar un nuevo préstamo
    public void insertLoan(Loan loan) {
        loans.add(loan);
    }

    // Método para obtener todos los préstamos
    public ArrayList<Loan> getAllLoans() {
        return loans;
    }

    // Método para buscar préstamos
    public ArrayList<Loan> searchLoans(String searchLoanTerm) {
        ArrayList<Loan> foundLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (String.valueOf(loan.getLoanId()).contains(searchLoanTerm) ||
                    String.valueOf(loan.getBookId()).contains(searchLoanTerm) ||
                    String.valueOf(loan.getUserId()).contains(searchLoanTerm)) {
                foundLoans.add(loan);
            }
        }
        return foundLoans;
    }

    // Método para eliminar un préstamo por ID
    public boolean deleteLoan(int loanId) {
        return loans.removeIf(loan -> loan.getLoanId() == loanId);
    }
}
