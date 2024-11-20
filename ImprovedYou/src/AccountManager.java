/*import java.util.Scanner;

public class Account {

    public static String[] signUp(){
        Scanner input = new Scanner(System.in);
        String[] account = new String[2];

        System.out.print("Please input an account name: ");
        String name = input.nextLine();
        System.out.print("Please input a password: ");
        String password = input.nextLine();
        account[0] = name;
        account[1] = password;
        return account;
    }
    public static String[] signIn(){
        Scanner input = new Scanner(System.in);
        String[] account = new String[2];

        System.out.print("Please input an account name: ");
        String name = input.nextLine();
        System.out.print("Please input a password: ");
        String password = input.nextLine();
        account[0] = name;
        account[1] = password;
        return account;
    }
}*/
import java.util.HashMap;

class Account {
    String username;
    String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class AccountManager {
    private HashMap<String, Account> accounts = new HashMap<>();

    public void addAccount(String username, String password) {
        if (accounts.containsKey(username)) {
            System.out.println("Account already exists!");
            return;
        }
        accounts.put(username, new Account(username, password));
        System.out.println("Account added successfully!");
    }

    public Account getAccount(String username) {
        return accounts.get(username);
    }

    public boolean validateLogin(String username, String password) {
        Account account = accounts.get(username);
        if (account != null && account.password.equals(password)) {
            return true;
        }
        return false;
    }
}
