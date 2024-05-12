class ListNodeUser {
    private User user;
    private ListNodeUser next;

    public ListNodeUser(User user, ListNodeUser next) {
        this.user = user;
        this.next = next;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        ListNodeUser foundUsers = null;
        while (node != null) {
            if (node.getUser().getUsername().toLowerCase().contains(searchTerm) ||
                    node.getUser().getLastname().toLowerCase().contains(searchTerm) ||
                    node.getUser().getIdUser().toLowerCase().contains(searchTerm)) {
                if (foundUsers == null) {
                    foundUsers = new ListNodeUser(node.getUser(), null);
                } else {
                    ListNodeUser current = foundUsers;
                    while (current.getNext() != null) {
                        current = current.getNext();
                    }
                    ListNodeUser newFoundUser = new ListNodeUser(node.getUser(), null);
                    current.setNext(newFoundUser);
                }
            }
            node = node.getNext();
        }
        return foundUsers;
    }

    public ListNodeUser getAlluser() {
        if (head == null) {
            return null;
        }


        ListNodeUser allUsersHead = new ListNodeUser(null, null);
        ListNodeUser currentAllUserNode = allUsersHead;

        ListNodeUser current = head;
        while (current != null) {

            ListNodeUser newUserNode = new ListNodeUser(current.getUser(), null);

            currentAllUserNode.setNext(newUserNode);

            currentAllUserNode = newUserNode;

            current = current.getNext();
        }


        return allUsersHead.getNext();
    }


}
