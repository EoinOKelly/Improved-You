import java.sql.*;
import java.util.HashMap;
import java.util.Map;

class Account {
    String username;
    String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class AccountManager {
    private static final String DB_URL = "jdbc:sqlite:C:/Users/eoino/SQLite/improved_you_databasee.db"; // Adjust path as needed
    private HashMap<String, Account> accounts = new HashMap<>();

    // Constructor initializes the database connection and table
    public AccountManager() {
        createTableIfNotExists();
    }

    // Create table if not exists
    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts (username TEXT PRIMARY KEY, password TEXT)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add account both in-memory and in the database
    public void addAccount(String username, String password) {
        // Check if account exists in memory or database
        if (accounts.containsKey(username) || accountExistsInDatabase(username)) {
            System.out.println("Account already exists!");
            return;
        }

        // Add to in-memory HashMap
        accounts.put(username, new Account(username, password));

        // Insert into database
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO accounts (username, password) VALUES (?, ?)")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("Account added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Validate login credentials
    public boolean validateLogin(String username, String password) {
        // First check in-memory accounts
        Account account = accounts.get(username);
        if (account != null && account.password.equals(password)) {
            return true;
        }

        // Then check the database
        return validateLoginInDatabase(username, password);
    }

    // Check if account exists in the database
    private boolean accountExistsInDatabase(String username) {
        String query = "SELECT COUNT(*) FROM accounts WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Validate login from the database
    private boolean validateLoginInDatabase(String username, String password) {
        String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Load all accounts from the database to in-memory HashMap (if necessary)
    public void loadAccountsFromDatabase() {
        String query = "SELECT * FROM accounts";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                accounts.put(username, new Account(username, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
