class ListNode {
    private Book book;
    private ListNode next;

    public ListNode(Book book, ListNode next) {
        this.book = book;
        this.next = next;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}

class LibraryDatabase {
    private ListNode head;

    public LibraryDatabase() {
        head = null;
    }

    public void insertBook(Book book) {
        ListNode newNode = new ListNode(book, null);

        if (head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public ListNode searchBooks(String searchTerm) {
        return searchRecursive(head, searchTerm.toLowerCase());
    }

    private ListNode searchRecursive(ListNode node, String searchTerm) {
        ListNode foundBooks = null;
        while (node != null) {
            if (node.getBook().getTitle().toLowerCase().contains(searchTerm) ||
                    node.getBook().getAuthor().toLowerCase().contains(searchTerm) ||
                    node.getBook().getGenre().toLowerCase().contains(searchTerm) ||
                    String.valueOf(node.getBook().getYear()).equals(searchTerm)) {
                if (foundBooks == null) {
                    foundBooks = new ListNode(node.getBook(), null);
                } else {
                    ListNode current = foundBooks;
                    while (current.getNext() != null) {
                        current = current.getNext();
                    }
                    ListNode newFoundBook = new ListNode(node.getBook(), null);
                    current.setNext(newFoundBook);
                }
            }
            node = node.getNext();
        }
        return foundBooks;
    }

    public ListNode getAllBooks() {

        if (head == null) {
            return null;
        }

        ListNode allBooksHead = new ListNode(null, null);
        ListNode currentAllBooksNode = allBooksHead;

        ListNode current = head;
        while (current != null) {

            ListNode newBookNode = new ListNode(current.getBook(), null);
            currentAllBooksNode.setNext(newBookNode);
            currentAllBooksNode = newBookNode;

            current = current.getNext();
        }


        return allBooksHead.getNext();
    }

}
