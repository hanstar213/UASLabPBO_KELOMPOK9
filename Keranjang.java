import java.io.*;
import java.util.ArrayList;

public class Keranjang {
    private ArrayList<Barang> daftarKeranjang;
    public String fileName = "Cart.txt";

    public Keranjang() {
        this.daftarKeranjang = new ArrayList<>();
    }

    // Menambahkan item ke dalam keranjang
    public void addToCart(Customer customer, Barang barang, int jumlah) {
        // Muat data dari file Cart.txt ke daftarKeranjang
        loadFromFile(fileName);

        boolean foundPrimaryKey = false;

        // Perbarui data barang jika ditemukan
        for (Barang item : daftarKeranjang) {
            if (item.getIdBarang().equals(barang.getIdBarang()) && item.getCustomerId().equals(customer.getId())) {
                item.setStok(item.getStok() + jumlah); // Update jumlah
                foundPrimaryKey = true;
                break;
            }
        }

        // Jika primary key tidak ditemukan, tambahkan sebagai barang baru
        if (!foundPrimaryKey) {
            Barang itemBaru = new Barang(customer.getId(), barang.getIdBarang(), barang.getNamaBarang(), barang.getHargaBarang(), jumlah);
            daftarKeranjang.add(itemBaru);
        }

        // Simpan ulang semua data ke file
        saveToFile(customer, fileName);
    }

    // Menyimpan data daftar keranjang ke file Cart.txt
    public void saveToFile(Customer customer, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Barang item : daftarKeranjang) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to Cart.txt.. " + e.getMessage());
        }
    }

    // Memuat data dari file Cart.txt
    public void loadFromFile(String fileName) {
        daftarKeranjang.clear(); // Hapus daftar keranjang sebelum memuat ulang data

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

                    // Tambahkan data ke daftarKeranjang
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

    public void removeFromCart(String customerId, String idBarang) {
        loadFromFile(fileName); // Memuat data dari file Cart.txt
    
        // Menemukan dan menghapus barang berdasarkan customerId dan idBarang
        for (int i = 0; i < daftarKeranjang.size(); i++) {
            Barang item = daftarKeranjang.get(i);
            if (item.getCustomerId().equals(customerId) && item.getIdBarang().equals(idBarang)) {
                daftarKeranjang.remove(i);  // Hapus barang dari daftar
                saveToFile(null, fileName); // Simpan perubahan ke file Cart.txt
                return;
            }
        }
        System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan di keranjang.");
    }

    public ArrayList<Barang> getDaftarKeranjang() {
        return daftarKeranjang;
    }
}
