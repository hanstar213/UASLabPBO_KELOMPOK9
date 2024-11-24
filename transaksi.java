
import java.util.ArrayList;

public class transaksi {
    private Customer customer;
    private ArrayList<Barang> barang;
    private boolean isApproved;

    public Transaksi(Customer customer, ArrayList<Barang> barang) {
        this.customer = customer;
        this.barang = barang;
        this.isApproved = false;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    public void setBarang(ArrayList<Barang> barang) {
        this.barang = barang;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public void approve() {
        this.isApproved = true;
    }
}


@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer Id: ").append(customer.getId()).append("\n");
        sb.append("List barang yang dibeli:\n");
        for (Barang b : barang) {
            sb.append("- ").append(b.getNama()).append(": Rp ").append(b.getHarga()).append("\n");
        }
        return sb.toString();
    }
