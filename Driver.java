import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class Transaksi {
    private String idTransaksi;
    private String idCustomer;
    private String idBarang;
    private String tanggalTransaksi;
    private String metodePembayaran;
    private String totalHarga;

    // Constructor
    public Transaksi(String idTransaksi, String idCustomer, String idBarang, 
                     String tanggalTransaksi, String metodePembayaran, String totalHarga) {
        this.idTransaksi = idTransaksi;
        this.idCustomer = idCustomer;
        this.idBarang = idBarang;
        this.tanggalTransaksi = tanggalTransaksi;
        this.metodePembayaran = metodePembayaran;
        this.totalHarga = totalHarga;
    }

    // Method untuk menyimpan transaksi baru ke file
    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String transaksiData = String.join(",",
                    idTransaksi,          
                    idCustomer,     
                    idBarang,             
                    tanggalTransaksi,     
                    metodePembayaran,     
                    totalHarga 
            );
            writer.write(transaksiData);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Gagal menyimpan transaksi ke file: " + e.getMessage());
        }
    }

    // Method untuk melakukan transaksi
    public void doTransaksi(Customer customer, Barang barangDipilih, Pembayaran pembayaran) {
        if (customer == null || barangDipilih == null) {
            System.out.println("Error: Data transaksi tidak lengkap.");
            return;
        }

        String fileTransaksi = "Transaksi.txt";
        double totalHarga = barangDipilih.getHargaBarang() * barangDipilih.getStok(); // Harga total berdasarkan jumlah
        DecimalFormat df = new DecimalFormat("#,###", new java.text.DecimalFormatSymbols(Locale.GERMANY));
        String totalHargaFormatted = df.format(totalHarga); // Format harga dengan pemisah ribuan

        // Menggunakan ID pembayaran sebagai ID transaksi
        idTransaksi = pembayaran.getIdPembayaran(); // Menggunakan ID yang dihasilkan oleh objek pembayaran

        // Menentukan tanggal transaksi
        tanggalTransaksi = LocalDate.now().toString();

        // Menyimpan data transaksi ke file
        Transaksi transaksiBaru = new Transaksi(
            idTransaksi,
            customer.getId(),
            barangDipilih.getIdBarang(),
            tanggalTransaksi,
            pembayaran.getClass().getSimpleName(),  // Menyimpan jenis metode pembayaran (COD, Bank, QRIS)
            totalHargaFormatted
        );

        transaksiBaru.saveToFile(fileTransaksi);

        // Membuat Invoice dan mencetaknya
        List<Barang> barangDibeli = List.of(barangDipilih);  // Daftar barang yang dibeli
        Invoice invoice = new Invoice(idTransaksi, customer.getId(), barangDibeli, totalHarga, pembayaran.getClass().getSimpleName(), tanggalTransaksi);

        // Cetak faktur
        invoice.printInvoice();
    }

    public void showAllTransactions() {
        String filePath = "Transaksi.txt";
        File file = new File(filePath);
        DecimalFormat df = new DecimalFormat("#,###", new java.text.DecimalFormatSymbols(Locale.GERMANY));
        
        // Cek jika file kosong
        if (file.length() == 0) {
            System.out.println("Tidak ada data transaksi yang ditemukan.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            System.out.println("Riwayat Transaksi:");
            System.out.println("+-----------------+-----------------+-----------------+-----------------+----------------------+---------------------------+");
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-20s | %-25s |\n",
                    " ID Transaksi", " ID Customer", " ID Barang", " Tanggal", " Metode Pembayaran", " Total Harga");
            System.out.println("+-----------------+-----------------+-----------------+-----------------+----------------------+---------------------------+");
        
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    // Hapus tanda ribuan dan format harga
                    String totalHarga = df.format(Double.parseDouble(parts[5].replace(".", "").replace(",", ".")));
                    System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-20s | Rp%-23s |\n",
                            parts[0], parts[1], parts[2], parts[3], parts[4], totalHarga);
                }
            }
            System.out.println("+-----------------+-----------------+-----------------+-----------------+----------------------+---------------------------+");
        } catch (IOException e) {
            System.out.println("Gagal membaca riwayat transaksi: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Format data transaksi tidak valid: " + e.getMessage());
        }
    }
    
    public void showTransactionsByCustomer(String customerId) {
        String filePath = "Transaksi.txt";
        DecimalFormat df = new DecimalFormat("#,###", new java.text.DecimalFormatSymbols(Locale.GERMANY));

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;
            System.out.println("Riwayat Transaksi untuk Customer ID: " + customerId);
            System.out.println("+-----------------+-----------------+-----------------+----------------------+---------------------------+");
            System.out.printf("| %-15s | %-15s | %-15s | %-20s |  %-24s |\n",
                    "ID Transaksi", "ID Barang", "Tanggal", "Metode Pembayaran", "Total Harga");
            System.out.println("+-----------------+-----------------+-----------------+----------------------+---------------------------+");
    
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6 && parts[1].equals(customerId)) {
                    String totalHarga = df.format(Double.parseDouble(parts[5].replace(".", "").replace(",", ".")));
                    System.out.printf("| %-15s | %-15s | %-15s | %-20s |  Rp%-22s |\n",
                            parts[0], parts[2], parts[3], parts[4], totalHarga);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("|\t\t   < Tidak ada transaksi ditemukan untuk customer dengan ID" + customerId + " >\t\t\t |");
            }
            System.out.println("+-----------------+-----------------+-----------------+----------------------+---------------------------+");
        } catch (IOException e) {
            System.out.println("Gagal membaca riwayat transaksi untuk customer: " + e.getMessage());
        }
    }
    

}

