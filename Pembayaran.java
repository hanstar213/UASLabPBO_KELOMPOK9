import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Pembayaran {
    protected String idPembayaran;
    private static final String FILE_NAME = "Transaksi.txt"; // Nama file tempat ID disimpan

    public Pembayaran(String idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    protected String generateUniqueId() {
        Random random = new Random();
        return String.format("%03d", 100 + random.nextInt(900));  // Menghasilkan angka acak 3 digit
    }

    // Metode abstrak untuk menghasilkan ID pembayaran
    public abstract void generateIdPembayaran();

    public String getIdPembayaran() {
        return idPembayaran;
    }

    // Memeriksa apakah ID sudah ada di dalam file
    protected boolean isIdExist(String id) {
        Set<String> existingIds = loadExistingIdFromFile(FILE_NAME);
        return existingIds.contains(id);
    }

    // Memuat ID yang sudah ada dari file Pembayaran.txt
    private Set<String> loadExistingIdFromFile(String fileName) {
        Set<String> existingId = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    existingId.add(parts[0].trim()); // Ambil ID pembayaran yang ada
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.. " + e.getMessage());
        }
        return existingId;
    }
}
