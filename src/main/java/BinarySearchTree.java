class BinarySearchTree {
    private BinaryTreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insertBook(Book book) {
        root = insertBookRecursive(root, book);
    }

    private BinaryTreeNode insertBookRecursive(BinaryTreeNode node, Book book) {
        if (node == null) {
            return new BinaryTreeNode(book);
        }

        if (book.getId().compareTo(node.getBook().getId()) < 0) {
            node.setLeft(insertBookRecursive(node.getLeft(), book));
        } else if (book.getId().compareTo(node.getBook().getId()) > 0) {
            node.setRight(insertBookRecursive(node.getRight(), book));
        }

        return node;
    }

    public void insertUser(User user ) {
        root = insertUserRecursive(root, user);
    }

    private BinaryTreeNode insertUserRecursive(BinaryTreeNode node, User user) {
        if (node == null) {
            return new BinaryTreeNode(user);
        }

        if (user.getId().compareTo(node.getUser().getId()) < 0) {
            node.setLeft(insertUserRecursive(node.getLeft(), user));
        } else if (user.getId().compareTo(node.getUser().getId()) > 0) {
            node.setRight(insertUserRecursive(node.getRight(), user));
        }

        return node;
    }

    public BinaryTreeNode searchBook(String bookId) {
        return searchBookRecursive(root, bookId);
    }

    private BinaryTreeNode searchBookRecursive(BinaryTreeNode node, String bookId) {
        if (node == null || node.getBook().getId().equals(bookId)) {
            return node;
        }

        if (bookId.compareTo(node.getBook().getId()) < 0) {
            return searchBookRecursive(node.getLeft(), bookId);
        } else {
            return searchBookRecursive(node.getRight(), bookId);
        }
    }

    public BinaryTreeNode searchUser(String userId) {
        return searchUserRecursive(root, userId);
    }

    private BinaryTreeNode searchUserRecursive(BinaryTreeNode node, String userId) {
        if (node == null || node.getUser().getId().equals(userId)) {
            return node;
        }

        if (userId.compareTo(node.getUser().getId().toString()) < 0) {
            return searchUserRecursive(node.getLeft(), userId);
        } else {
            return searchUserRecursive(node.getRight(), userId);
        }
    }
}
