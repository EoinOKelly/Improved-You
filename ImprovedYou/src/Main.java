import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();

        // Option to load accounts from database (optional, you could also skip this)
        accountManager.loadAccountsFromDatabase();

        System.out.println("Welcome to \"ImprovedYou\"");

        String username = "";
        String password = "";

        while (true) {
            System.out.print("\nPick an option:\n1. Sign Up\n2. Sign In\n");
            String optionIU = input.nextLine();

            if (optionIU.equals("1")) {
                System.out.print("Enter username: ");
                username = input.nextLine();
                System.out.print("Enter password: ");
                password = input.nextLine();
                accountManager.addAccount(username, password);
                break;
            } else if (optionIU.equals("2")) {
                System.out.print("Enter username: ");
                username = input.nextLine();
                System.out.print("Enter password: ");
                password = input.nextLine();

                if (accountManager.validateLogin(username, password)) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Invalid username or password.");
                }
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        // Continue with goal setting
        System.out.printf("%nWelcome %s%nWould you like to set a daily or weekly goal? [D/W] ", username);
        String optionDW = input.nextLine();

        while (!optionDW.equalsIgnoreCase("D") && !optionDW.equalsIgnoreCase("W")) {
            System.out.println("Please put a valid input [D/W]");
            optionDW = input.nextLine();
        }

        int goalAmount = 0;
        if (optionDW.equalsIgnoreCase("D")) {
            System.out.print("\nHow many daily goals would you like to set? ");
            goalAmount = input.nextInt();
        } else {
            System.out.print("\nHow many weekly goals would you like to set? ");
            goalAmount = input.nextInt();
        }

        int yTracker = 0;
        String[] goals = new String[goalAmount];
        input.nextLine();

        for (int i = 1; i <= goalAmount; i++) {
            System.out.printf("Input goal %d: ", i);
            String tempGoal = input.nextLine();
            goals[i - 1] = tempGoal;
        }

        for (int i = 1; i <= goalAmount; i++) {
            System.out.printf("Did you successfully meet this goal [Y/N]: \"%s\" ", goals[i - 1]);
            String tempResult = input.nextLine();

            if (tempResult.equals("Y")) {
                yTracker += 1;
            }
        }
        System.out.printf("You have met %d/%d results", yTracker, goalAmount);
    }
}
