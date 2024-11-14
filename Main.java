/*
 15 November 2024
*/

public class Main {
    public Akun akun;
    public Driver driverAkun;

    // Konstruktor
    public Main(Akun akun, Driver driverAkun) {
        this.akun = akun;
        this.driverAkun = driverAkun;
    }

    // Metode main
    public static void main(String[] args) {
        // Membuat objek Akun dan Driver
        Akun akun = new Akun(); 
        Driver driverAkun = new Driver(); 

        // Membuat objek Main
        Main main = new Main(akun, driverAkun);

        // Memanggil metode login
        main.login();
    }
}
