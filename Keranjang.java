import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Keranjang {
    private List<Barang> daftarKeranjang;
    private String fileName = "Cart.txt";

    public Keranjang() {
        this.daftarKeranjang = new ArrayList<>();
    }

    // Menambahkan item ke dalam keranjang
    public void addToCart(Customer customer, Barang barang, int jumlah) {
        int is_found_primary_id = 0;
        is_found_primary_id = loadFromFile(fileName, barang, jumlah);
        if (is_found_primary_id == 0) {
            Barang item = new Barang(customer.getId(), barang.getIdBarang(), barang.getNamaBarang(), barang.getHargaBarang(), jumlah);
            daftarKeranjang.add(item);
            saveToFile(customer, fileName);
        } else if(is_found_primary_id == 1){
            saveToFile(customer, fileName);
        }
        daftarKeranjang.clear();
    }

    // Menyimpan keranjang ke dalam file Cart.txt
    public void saveToFile(Customer customer, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Barang item : daftarKeranjang) {
                writer.write(item.toString(customer.getId()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to Cart.txt.. " + e.getMessage());
        }
    }

    // Memuat keranjang dari file Cart.txt
    public int loadFromFile(String fileName, Barang barang, int jumlah) {
        int primaryCustomerId = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String customerId = parts[0];
                    String idBarang = parts[1];
                    String namaBarang = parts[2];
                    double hargaBarang = Double.parseDouble(parts[3]);
                    int jumlahBarangKeranjang = Integer.parseInt(parts[4]);
                    if (barang.getIdBarang().equals(idBarang)) {
                        jumlahBarangKeranjang += jumlah; 
                        primaryCustomerId = 1;
                    } else {
                    daftarKeranjang.add(new Barang(customerId, idBarang, namaBarang, hargaBarang, jumlahBarangKeranjang));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from Cart.txt.. " + e.getMessage());
        }

        return primaryCustomerId;
    }

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String customerId = parts[0];
                    String idBarang = parts[1];
                    String namaBarang = parts[2];
                    double hargaBarang = Double.parseDouble(parts[3]);
                    int jumlahBarangKeranjang = Integer.parseInt(parts[4]);
                    daftarKeranjang.add(new Barang(customerId, idBarang, namaBarang, hargaBarang, jumlahBarangKeranjang));  
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from Cart.txt.. " + e.getMessage());
        }
    }
    
    // Menampilkan daftar keranjang
    public void showCart(Customer customer){
        loadFromFile(fileName);
        System.out.println("+------------+------------------------------------------+-----------------+------------+");
                    System.out.printf("| %-10s | %-40s | %-15s | %-10s |\n", "ID Barang", "Nama Barang", "Harga", "Jumlah");
                    System.out.println("+------------+------------------------------------------+-----------------+------------+");                    
                    for (Barang barang : daftarKeranjang) {
                        // Menampilkan data barang dalam format tabel
                        if (barang.getCustomerId().equals(customer.getId())) {                        
                            System.out.printf("| %-10s | %-40s | %-15.2f | %-10d |\n",
                                barang.getIdBarang(),
                                barang.getNamaBarang(),
                                barang.getHargaBarang(),
                                barang.getStok());
                            System.out.println("+------------+------------------------------------------+-----------------+------------+");
                        }
                    }
        daftarKeranjang.clear();
    }
}
