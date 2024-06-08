public class User {
    private String idUser;
    private String username;
    private String lastname;

    public User(String idUser, String username, String lastname) {
        this.idUser = idUser;
        this.username = username;
        this.lastname = lastname;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getLastname() {
        return lastname;
    }

    public <T> Comparable<T> getId() {
        return null;
    }
}
