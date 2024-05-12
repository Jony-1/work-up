class LoanDatabase {
    private LoanNode root;

    public LoanDatabase() {
        root = null;
    }

    public void insertLoan(Loan loan) {
        root = insertRecursive(root, loan);
    }

    private LoanNode insertRecursive(LoanNode node, Loan loan) {
        if (node == null) {
            return new LoanNode(loan);
        }

        int comparison = loan.compareTo(node.getLoan());
        if (comparison < 0) {
            node.setLeft(insertRecursive(node.getLeft(), loan));
        } else if (comparison > 0) {
            node.setRight(insertRecursive(node.getRight(), loan));
        } else {

        }

        return node;}

}
