public class ListNode {
    private Book book;
    private ListNode next;

    public ListNode(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
