public abstract class pembayaran {
    protected String id;

    public pembayaran(String id) {
        this.id = id;
    }
   
    public abstract void prosesPembayaran();
}


// Subkelas Pembayaran
public class QRIS extends Pembayaran {
    public QRIS(String id) {
        super(id);
    }

    @Override
    public void prosesPembayaran() {
    }
}

public class Bank extends Pembayaran {
    public Bank(String id) {
        super(id);
    }

    @Override
    public void prosesPembayaran() {
    }
}

public class COD extends Pembayaran {
    public COD(String id) {
        super(id);
    }

    @Override
    public void prosesPembayaran() {
    }
}
