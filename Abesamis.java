package bank.account;

import java.util.Scanner;

public class BankAccount {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Store usernames & passwords
        String[][] accounts = new String[50][2];
        // Store balances for each user
        double[] balances = new double[50];

        int count = 0; // number of registered accounts

        boolean running = true;

        while (running) {

            System.out.println("==== LOGIN SYSTEM ====");
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[3] Exit");
            System.out.print("Choose an option: ");

            String option = sc.nextLine();

            switch (option) {

                // LOGIN
                case "1":
                    System.out.println("--- LOGIN ---");
                    System.out.print("Enter Username: ");
                    String loginUser = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String loginPass = sc.nextLine();

                    int index = -1; // store index of logged-in user

                    for (int i = 0; i < count; i++) {
                        if (accounts[i][0].equals(loginUser) && accounts[i][1].equals(loginPass)) {
                            index = i;
                            break;
                        }
                    }

                    if (index == -1) {
                        System.out.println("Invalid username or password! Bruh.");
                        break;
                    }

                    System.out.println("Hello, " + loginUser + "! You are now logged in.");
                    startBanking(sc, loginUser, balances, index);
                    break;

                // REGISTER
                case "2":
                    System.out.println("--- REGISTER ---");

                    System.out.print("Enter Username: ");
                    String newUser = sc.nextLine();

                    boolean exists = false;
                    for (int i = 0; i < count; i++) {
                        if (accounts[i][0].equals(newUser)) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("Username already exists!");
                        System.out.println("Tip: Be Original.");
                        break;
                    }

                    System.out.print("Enter Password: ");
                    String pass1 = sc.nextLine();

                    System.out.print("Confirm Password: ");
                    String pass2 = sc.nextLine();

                    if (!pass1.equals(pass2)) {
                        System.out.println("Passwords do not match! Are you Dyslexic?");
                        break;
                    }

                    accounts[count][0] = newUser;
                    accounts[count][1] = pass1;
                    balances[count] = 0; // new account balance starts at 0
                    count++;

                    System.out.println("Registration Successful!");
                    break;

                // EXIT
                case "3":
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option dumbass! Try again.");
            }
        }

        sc.close();
    }


    // BANKING MENU (After Login)
    public static void startBanking(Scanner sc, String name, double[] balances, int index) {

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println("==== BANKING MENU ====");
            System.out.println("(1) Deposit");
            System.out.println("(2) Withdraw");
            System.out.println("(3) Check Balance");
            System.out.println("(4) Logout");
            System.out.print("Choose: ");

            String choice = sc.nextLine();

            switch (choice) {

                // DEPOSIT
                case "1":
                    System.out.print("Enter amount to Deposit: ");
                    double deposit = Double.parseDouble(sc.nextLine());

                    if (deposit == 0) {
                        System.out.println("Invalid Amount! lol.");
                    } else {
                        balances[index] += deposit;
                        System.out.println("Deposited: " + deposit);
                    }
                    break;

                // WITHDRAW
                case "2":
                    System.out.print("Enter amount to Withdraw: ");
                    double withdraw = Double.parseDouble(sc.nextLine());

                    if (withdraw > balances[index]) {
                        System.out.println("Your Card Declined!");
                    } else if (withdraw == 0) {
                        System.out.println("Invalid Amount! -_- ");
                    } else {
                        balances[index] -= withdraw;
                        System.out.println("Withdrew: " + withdraw);
                    }
                    break;

                // BALANCE CHECK
                case "3":
                    System.out.println("Your Current Balance: " + balances[index]);
                    break;

                // LOGOUT
                case "4":
                    System.out.println("Logged out. Goodbye, " + name + "!");
                    loggedIn = false;
                    break;

                default:
                    System.out.println("Invalid choice you dumbass!");
            }
        }
    }
}