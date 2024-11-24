public abstract class pembayaran {
    protected String id;

    public pembayaran(String id) {
        this.id = id;
    }
   
    public abstract void prosesPembayaran();
}
