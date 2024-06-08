public class UserDatabase {
    private ListNodeUser head;

    public void insertUser(User user) {
        ListNodeUser newNode = new ListNodeUser(user);
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

    public void initializeUsers() {
        User user1 = new User("100", "karolina", "sierra");
        User user2 = new User("200", "jonathan", "segura");

        insertUser(user1);
        insertUser(user2);
    }

    public User getUserById(String id) {
        ListNodeUser current = head;
        while (current != null) {
            if (current.getUser().getIdUser().equals(id)) {
                return current.getUser();
            }
            current = current.getNext();
        }
        return null;
    }

    public ListNodeUser getAllUsers() {
        return head;
    }
}