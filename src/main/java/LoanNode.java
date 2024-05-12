class LoanNode {
    private Loan loan;
    private LoanNode left;
    private LoanNode right;

    public LoanNode(Loan loan) {
        this.loan = loan;
        this.left = null;
        this.right = null;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public LoanNode getLeft() {
        return left;
    }

    public void setLeft(LoanNode left) {
        this.left = left;
    }

    public LoanNode getRight() {
        return right;
    }

    public void setRight(LoanNode right) {
        this.right = right;
    }
}