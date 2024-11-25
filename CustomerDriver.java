
import java.util.Scanner;

public class CustomerDriver extends Driver {
    private Customer customer;

    public CustomerDriver(Customer customer){
        super(customer);
        this.customer = customer;
    }

    @Override
    public void menu(){
        Scanner input = new Scanner(System.in);
        ListBarang daftarBarang = new ListBarang();
        daftarBarang.loadFromFile("Barang.txt");

        if (daftarBarang.getDaftarBarang().isEmpty()) {
            System.out.println("Daftar barang kosong!");
        } else {
            System.out.println("Daftar barang berhasil dimuat.");
        }

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
                    break;
                case 2 :
                    // keranjang
                    break;

                case 3 :
                    // Checkout
                    
                    break;
                case 4 :
                    System.out.println("Terima kasih telah berbelanja.");
                    is_continue = 1;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
            if (is_continue == 1){
                break;
            }
        }
    }
}
