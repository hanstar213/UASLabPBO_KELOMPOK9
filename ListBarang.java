import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; 

public class ListBarang {
    private ArrayList<Barang> daftarBarang;
    private String fileName = "Barang.txt";

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

    // Method untuk menambahkan barang
    public void tambahBarang(Scanner scanner) {
        daftarBarang.clear();
        System.out.print("Masukkan ID Barang: ");
        String idBarang = scanner.nextLine();
        System.out.print("Masukkan Nama Barang: ");
        String namaBarang = scanner.nextLine();
        System.out.print("Masukkan Harga Barang: ");
        double hargaBarang = scanner.nextDouble();
        System.out.print("Masukkan Stok Barang: ");
        int stok = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("\nApakah anda yakin ingin menambahkan barang tersebut? (y/n) :");
        String konfirmasi = scanner.nextLine();
            if (konfirmasi.equalsIgnoreCase("y")) {     
                Barang barang = new Barang(idBarang, namaBarang, hargaBarang, stok);
                loadFromFile(fileName);
                daftarBarang.add(barang);
                saveToFile(fileName); // Simpan perubahan ke file
                System.out.println("\n<Barang berhasil ditambahkan>");

            } else if(konfirmasi.equalsIgnoreCase("n")) {
                System.out.println("\n<Penambahan barang ke dibatalkan>");
            } else {
                System.out.println("\n<Pilihan tidak valid>");
            }
        daftarBarang.clear();
    }

    // Method untuk menghapus barang berdasarkan ID
    public void hapusBarang(Scanner scanner) {
        System.out.print("Masukkan ID Barang yang akan dihapus: ");
        String idBarang = scanner.nextLine();
        loadFromFile(fileName);
        boolean found = false;
        Barang removeBarang = new Barang();
            for (Barang barang : daftarBarang) {
                if (barang.getIdBarang().equals(idBarang)) {
                    removeBarang = barang; 
                    found = true;
                    break;
                    }
                }

        if (found) {
            
            Main.clearScreen();
            System.out.println("Barang yang ingin dihapus : ");
            System.out.println("ID Barang\t\t : " + removeBarang.getIdBarang());
            System.out.println("Nama Barang\t\t : " + removeBarang.getNamaBarang());
            System.out.println("Harga Barang\t\t : " + removeBarang.getHargaBarang());
            System.out.println("Stok Barang\t\t : " + removeBarang.getStok());

            System.out.print("\nApakah anda yakin ingin menghapus barang tersebut? (y/n) :");
            String konfirmasi = scanner.nextLine();
                if (konfirmasi.equalsIgnoreCase("y")) {     
                    daftarBarang.remove(removeBarang);
                    saveToFile(fileName); // Simpan perubahan ke file
                    System.out.println("\n<Barang dengan ID " + idBarang + " berhasil dihapus>");
                    daftarBarang.clear();
                    scanner.nextLine();
                    if (!found) {
                        System.out.println("<Barang dengan ID " + idBarang + " tidak ditemukan>");
                    }
                } else if(konfirmasi.equalsIgnoreCase("n")) {
                    System.out.println("\n<Penghapusan barang ke dibatalkan>");
                    scanner.nextLine();
                } else {
                    System.out.println("\n<Pilihan tidak valid>");
                    scanner.nextLine();
                }
            daftarBarang.clear();
        }
        else if (!found) {
                System.out.println("\n<Barang dengan ID " + idBarang + " tidak ditemukan>");
        }    
    }

    // Method untuk mengedit barang berdasarkan ID
    public void editBarang(Scanner scanner) {
        System.out.print("Masukkan ID Barang yang akan diedit: ");
        String idBarang = scanner.nextLine();
        daftarBarang.clear();
        loadFromFile(fileName);
        boolean found = false;
        Barang editBarang = new Barang();
        for (Barang barang : daftarBarang) {
            if (barang.getIdBarang().equals(idBarang)) {
                editBarang = barang; 
                found = true;
                break;
                }
            }

        if (found) {
            Main.clearScreen();
            System.out.println("Barang yang ingin diedit : ");
            System.out.println("ID Barang\t\t : " + editBarang.getIdBarang());
            System.out.println("Nama Barang\t\t : " + editBarang.getNamaBarang());
            System.out.println("Harga Barang\t\t : " + editBarang.getHargaBarang());
            System.out.println("Stok Barang\t\t : " + editBarang.getStok());

            System.out.print("\nMasukkan Nama Barang baru: ");
            String namaBarang = scanner.nextLine();
            System.out.print("Masukkan Harga Barang baru: ");
            double hargaBarang = scanner.nextDouble();
            System.out.print("Masukkan Stok Barang baru: ");
            int stok = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.print("\nApakah anda yakin ingin menghapus barang tersebut? (y/n) :");
            String konfirmasi = scanner.nextLine();
                if (konfirmasi.equalsIgnoreCase("y")) {     
                        editBarang.setNamaBarang(namaBarang);
                        editBarang.setHargaBarang(hargaBarang);
                        editBarang.setStok(stok);
                        saveToFile(fileName); // Simpan perubahan ke file
                        
                        System.out.println("\n<Barang dengan ID " + idBarang + " berhasil diedit>");

                } else if(konfirmasi.equalsIgnoreCase("n") || found) {
                    System.out.println("\n<Pengeditan barang ke dibatalkan>");
                    scanner.nextLine();
                } else {
                    System.out.println("\n<Pilihan tidak valid>");
                    scanner.nextLine();
                }
            daftarBarang.clear();
        }
        else if (!found) {
                System.out.println("\n<Barang dengan ID " + idBarang + " tidak ditemukan>");
        }    
    }

    // Method untuk memperbarui stok barang dengan mengurangi jumlah yang diberikan
    public void updateStok(String idBarang, int jumlah) {
        for (Barang barang : daftarBarang) {
            if (barang.getIdBarang().equals(idBarang)) {
                int stokBaru = barang.getStok() - jumlah;
                if (stokBaru >= 0) {
                    barang.setStok(stokBaru); // Update stok jika jumlah cukup
                    saveToFile(fileName); // Simpan perubahan ke file setelah stok diperbarui
                } else {
                    System.out.println("<Stok tidak mencukupi untuk pengurangan>");
                }
            }
        }
    }

    // Method untuk memperbarui file setelah perubahan
    public void updateAndSave(String fileName, String idBarang, String namaBarang, double hargaBarang, int stok) {
        if (updateBarang(idBarang, namaBarang, hargaBarang, stok)) {
            saveToFile(fileName); // Simpan perubahan ke file jika update berhasil
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
    
    // Method untuk menampilkan d   aftar barang
    public ArrayList<Barang> getDaftarBarang() {
        return daftarBarang;
    }
}
