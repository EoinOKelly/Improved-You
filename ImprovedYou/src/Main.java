import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Welcome to \"ImprovedYou\", would you like to sign in or sign up? [I/U] ");
        String optionIU = input.nextLine();
        String[] newAccount = new String[2];

        if(optionIU.equalsIgnoreCase("U")){
            newAccount = signUp();
        }
        System.out.printf("%nWelcome %s%nWould you like to set a daily or weekly goal? [D/W] ", newAccount[0]);
        String optionDW = input.nextLine();

        while(!optionDW.equalsIgnoreCase("D") & !optionDW.equalsIgnoreCase("W")){
            System.out.println("Please put a valid input [D/W] ");
            optionDW = input.nextLine();
        }

        int goalAmmount = 0;
        if(optionDW.equals("D") || optionDW.equals("d")){
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
}