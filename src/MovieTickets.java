/*
    Created By: Elliot J Scribner on 11/30/17
    Student ID: ejs320
    Lab #: **Num**
    MovieTickets: **Description**
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
        int seatCount;
        if(loginToken) {
            movieName = getMovieName(input);
            ticketPrice = getTicketPrice(input);
            seatCount = 50;
            System.out.println("Admin setup complete! To power off system, press 'q' after seeing the welcome message at any time");
        }
        customerDisplay(movieName, ticketPrice, input);
        int numTix = customerTicketNumber(input);
        double change = cashOut(numTix, ticketPrice, input);
        System.out.print(change);
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
        System.out.println("Welcome to the theater! Press 'p' to purchase tickets");
        String purchaseAuth = getInput(" ", input);
        if (purchaseAuth.equalsIgnoreCase("q")) {
            System.exit(0);
        }
        if (purchaseAuth.equalsIgnoreCase("p")) {
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
        double cashCharged = numTix*ticketPrice;
        System.out.println("Please insert your cash. (Type the number as xx.xx)");
        double cashIn = getInput(1.1, input);
        System.out.println(cashIn);
        System.out.println(cashCharged);
        if (cashIn < cashCharged) {
            System.out.println("Not enough entered, please try again!");
            cashOut(numTix, ticketPrice, input);
        }
        System.out.println(cashIn);
        System.out.println(cashCharged);
        double cashOut = cashIn-cashCharged;
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

    public static int getInput(int dataType, Scanner input) { //variable dataType used to overload the method
        int value = input.nextInt();
        return value;
    }

    public static double getInput(double dataType, Scanner input) { //variable dataType used to overload the method
        double value = input.nextDouble();
        return value;
    }

    public static String getInput(String dataType, Scanner input) { //variable dataType used to overload the method
        String value = input.nextLine();
        return value;
    }

}
