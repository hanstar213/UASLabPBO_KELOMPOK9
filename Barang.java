public class Barang {
    private String customerId;
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

    public Barang(String customerId, String idBarang, String namaBarang, double hargaBarang, int stok){
        this.customerId = customerId;
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.stok = stok;
    }

    // Getter untuk idBarang
    public String getIdBarang(){
        return idBarang;
    }

    // Setter untuk idBarang
    public void setIdBarang(String idBarang) {
        if (idBarang == null || idBarang.trim().isEmpty()) {
            System.out.println("<ID Barang tidak boleh kosong>");
            return;
        }
        this.idBarang = idBarang;
    }

    // Getter untuk namaBarang
    public String getNamaBarang() {
        return namaBarang;
    }

    // Setter untuk namaBarang
    public void setNamaBarang(String namaBarang) {
        if (namaBarang == null || namaBarang.trim().isEmpty()) {
            System.out.println("<Nama Barang tidak boleh kosong>");
            return;
        }
        this.namaBarang = namaBarang;
    }

    // Getter untuk hargaBarang
    public double getHargaBarang() {
        return hargaBarang;
    }

    // Setter untuk hargaBarang
    public void setHargaBarang(double hargaBarang) {
        if (hargaBarang < 0) {
            System.out.println("<Harga Barang tidak boleh negatif>");
            return;
        }
        this.hargaBarang = hargaBarang;
    }

    // Getter untuk stok
    public int getStok() {
        return stok;
    }

    // Setter untuk stok
    public void setStok(int stok) {
        if (stok < 0) {
            System.out.println("<Stok tidak boleh bernilai negatif>");
            return;
        }
        this.stok = stok;
    }

    public String getCustomerId(){
        return customerId;
    }

    @Override
    public String toString() {
        return customerId + "," + idBarang + "," + namaBarang + "," + hargaBarang + "," + stok;
    }
}
