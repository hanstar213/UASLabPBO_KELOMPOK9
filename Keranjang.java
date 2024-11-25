import java.util.ArrayList;
import java.util.List;

public class Keranjang {
    private List<Barang> listBarang;
    private List<Integer> jumlahBarang;

    public Keranjang() {
        this.listBarang = new ArrayList<>();
        this.jumlahBarang = new ArrayList<>();
    }

    public void addToCart(Barang barang, int jumlah) {
        listBarang.add(barang);
        jumlahBarang.add(jumlah);
    }

    public void clearCart() {
        listBarang.clear();
        jumlahBarang.clear();
    }

    public List<Barang> getListBarang() {
        return listBarang;
    }

    public double getTotalHarga() {
        double total = 0;
        for (int i = 0; i < listBarang.size(); i++) {
            total += listBarang.get(i).getHargaBarang() * jumlahBarang.get(i);
        }
        return total;
    }
}
