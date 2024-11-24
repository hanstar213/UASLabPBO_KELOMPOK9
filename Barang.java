public class Barang {
    private String nama;
    private double harga;

    public Barang(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return "Barang{" +
                "nama='" + nama + '\'' +
                ", harga=" + harga +
                '}';
    }
}
    

