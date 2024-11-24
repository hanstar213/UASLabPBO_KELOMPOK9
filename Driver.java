import java.util.ArrayList;

public abstract class Driver {
    // Konstruktor
    public Driver() {
        this.listBarang = new ArrayList<>();
    }

    // Metode abstrak untuk menampilkan barang
    public abstract void tampilkanBarang();

    // Metode abstrak untuk menambahkan barang
    public abstract void tambahBarang();

    // Metode abstrak untuk menghapus barang
    public abstract void hapusBarang();

    // Metode abstrak untuk mengedit barang
    public abstract void editBarang();
}
