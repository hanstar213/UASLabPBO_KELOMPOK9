/*import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminDriver {
    public Admin akun; // Atribut akun
    public List<Barang> ListBarang; // Atribut ListBarang
    //public ArrayList<Transaksi> listTransaksi; // Atribut ListTransaksi

    // Konstruktor
    public AdminDriver(String username, String password) {
        this.akun = new Admin(username, password);
        this.ListBarang = new ArrayList<>();
        //this.listTransaksi = new ArrayList<>();
    }

    // Metode untuk menambahkan Barang
    public void tambahBarang(Barang Barang) {
        ListBarang.add(Barang);
        System.out.println("Barang " + Barang.getNama() + " telah ditambahkan.");
    }

    // Metode untuk menampilkan semua Barang
    public void tampilkanBarang() {
        System.out.println("Daftar Barang:");
        for (Barang Barang : ListBarang) {
            System.out.println(Barang);
        }
    }

    // Metode untuk menghapus Barang
    public void hapusBarang(String namaBarang) {
        for (int i = 0; i < ListBarang.size(); i++) {
            if (ListBarang.get(i).getNama().equalsIgnoreCase(namaBarang)) {
                ListBarang.remove(i);
                System.out.println("Barang " + namaBarang + " telah dihapus.");
                return;
            }
        }
        System.out.println("Barang " + namaBarang + " tidak ditemukan.");
    }

    // Metode untuk mengedit Barang
    public void editBarang(String namaBarang, String namaBaru, double hargaBaru) {
        for (int i = 0; i < ListBarang.size(); i++) {
            if (ListBarang.get(i).getNama().equalsIgnoreCase(namaBarang)) {
                ListBarang.set(i, new Barang(namaBaru, hargaBaru));
                System.out.println("Barang " + namaBarang + " telah diedit menjadi " + namaBaru + " dengan harga " + hargaBaru);
                return;
            }
        }
        System.out.println("Barang " + namaBarang + " tidak ditemukan.");
    }

    // Metode untuk menambahkan transaksi
    public void tambahTransaksi(Transaksi transaksi) {
        listTransaksi.add(transaksi);
        System.out.println("Transaksi " + transaksi.getIdTransaksi() + " telah ditambahkan.");
    }*/

    // Metode untuk menampilkan semua transaksi
    /*public void tampilkanTransaksi() {
        System.out.println("Daftar Transaksi:");
        for (Transaksi transaksi : listTransaksi) {
            System.out.println(transaksi);
        }
    }
}*/

import java.util.Scanner;

public class AdminDriver extends Driver {
    public Admin akun; // Atribut akun
    public ListBarang listBarang; // Menggunakan ListBarang
    public Scanner scanner; // Scanner untuk input pengguna

    // Konstruktor
    public AdminDriver(String username, String password) {
        super(); // Memanggil konstruktor superclass
        this.akun = new Admin(username, password);
        this.listBarang = new ListBarang(); // Inisialisasi ListBarang
        this.scanner = new Scanner(System.in); // Inisialisasi scanner
    }

    // Metode untuk menambahkan barang
    public void tambahBarang() {
        System.out.println("=== Tambah Barang ===");
        listBarang.tambahBarang(); // Menggunakan metode dari ListBarang
    }

    // Metode untuk menampilkan semua barang
    public void tampilkanBarang() {
        System.out.println("=== Daftar Barang ===");
        listBarang.tampilkanBarang(); // Menggunakan metode dari ListBarang
    }

    // Metode untuk menghapus barang
    public void hapusBarang() {
        System.out.println("=== Hapus Barang ===");
        listBarang.hapusBarang(); // Menggunakan metode dari ListBarang
    }

    // Metode untuk mengedit barang
    public void editBarang() {
        System.out.println("=== Edit Barang ===");
        listBarang.editBarang(); // Menggunakan metode dari ListBarang
    }

    // Metode untuk menampilkan menu admin
    public void menuAdmin() {
        int pilihan;
        do {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Tampilkan Barang");
            System.out.println("3. Hapus Barang");
            System.out.println("4. Edit Barang");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (pilihan) {
                case 1:
                    tambahBarang();
                    break;
                case 2:
                    tampilkanBarang();
                    break;
                case 3:
                    hapusBarang();
                    break;
                case 4:
                    editBarang();
                    break;
                case 5:
                    System.out.println("Keluar dari menu admin.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 5);
    }
}
