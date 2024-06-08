class BinaryTreeNode {
    private Book book;
    private User user;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(Book book) {
        this.book = book;
        this.left = null;
        this.right = null;
    }

    public BinaryTreeNode(User user) {
        this.user = user;
        this.left = null;
        this.right = null;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}
