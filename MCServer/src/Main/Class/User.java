package Main.Class;

/**
 * 用户类
 */
public class User {
    /**
     * 账号
     */
    public String account;

    /**
     * 用户名
     */
    public String name;

    /**
     * 密码
     */
    public String password;

    public User(String account, String name, String password) {
        this.account = account;
        this.name = name;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
