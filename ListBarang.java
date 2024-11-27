import java.io.*;
import java.util.ArrayList;

public class ListBarang {
    private ArrayList<Barang> daftarBarang;

    public ListBarang(){
        this.daftarBarang = new ArrayList<>();
    }
    
    // Method untuk membaca data barang dari file teks
    public void loadFromFile(String fileName){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 4){
                    String idBarang = parts[0].trim();
                    String namaBarang = parts[1].trim();
                    double hargaBarang = Double.parseDouble(parts[2].trim());
                    int stok = Integer.parseInt(parts[3].trim());
                    daftarBarang.add(new Barang(idBarang, namaBarang, hargaBarang, stok));
                }
            }
        } catch(IOException e){
            System.out.println("Error reading file.. " + e.getMessage());
        }
    }

    // Method untuk menyimpan data barang ke file teks
    public void saveToFile(String fileName){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (Barang barang : daftarBarang){
                bw.write(barang.getIdBarang() + "," + barang.getNamaBarang() + "," + barang.getHargaBarang() + "," + barang.getStok());
                bw.newLine();
            }
        } catch(IOException e){
            System.out.println("Error saving data to file.. " + e.getMessage());
        }
    }

     // Method untuk memperbarui seluruh atribut barang berdasarkan idBarang
     public boolean updateBarang(String idBarang, String namaBarang, double hargaBarang, int stok) {
        for (Barang barang : daftarBarang) {
            if (barang.getIdBarang().equals(idBarang)) {
                // Update seluruh atribut barang
                barang.setNamaBarang(namaBarang);
                barang.setHargaBarang(hargaBarang);
                barang.setStok(stok);
                return true; // Berhasil diperbarui
            }
        }
        System.out.println("<Barang dengan ID " + idBarang + " tidak ditemukan>");
        return false; // Barang tidak ditemukan
    }

    // Method untuk memperbarui file setelah perubahan
     public void updateAndSave(String fileName, String idBarang, String namaBarang, double hargaBarang, int stok) {
        if (updateBarang(idBarang, namaBarang, hargaBarang, stok)) {
            saveToFile(fileName); // Simpan perubahan ke file jika update berhasil
        }
    }

    // Method untuk memperbarui stok barang dengan mengurangi jumlah yang diberikan
    public void updateStok(String idBarang, int jumlah) {
        for (Barang barang : daftarBarang) {
            if (barang.getIdBarang().equals(idBarang)) {
                int stokBaru = barang.getStok() - jumlah;
                if (stokBaru >= 0) {
                    barang.setStok(stokBaru); // Update stok jika jumlah cukup
                    saveToFile("Barang.txt"); // Simpan perubahan ke file setelah stok diperbarui
                } else {
                    System.out.println("<Stok tidak mencukupi untuk pengurangan>");
                }
            }
        }
    }

    public void updateStokAfterCancel(String idBarang, int jumlahBatal) {
        String fileBarang = "Barang.txt";
        StringBuilder updatedContent = new StringBuilder();
        boolean found = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(fileBarang))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].equals(idBarang)) {
                    // Update stok barang jika ID sesuai
                    int stokLama = Integer.parseInt(parts[3]);
                    int stokBaru = stokLama + jumlahBatal; // Tambahkan stok dengan jumlah yang dibatalkan
    
                    // Tambahkan data barang yang sudah di-update ke StringBuilder
                    updatedContent.append(parts[0]).append(",")
                                   .append(parts[1]).append(",")
                                   .append(parts[2]).append(",")
                                   .append(stokBaru).append("\n");
                    found = true;
                } else {
                    // Jika barang tidak ditemukan, tambahkan data lama tanpa perubahan
                    updatedContent.append(line).append("\n");
                }
            }
    
            // Jika barang ditemukan dan stok sudah diperbarui, tuliskan ulang ke file
            if (found) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileBarang))) {
                    writer.write(updatedContent.toString());
                }
            } else {
                System.out.println("<Barang tidak ditemukan>");
            }
    
        } catch (IOException e) {
            System.out.println("Gagal memperbarui stok barang: " + e.getMessage());
        }
    }
    
    // method untuk menampilkan daftar barang
    public ArrayList<Barang> getDaftarBarang(){
        return daftarBarang;
    }

}
