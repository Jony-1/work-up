public class ListNodeLoan {
    private Loan loan;
    private ListNodeLoan next;

    public ListNodeLoan(Loan loan) {
        this.loan = loan;
        this.next = null;
    }

    public Loan getLoan() {
        return loan;
    }

    public ListNodeLoan getNext() {
        return next;
    }

    public void setNext(ListNodeLoan next) {
        this.next = next;
    }
}
