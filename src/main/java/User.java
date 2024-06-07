public class User {
    private String username;
    private String lastname;
    private int userId;

    public User(String username, String lastname) {
        this.username = username;
        this.lastname = lastname;
        this.userId = generateUserId(); // Método para generar un ID único para cada usuario
    }

    private int generateUserId() {
        // Implementa la lógica para generar un ID único
        return (int) (Math.random() * 10000); // Ejemplo simple
    }

    public String getUsername() {
        return username;
    }

    public String getLastname() {
        return lastname;
    }

    public int getUserId() {
        return userId;
    }
}
