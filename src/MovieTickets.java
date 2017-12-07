/*
    Created By: Elliot J Scribner on 11/30/17
    Student ID: ejs320
    Final Project
    MovieTickets: Machine to purchase movie tickets
 */

import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.text.SimpleAttributeSet;
import java.util.Scanner;

public class MovieTickets {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Powering On");
        Boolean loginToken = adminLogin("admin", "123", input);
        String movieName = null;
        double ticketPrice = 0.0;
        int seatCount = 50;
        if(loginToken) {
            movieName = getMovieName(input);
            ticketPrice = getTicketPrice(input);
            System.out.println("Manager setup complete!");
            lineBreak();
        }
        do {
            int seatsSold = customerInteraction(movieName, ticketPrice, seatCount, input);
            seatCount -= seatsSold;
        } while (true);
    }
    public static void lineBreak() {
        System.out.println("-----------------------------------");
    }
    public static int customerInteraction(String movieName, double ticketPrice, int seatCount, Scanner input) {
        customerDisplay(movieName, ticketPrice, seatCount, input);
        int numTix = customerTicketNumber(input);
        double change = cashOut(numTix, ticketPrice, input);
        System.out.println("Your change is: " + change);
        lineBreak();
        return numTix;
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

    public static void customerDisplay (String movieName, double ticketPrice, int seatCount, Scanner input) {
        System.out.println("Welcome to the theater! Press '1' to purchase tickets or '2' to access the manager dashboard");
        int purchaseAuth = getInput(6, input);
        if (purchaseAuth == 2) {
            lineBreak();
            System.out.println("Please log in to view dashboard");
            Boolean logoutToken = adminLogout("admin", "123", movieName, ticketPrice, seatCount, input);
            if(logoutToken) {
                mgrDash(movieName, ticketPrice, seatCount, input);
            }
        }if (purchaseAuth == 1) {
            System.out.println("Movie Name: " + movieName);
            System.out.println("Showtime: " + 6);
            System.out.println("Price: $" + ticketPrice);
        } else {
            System.out.println("Invalid entry");
            customerDisplay(movieName, ticketPrice, seatCount, input);
        }
    }
    public static void mgrDash(String movieName, double ticketPrice, int seatCount, Scanner input) {
        int seatsRemaining = 50-seatCount;
        double cashMade = seatsRemaining*ticketPrice;
        System.out.println("Manager Dashboard");
        System.out.println("-------------------");
        System.out.println("Today's Movie: " + movieName);
        System.out.println("Ticket Price: " + ticketPrice);
        System.out.println("Tickets sold: " + seatCount);
        System.out.println("Money earned: " + cashMade);
        System.out.println("Press 1 to power off the machine, or 2 to return to customer mode");
        int offToken = getInput(1, input);
        if(offToken == 1) {
            System.out.println("Powering Down");
            System.exit(0);
        } else if(offToken == 2) {
            customerDisplay(movieName, ticketPrice, seatCount, input);
        } else {
            System.out.println("Invalid entry");
            mgrDash(movieName, ticketPrice, seatCount, input);
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
            lineBreak();
        }
        if(!hasPasswordToken){
            System.out.println("Incorrect Username or Password, please try again!");
            lineBreak();
            hasPasswordToken = adminLogin(username, password, input);
        }
        return hasPasswordToken;
    }

    public static Boolean adminLogout(String username, String password, String movieName, double ticketPrice, int seatCount, Scanner input) {
        getInput("", input);
        System.out.print("Please type your username: ");
        String enteredUsername = getInput("", input);
        if(enteredUsername.equalsIgnoreCase("1")) {
            lineBreak();
            customerInteraction(movieName, ticketPrice, seatCount, input);
        }
        System.out.print("Please type your password: ");
        String enteredPassword = getInput("", input);
        Boolean hasPasswordToken = false;
        if(enteredUsername.equals(username) && enteredPassword.equals(password)) {
            hasPasswordToken = true;
        }
        if(!hasPasswordToken){
            System.out.println("Incorrect Username or Password, returning to customer mode");
            lineBreak();
            customerInteraction(movieName, ticketPrice, seatCount, input);
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
