import java.util.ArrayList;

public class Transaksi {
    private Customer akun;
    private ArrayList<Barang> barang;

    public Transaksi(Customer akun) {
        this.akun = akun;
        this.barang = new ArrayList<>();
    }

    public Customer getAkun() {
        return akun;
    }

    public void addBarang(Barang barangItem) {
        barang.add(barangItem);
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }
}
