import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to \"ImprovedYou\"");
        AccountManager accountManager = new AccountManager();

        String username = "";
        String password = "";

        while (true) {
            System.out.print("\nPick an option:\n1. Sign Up\n2. Sign In\n");
            String optionIU = input.nextLine();
            switch (optionIU) {
                case "1":
                    System.out.print("Enter username: ");
                    username = input.nextLine();
                    System.out.print("Enter password: ");
                    password = input.nextLine();
                    accountManager.addAccount(username, password);
                    break;
                case "2":
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
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.printf("%nWelcome %s%nWould you like to set a daily or weekly goal? [D/W] ", username);
        String optionDW = input.nextLine();

        while(!optionDW.equalsIgnoreCase("D") & !optionDW.equalsIgnoreCase("W")){
            System.out.println("Please put a valid input [D/W] ");
            optionDW = input.nextLine();
        }

        int goalAmmount = 0;
        if(optionDW.equalsIgnoreCase("D")){
            System.out.print("\nHow many daily goals would you like to set? ");
            goalAmmount = input.nextInt();
        }
        else {
            System.out.print("\nHow many weekly goals would you like to set? ");
            goalAmmount = input.nextInt();
        }

        int yTracker = 0;
        String[] goals = new String[goalAmmount];
        input.nextLine();

        for (int i = 1; i <= goalAmmount; i++){
            System.out.printf("Input goal %d: ", i);
            String tempGoal = input.nextLine();
            goals[i - 1] = tempGoal;
        }

        for (int i = 1; i <= goalAmmount; i++){
            System.out.printf("Did you successfully meet this goal [Y/N]: \"%s\" ", goals[i-1]);
            String tempResult = input.nextLine();

            if (tempResult.equals("Y")){
                yTracker += 1;
            }
        }
        System.out.printf("You have met %d/%d results", yTracker, goalAmmount);
    }
}