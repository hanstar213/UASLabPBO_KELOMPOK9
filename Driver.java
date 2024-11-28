public abstract class Driver {
    protected Account user;

    public Driver(Account user){
        this.user = user;
    }

    public abstract void menu();
}
