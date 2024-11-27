public class Customer extends Account {
    private Keranjang keranjang;

    public Customer(String id, String userName, String password) {
        super(id, userName, password);
        this.keranjang = new Keranjang();
    }

    public Keranjang getKeranjang() {
        return keranjang;
    }
}
