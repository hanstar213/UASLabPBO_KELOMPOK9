import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String AKUN_FILE = "Account.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Selamat datang di Sistem Perbelanjaan Online!");

        while (true) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Keluar");
            System.out.print("Pilihan: ");
            int pilihan = input.nextInt();
            input.nextLine(); // Membersihkan newline

            if (pilihan == 1) {
                Account user = login(input); // Memanggil metode login
                if (user != null) {
                    Driver driver = createDriver(user); // Membuat objek driver dari kelas Driver
                    driver.menu(); // Jalankan menu driver (Admin atau Customer)
                } else {
                    System.out.println("Username atau password salah!");
                }
            } else if (pilihan == 2) {
                register(input); // Memanggil metode register
            } else if (pilihan == 3) {
                System.out.println("Terima kasih telah menggunakan sistem ini!");
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }

        input.close();
    }

    // Pindahkan metode login ke sini, di luar dari main()
    public static Account login(Scanner input) {
        System.out.print("Masukkan Username : ");
        String userName = input.nextLine();
        System.out.print("Masukkan Password : ");
        String password = input.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(AKUN_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(userName) && parts[1].equals(password)) {
                    String role = parts[2];

                    if (role.equals("customer")) {
                        return new Customer("000", userName, password);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading account file.. " + e.getMessage());
        }
        return null;
    }

    // Pindahkan metode register ke sini, di luar dari main()
    public static void register(Scanner input) {
        System.out.print("Masukkan Username : ");
        String userName = input.nextLine();
        System.out.print("Masukkan Password : ");
        String password = input.nextLine();
        System.out.print("Pilih role (admin/customer): ");
        String role = input.nextLine().toLowerCase();

        if (!role.equals("admin") && !role.equals("customer")) {
            System.out.println("Role tidak valid. Silakan masukkan admin atau customer.");
            return;
        }

        if (addAccountToFile(userName, password, role)) {
            System.out.println("Registrasi berhasil! Silakan login.");
        } else {
            System.out.println("Gagal melakukan registrasi. Username mungkin sudah terdaftar.");
        }
    }

    // Metode lainnya tetap sama
    public static Driver createDriver(Account user) {
        // if (user instanceof Admin) {
        //     return new AdminDriver((Admin) user);
        // } 
        if (user instanceof Customer) {
            return new CustomerDriver((Customer) user);
        }
        return null;
    }

    // Tambahkan akun baru ke file
    public static boolean addAccountToFile(String username, String password, String role) {
        try (BufferedReader br = new BufferedReader(new FileReader(AKUN_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(username)) {
                    return false; // Username sudah ada
                }
            }
        } catch (IOException e) {
            // Jika file tidak ada, lanjutkan untuk membuat file baru
        }

        // Tambahkan akun baru
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AKUN_FILE, true))) {
            bw.write(username + "," + password + "," + role);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error menyimpan file akun: " + e.getMessage());
        }
        return false;
    }
}
