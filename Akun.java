public class Akun {
    private String id;
    private String username;
    private String password;

    // Konstruktor
    public Akun(String username, String password) {
        setUsername(username);
        setPassword(password);
        setIdBasedOnUsername(username);
    }

    // Metode untuk menentukan id berdasarkan username
    private void setIdBasedOnUsername(String username) {
        if ("admin".equals(username)) {
            this.id = "admin";  // Jika username adalah "admin", maka id adalah "admin"
        } else {
            this.id = "user";   // Selain itu, id adalah "user"
        }
    }

    // Getter dan Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong");
        }
        // Simpan password terenkripsi (contoh sederhana)
        this.password = encryptPassword(password);
    }

    private String encryptPassword(String password) {
        // Implementasikan logika enkripsi di sini
        return password; // Ganti dengan enkripsi yang sebenarnya
    }

    public boolean isAdmin() {
        return "admin".equals(this.id); // ID admin diatur sebagai "admin"
    }
    
    @Override
    public String toString() {
        return "Akun{id='" + id + "', username='" + username + "', password='" + password + "'}";
    }
}
