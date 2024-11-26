import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListBarang {
    private List<Barang> daftarBarang;

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

    // method untuk menampilkan daftar barang
    public List<Barang> getDaftarBarang(){
        return daftarBarang;
    }

}