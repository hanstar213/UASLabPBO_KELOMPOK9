import java.util.Random;

public class QRIS extends Pembayaran {
    
    public QRIS(String idPembayaran) {
        super(idPembayaran);
        generateIdPembayaran(); // Generate ID pembayaran saat instansi dibuat
    }

    @Override
    public void generateIdPembayaran() {
        Random random = new Random();
        String newId;
        
        // Generate ID hingga id unik ditemukan
        do {
            int angkaAcak = 100 + random.nextInt(900); // Generate angka acak antara 100 dan 999
            newId = "Q" + String.format("%03d", angkaAcak); 
        } while (isIdExist(newId)); // Memeriksa apakah ID sudah ada di file

        this.idPembayaran = newId; // Set ID baru yang unik
    }

}
