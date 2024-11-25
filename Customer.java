public class Customer extends Account {
    private Keranjang keranjang;

    public Customer(String id, String userName, String password) {
        super(id, userName, password);
        this.keranjang = new Keranjang();
    }

    @Override
    public void login() {
        System.out.println("Customer " + userName + " berhasil login.");
    }

    public Keranjang getKeranjang() {
        return keranjang;
    }
}
