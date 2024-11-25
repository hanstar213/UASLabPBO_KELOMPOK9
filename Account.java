public abstract class Account{
    protected String id = "000";
    protected String userName = "NULL";
    protected String password = "NULL";

    public Account(String id, String userName, String password){
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public String getId(){
        return id;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public abstract void login();

}