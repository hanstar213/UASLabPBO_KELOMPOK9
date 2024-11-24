import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListBarang {
    private List<Barang> barang; // Daftar barang
    private Scanner scanner; // Scanner untuk input pengguna

    // Konstruktor
    public ListBarang() {
        this.barang = new ArrayList<>(); // Inisialisasi ArrayList
        this.scanner = new Scanner(System.in); // Inisialisasi scanner
    }

    // Metode untuk menambahkan barang menggunakan input dari pengguna
    public void tambahBarang() {
        System.out.print("Masukkan nama barang: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan harga barang: ");
        double harga = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer

        Barang newBarang = new Barang(nama, harga);
        this.barang.add(newBarang);
        System.out.println("Barang " + newBarang.getNama() + " telah ditambahkan.");
    }

    // Metode untuk menghapus barang berdasarkan nama
    public boolean hapusBarang() {
        System.out.print("Masukkan nama barang yang ingin dihapus: ");
        String namaBarang = scanner.nextLine();

        for (int i = 0; i < barang.size(); i++) {
            if (barang.get(i).getNama().equalsIgnoreCase(namaBarang)) {
                barang.remove(i);
                System.out.println("Barang " + namaBarang + " telah dihapus.");
                return true;
            }
        }
        System.out.println("Barang " + namaBarang + " tidak ditemukan.");
        return false;
    }

    // Metode untuk mengedit barang menggunakan input dari pengguna
    public boolean editBarang() {
        System.out.print("Masukkan nama barang yang ingin diedit: ");
        String namaBarang = scanner.nextLine();

        for (int i = 0; i < barang.size(); i++) {
            if (barang.get(i).getNama().equalsIgnoreCase(namaBarang)) {
                System.out.print("Masukkan nama baru: ");
                String namaBaru = scanner.nextLine();

                System.out.print("Masukkan harga baru: ");
                double hargaBaru = scanner.nextDouble();
                scanner.nextLine(); // Clear the buffer

                barang.set(i, new Barang(namaBaru, hargaBaru));
                System.out.println("Barang " + namaBarang + " telah diedit menjadi " + namaBaru + " dengan harga " + hargaBaru);
                return true;
            }
        }
        System.out.println("Barang " + namaBarang + " tidak ditemukan.");
        return false;
    }

    // Metode untuk menampilkan semua barang
    public void tampilkanBarang() {
        System.out.println("Daftar Barang:");
        for (Barang barang : this.barang) {
            System.out.println(barang);
        }
    }
}
