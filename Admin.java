import java.util.Scanner;

public class Admin extends Akun {
    // Konstruktor untuk kelas Admin
    public Admin(String username, String password) {
        super(username, password); // Memanggil konstruktor kelas Akun
    }

    public void displayAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        while (true) {
            System.out.println("Selamat datang di menu Admin!");
            // Menu pada admin
            System.out.println("1. Tambahkan Barang");
            System.out.println("2. Menghapus Barang");
            System.out.println("3. Mengedit Barang");
            System.out.println("4. Keluar");

            System.out.print("Pilih menu (1-4): ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.println("Pilih barang yang ingin anda tambahkan");
                    // Logika untuk menambahkan barang
                    break;

                case 2:
                    System.out.println("Pilih barang yang ingin anda hapus");
                    // Logika untuk menghapus barang
                    break;

                case 3:
                    System.out.println("Pilih barang yang ingin anda edit");
                    // Logika untuk mengedit barang
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan menu Admin. Keluar...");
                    scanner.close(); // Menutup scanner
                    return;

                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
                    break;
            }
        }
    }
