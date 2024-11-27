import java.text.DecimalFormat;
import java.util.List;

public class Invoice {
    private String idFaktur;
    private String idPelanggan;
    private List<Barang> barangDibeli;
    private double totalHarga;
    private String metodePembayaran;
    private String tanggalPembayaran;

    // Constructor untuk membuat faktur pembayaran
    public Invoice(String idFaktur, String idPelanggan, List<Barang> barangDibeli, double totalHarga, String metodePembayaran, String tanggalPembayaran) {
        this.idFaktur = idFaktur;
        this.idPelanggan = idPelanggan;
        this.barangDibeli = barangDibeli;
        this.totalHarga = totalHarga;
        this.metodePembayaran = metodePembayaran;
        this.tanggalPembayaran = tanggalPembayaran;
    }

    // Menampilkan detail faktur
    public void printInvoice() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        System.out.println("+========================================================================================================+");
        System.out.println("|  \t\t\t\t           Faktur Pembayaran     \t\t\t\t\t |");
        System.out.println("+========================================================================================================+");
        System.out.println("|                                                                                                        |");
        System.out.println("|\tID Faktur       \t: " + idFaktur + "\t\t\t\t\t\t\t\t\t |");
        System.out.println("|\tID Pelanggan    \t: " + idPelanggan + "\t\t\t\t\t\t\t\t\t |");
        System.out.println("|\ttTanggal Pembayaran\t: " + tanggalPembayaran + "\t\t\t\t\t\t\t\t |");
        System.out.println("|\tMetode Pembayaran\t: " + metodePembayaran + "\t\t\t\t\t\t\t\t\t |");
        
        System.out.println("|                                                                                                        |");
        System.out.println("|\tDaftar Barang : \t\t\t\t\t\t\t\t\t\t |");
        System.out.println("|\t+------------+------------------------------------------+-----------------+------------+\t |");
        System.out.printf("|\t| %-10s | %-40s | %-15s | %-10s |\t |\n", "ID Barang", "Nama Barang", "Harga", "Jumlah");
        System.out.println("|\t+------------+------------------------------------------+-----------------+------------+\t |");
        
        for (Barang barang : barangDibeli) {
            String hargaFormatted = df.format(barang.getHargaBarang());
            System.out.printf("|\t| %-10s | %-40s | %-15s | %-10d |\t |\n", 
                              barang.getIdBarang(),
                              barang.getNamaBarang(),
                              hargaFormatted,
                              barang.getStok());
        }
        System.out.println("|\t+------------+------------------------------------------+-----------------+------------+\t |");
        System.out.println("|                                                                                                        |");
        System.out.println("|\tTotal Harga      : Rp " + df.format(totalHarga) + "\t\t\t\t\t\t\t\t |");
        System.out.println("|                                                                                                        |");
        System.out.println("+========================================================================================================+");
    }
}
