import java.util.ArrayList;

class UserDatabase {
    private ArrayList<User> users;

    public UserDatabase() {
        users = new ArrayList<>();
    }

    public void insertUser(User user) {
        users.add(user);
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public ArrayList<User> searchUsers(String searchUserTerm) {
        ArrayList<User> foundUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getUsername().toLowerCase().contains(searchUserTerm.toLowerCase()) ||
                    searchUserTerm.toLowerCase().contains(user.getLastname().toLowerCase())) {
                foundUsers.add(user);
            }
        }
        return foundUsers;
    }
}
