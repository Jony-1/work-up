class User {
    private String username;
    private String lastname;


    public User(String username, String lastname) {
        this.username = username;
        this.lastname = lastname;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;

    }
}
