/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

public class SaveHashMapToDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

        saveHashMapToDatabase(myHashMap);
    }

    public static void saveHashMapToDatabase(HashMap<String, String> hashMap) {
        // SQL INSERT statement
        String insertQuery = "INSERT INTO hashmap_data (map_key, map_value) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            // Iterate through the hashmap and insert each key-value pair
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                pstmt.setString(1, entry.getKey());
                pstmt.setString(2, entry.getValue());
                pstmt.addBatch(); // Add to batch for efficiency
            }

            // Execute batch insert
            pstmt.executeBatch();
            System.out.println("HashMap data saved to database successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// Account class to represent user accounts
class Account {
    String username;
    String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

// AccountManager class to manage accounts
class AccountManager {
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
        return account != null && account.password.equals(password);
    }

    public HashMap<String, Account> getAllAccounts() {
        return accounts;
    }
}

// Main class to integrate AccountManager and database operations
public class SaveHashMapToDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database"; // Replace with your DB URL
    private static final String USERNAME = "your_username"; // Replace with your DB username
    private static final String PASSWORD = "your_password"; // Replace with your DB password

    public static void main(String[] args) {
        // Initialize the AccountManager and add some accounts
        AccountManager accountManager = new AccountManager();
        accountManager.addAccount("user1", "password1");
        accountManager.addAccount("user2", "password2");

        // Save accounts to the database
        saveAccountsToDatabase(accountManager.getAllAccounts());
    }

    public static void saveAccountsToDatabase(HashMap<String, Account> accounts) {
        // SQL INSERT statement
        String insertQuery = "INSERT INTO accounts (username, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            // Iterate through the accounts hashmap and insert each account
            for (Map.Entry<String, Account> entry : accounts.entrySet()) {
                Account account = entry.getValue();
                pstmt.setString(1, account.username);
                pstmt.setString(2, account.password);
                pstmt.addBatch(); // Add to batch for efficiency
            }

            // Execute batch insert
            pstmt.executeBatch();
            System.out.println("Accounts saved to database successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

