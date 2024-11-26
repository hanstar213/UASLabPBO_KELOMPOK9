
import java.util.Scanner;

public class CustomerDriver extends Driver {
    private Customer customer;
    private ListBarang daftarBarang;

    public CustomerDriver(Customer customer){
        super(customer);
        this.customer = customer;
        this.daftarBarang = new ListBarang();
        this.daftarBarang.loadFromFile("Barang.txt");
    }

    @Override
    public void menu(){
        Scanner input = new Scanner(System.in);

        while(true){
            System.out.println("\nMenu Pelanggan:");
            System.out.println("1. Lihat Barang");
            System.out.println("2. Tambah ke Keranjang");
            System.out.println("3. Checkout");
            System.out.println("4. Keluar");
            System.out.print("Pilihan: ");
            int pilihan = input.nextInt();
            input.nextLine();
            
            int is_continue=0;
            switch(pilihan){
                case 1 :
                    showItems();
                    break;
                case 2 :
                    // keranjang
                    cart(input);
                    break;

                case 3 :
                    // Checkout
                    customer.getKeranjang().showCart(customer);
                    break;
                case 4 :
                    System.out.println("<Terima kasih telah berbelanja>");
                    is_continue = 1;
                    break;
                default:
                    System.out.println("<Pilihan tidak valid>.");
                    break;
            }
            if (is_continue == 1){
                break;
            }
        }
    }

    public void showItems(){
        System.out.println("+------------+------------------------------------------+-----------------+------------+");
                    System.out.printf("| %-10s | %-40s | %-15s | %-10s |\n", "ID Barang", "Nama Barang", "Harga", "Stok");
                    System.out.println("+------------+------------------------------------------+-----------------+------------+");                    
                    for (Barang barang : daftarBarang.getDaftarBarang()) {
                        // Menampilkan data barang dalam format tabel
                        System.out.printf("| %-10s | %-40s | %-15.2f | %-10d |\n",
                                barang.getIdBarang(),
                                barang.getNamaBarang(),
                                barang.getHargaBarang(),
                                barang.getStok());
                            System.out.println("+------------+------------------------------------------+-----------------+------------+");
                    }
    }

    public void cart(Scanner input){
        System.out.println("Keranjang");
                    System.out.println("=====================");
                    System.out.print("Masukkan id barang atau nama barang yang ingin dicari : ");
                    String cartInput = input.nextLine();
                    System.out.print("Masukkan Jumlah : ");
                    int jumlah  = input.nextInt();
                    input.nextLine();
                    Barang barangDipilih = null;

                    for (Barang barang : daftarBarang.getDaftarBarang()) {
                        if (barang.getIdBarang().equals(cartInput) || barang.getNamaBarang().equals(cartInput)) {
                            barangDipilih = barang;
                            break;
                        }
                    }

                    if (barangDipilih != null && jumlah <= barangDipilih.getStok()) {
                        // Tambahkan ke keranjang
                        customer.getKeranjang().addToCart(customer, barangDipilih, jumlah);

                        // Kurangi stok barang
                        barangDipilih.setStok(barangDipilih.getStok() - jumlah);
                        System.out.println("Barang berhasil ditambahkan ke keranjang.");
                    } else {
                        System.out.println("Barang tidak ditemukan atau stok tidak mencukupi.");
                    }
    }

}
