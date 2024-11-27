import java.util.ArrayList;
import java.util.Scanner;

public class AdminDriver extends Driver {
    private Admin admin; // Atribut akun
    private ListBarang listBarang;
    private ArrayList<Transaksi> listTransaksi; // Atribut listTransaksi
    private Transaksi transaksi;

    // Konstruktor AdminDriver menerima 3 parameter
    public AdminDriver(Admin admin) {
        super(admin);
        this.admin = admin;
        this.listBarang = new ListBarang();
        this.listTransaksi = new ArrayList<>(); // Inisialisasi listTransaksi
        this.transaksi = new Transaksi(null, null, null, null, null, null);
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            Main.clearScreen();
            System.out.println("\nMenu Admin:");
            System.out.println("1) Lihat Daftar Barang");
            System.out.println("2) Tambah Barang");
            System.out.println("3) Hapus Barang");
            System.out.println("4) Edit Barang");
            System.out.println("5) Lihat Transaksi");
            System.out.println("6) Keluar");
            System.out.print("Pilihan: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            Main.clearScreen();
            switch (pilihan) {
                case 1:
                    System.out.println("=======================\n    Daftar Barang\n=======================");
                    showItems();
                    break;
                case 2:
                System.out.println("=======================\n    Tambah Barang\n=======================");
                    listBarang.tambahBarang(scanner); // Menggunakan metode dari ListBarang
                    break;
                case 3:
                    System.out.println("=======================\n      Hapus Barang\n=======================");
                    listBarang.hapusBarang(scanner); // Menggunakan metode dari ListBarang
                    break;
                case 4:
                    System.out.println("=======================\n      Edit Barang\n=======================");
                    listBarang.editBarang(scanner); // Menggunakan metode dari ListBarang
                    break;
                case 5:
                    transaksi.showAllTransactions();
                    break;
                case 6:
                    System.out.println("Terima kasih, keluar dari menu admin.");
                    isContinue = false; // Keluar dari loop menu
                break;
                default:
                    System.out.println("<Pilihan tidak valid>.");
            }
            scanner.nextLine();
        }
    }

    // method untuk menampilkan barang
    public void showItems(){
        Main.clearScreen();
        listBarang.loadFromFile("Barang.txt");
        System.out.println("+------------+------------------------------------------+-----------------+------------+");
                    System.out.printf("| %-10s | %-40s | %-15s | %-10s |\n", "ID Barang", "Nama Barang", "Harga", "Stok");
                    System.out.println("+------------+------------------------------------------+-----------------+------------+");                    
                    for (Barang barang : listBarang.getDaftarBarang()) {
                        // Menampilkan data barang dalam format tabel
                        System.out.printf("| %-10s | %-40s | %-15.2f | %-10d |\n",
                                barang.getIdBarang(),
                                barang.getNamaBarang(),
                                barang.getHargaBarang(),
                                barang.getStok());
                            System.out.println("+------------+------------------------------------------+-----------------+------------+");
                    }
        listBarang.getDaftarBarang().clear();
        System.out.println("\n<Tekan enter untuk berhenti menampilkan daftar>");
    }

}
