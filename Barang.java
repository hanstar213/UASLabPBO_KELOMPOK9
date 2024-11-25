public class Barang {
    private String idBarang;
    private String namaBarang;
    private double hargaBarang;
    private int stok;

    public Barang(String idBarang, String namaBarang, double hargaBarang, int stok){
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.stok = stok;
    }

    public String getIdBarang(){
        return idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public double getHargaBarang() {
        return hargaBarang;
    }

    public int getStok() {
        return stok;
    }

    @Override
    public String toString() {
        return idBarang + namaBarang + hargaBarang + stok;
    }
}
