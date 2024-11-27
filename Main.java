import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String AKUN_FILE = "Account.txt";

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Perintah untuk Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Perintah untuk Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
                System.out.println("Gagal menghapus layar: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Selamat datang di Sistem Perbelanjaan Online!");

        while (true) {
            clearScreen();
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
                    System.out.println("<Username atau password salah>");
                }
            } else if (pilihan == 2) {
                register(input); // Memanggil metode register
            } else if (pilihan == 3) {
                System.out.println("END OF PROGRAM");
                break;
            } else {
                System.out.println("<Pilihan tidak valid>");
            }
            input.nextLine();
        }

        input.close();
        clearScreen();
    }

    // Method untuk login
    public static Account login(Scanner input) {
        System.out.print("Masukkan Username : ");
        String userName = input.nextLine();
        System.out.print("Masukkan Password : ");
        String password = input.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(AKUN_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[1].equals(userName) && parts[2].equals(password)) {
                    String role = parts[3];
                    String accountId =  parts[0];
                    // if (role.equals("admin")) {
                        // return new Admin(accountId, userName, password);
                    // } else 
                    if (role.equals("customer")) {
                        return new Customer(accountId, userName, password);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading account file.. " + e.getMessage());
        }
        return null;
    }

    public static void register(Scanner input) {
        System.out.print("Masukkan Username : ");
        String userName = input.nextLine();
        System.out.print("Masukkan Password : ");
        String password = input.nextLine();
       
        // Cek apakah username sudah ada
        try (BufferedReader br = new BufferedReader(new FileReader(AKUN_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[1].equals(userName)) {
                    System.out.println("<Username sudah terdaftar, silakan pilih username lain>");
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error membaca file akun: " + e.getMessage());
        }
    
        // Buat ID baru
        String newId = generateNewId();
    
        // Tambahkan akun baru
        if (addAccountToFile(newId, userName, password, "customer")) {
            System.out.println("<Registrasi berhasil>");
        } else {
            System.out.println("<Gagal melakukan registrasi>");
        }
    }

    public static String generateNewId() {
        int maxId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(AKUN_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].startsWith("C")) {
                    try {
                        int currentId = Integer.parseInt(parts[0].substring(1));
                        maxId = Math.max(maxId, currentId);
                    } catch (NumberFormatException e) {
                        System.out.println("Error readind ID from file..");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found or error reading file..");
        }
        return String.format("C%03d", maxId + 1);
    }

    // Metode lainnya tetap sama
    public static Driver createDriver(Account user) {
        // if (user instanceof Admin) {
        //     return new AdminDriver((Admin) user);
        // } 
        // else 
        if (user instanceof Customer) {
            return new CustomerDriver((Customer) user);
        }
        return null;
    }

    // Tambahkan akun baru ke file
    public static boolean addAccountToFile(String id, String username, String password, String role) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AKUN_FILE, true))) {
            bw.write(id + "," + username + "," + password + "," + role);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error saving Account.txt " + e.getMessage());
        }
        return false;
    }
}
