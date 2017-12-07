/*
    Created By: Elliot J Scribner on 11/30/17
    Student ID: ejs320
    Final Project
    MovieTickets: Machine to purchase movie tickets
 */

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.text.SimpleAttributeSet;
import java.util.Scanner;

public class MovieTickets {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Powering On");
        Boolean loginToken = adminLogin("admin", "123", input);
        //Manager enters data
        String movieName = null;
        double ticketPrice = 0.0;
        if(loginToken) {
            movieName = getMovieName(input);
            ticketPrice = getTicketPrice(input);
            System.out.println("Manager setup complete! To access the manager dashboard, press '2' after seeing the welcome message at any time");
        }
        do {
            customerInteraction(movieName, ticketPrice, input);
        } while (true);
    }
    public static void customerInteraction(String movieName, double ticketPrice, Scanner input) {
        customerDisplay(movieName, ticketPrice, input);
        int numTix = customerTicketNumber(input);
        double change = cashOut(numTix, ticketPrice, input);
        System.out.println("Your change is: " + change);
    }

    public static String getMovieName(Scanner input) {
        System.out.print("Name of movie: ");
        String movieName = getInput("", input);
        return movieName;
    }

    public static double getTicketPrice(Scanner input) {
        System.out.print("Price: ");
        double ticketPrice = getInput(10.01, input);
        return ticketPrice;
    }

    public static void customerDisplay (String movieName, double ticketPrice, Scanner input) {
        System.out.println("Welcome to the theater! Press '1' to purchase tickets or '2' to access the manager dashboard");
        int purchaseAuth = getInput(6, input);
        if (purchaseAuth == 2) {
            System.out.println("Please enter your username and password to power off the machine");
            Boolean logoutToken = adminLogout("admin", "123", movieName, ticketPrice, input);
            if(logoutToken) {
                System.exit(0);
            }
        }if (purchaseAuth == 1) {
            System.out.println("5");
            System.out.println("Movie Name: " + movieName);
            System.out.println("Showtime: " + 6);
            System.out.println("Price: $" + ticketPrice);
        } else {
            System.out.println("Invalid entry");
            customerDisplay(movieName, ticketPrice, input);
        }
    }

    public static int customerTicketNumber (Scanner input) {
        System.out.println("How many tickets would you like to purchase?");
        int numTix = getInput(4, input);
        return numTix;
    }

    public static double cashOut(int numTix, double ticketPrice, Scanner input) {
        double cashCharged = 0;
        double cashIn = 0;
        double cashOut = 0;
        cashCharged = numTix*ticketPrice;
        System.out.println("Your total is $" + cashCharged);
        System.out.println("Please insert your cash. (Type the number as xx.xx)");
        cashIn = getInput(1.1, input);
        cashOut = cashIn-cashCharged;
        if(cashIn < cashCharged) {
            System.out.print("Not enough cash inserted. ");
            return cashOut(numTix, ticketPrice, input);
        }
        return cashOut;
    }

    public static Boolean adminLogin(String username, String password, Scanner input) {
        System.out.print("Please type your username: ");
        String enteredUsername = getInput("", input);
        System.out.print("Please type your password: ");
        String enteredPassword = getInput("", input);
        Boolean hasPasswordToken = false;
        if(enteredUsername.equals(username) && enteredPassword.equals(password)) {
            hasPasswordToken = true;
            System.out.println("Login Successful!");
        }
        if(!hasPasswordToken){
            System.out.println("Incorrect Username or Password, please try again!");
            hasPasswordToken = adminLogin(username, password, input);
        }
        return hasPasswordToken;
    }

    public static Boolean adminLogout(String username, String password, String movieName, double ticketPrice, Scanner input) {
        System.out.print("Please type your username: ");
        String enteredUsername = getInput("", input);
        if(enteredUsername.equalsIgnoreCase("p")) {
            customerInteraction(movieName, ticketPrice, input);
        }
        System.out.print("Please type your password: ");
        String enteredPassword = getInput("", input);
        Boolean hasPasswordToken = false;
        if(enteredUsername.equals(username) && enteredPassword.equals(password)) {
            hasPasswordToken = true;
            System.out.println("Powering Down");
        }
        if(!hasPasswordToken){
            System.out.println("Incorrect Username or Password, please try again or press 'p' to purchase tickets");
            hasPasswordToken = adminLogout(username, password, movieName, ticketPrice, input);
        }
        return hasPasswordToken;
    }

    public static int getInput(int dataTypeInt, Scanner input) { //variable dataType used to overload the method
        int value = input.nextInt();
        return value;
    }

    public static double getInput(double dataTypeDouble, Scanner input) { //variable dataType used to overload the method
        double value = input.nextDouble();
        return value;
    }

    public static String getInput(String dataTypeString, Scanner input) { //variable dataType used to overload the method
        String value = input.nextLine();
        return value;
    }

}
