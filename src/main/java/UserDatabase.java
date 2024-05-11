class ListNodeUser {
    private Book book;
    private ListNodeUser next;

    public ListNodeUser(Book book, ListNodeUser next) {
        this.book = book;
        this.next = next;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ListNodeUser getNext() {
        return next;
    }

    public void setNext(ListNodeUser next) {
        this.next = next;
    }
}

class UserDatabase {
    private ListNodeUser head;

    public UserDatabase() {
        head = null;
    }

    public void insertUser(User book) {
        ListNodeUser newNode = new ListNodeUser(book, null);

        if (head == null) {
            head = newNode;
        } else {
            ListNodeUser current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public ListNodeUser searchUsers(String searchTerm) {
        return searchRecursive(head, searchTerm.toLowerCase());
    }

    private ListNodeUser searchRecursive(ListNodeUser node, String searchTerm) {
        ListNodeUser foundBooks = null;
        while (node != null) {
            if (node.getBook().getTitle().toLowerCase().contains(searchTerm) ||
                    node.getBook().getAuthor().toLowerCase().contains(searchTerm) ||
                    node.getBook().getGenre().toLowerCase().contains(searchTerm) ||
                    String.valueOf(node.getBook().getYear()).equals(searchTerm)) {
                if (foundBooks == null) {
                    foundBooks = new ListNodeUser(node.getBook(), null);
                } else {
                    ListNodeUser current = foundBooks;
                    while (current.getNext() != null) {
                        current = current.getNext();
                    }
                    ListNodeUser newFoundBook = new ListNodeUser(node.getBook(), null);
                    current.setNext(newFoundBook);
                }
            }
            node = node.getNext();
        }
        return foundBooks;
    }

    public ListNodeUser getAllBooks() {

        if (head == null) {
            return null;
        }

        ListNodeUser allBooksHead = new ListNodeUser(null, null);
        ListNodeUser currentAllBooksNode = allBooksHead;

        ListNodeUser current = head;
        while (current != null) {

            ListNodeUser newBookNode = new ListNodeUser(current.getBook(), null);
            currentAllBooksNode.setNext(newBookNode);
            currentAllBooksNode = newBookNode;

            current = current.getNext();
        }


        return allBooksHead.getNext();
    }


}
