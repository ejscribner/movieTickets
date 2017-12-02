/*
    Created By: Elliot J Scribner on 11/30/17
    Student ID: ejs320
    Lab #: **Num**
    MovieTickets: **Description**
 */

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.text.SimpleAttributeSet;
import java.util.Scanner;

public class MovieTickets {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome Message");
        Boolean loginToken = adminLogin("admin", "123", input);
        System.out.println(loginToken);
        //Manager enters data
        System.out.print("Name of movie: ");
        String movieName = getInput("", input);
        System.out.print("Price: ");
        int ticketPrice = getInput(10, input);
        int seatCount = 50;
        customer(movieName, ticketPrice);
    }
    public static void customer(String movieName, int ticketPrice) {
        System.out.println("Movie Name: " + movieName);
        System.out.println("Showtime: " + 6);
        System.out.println("Price: $" + ticketPrice);
    }
    public static Boolean adminLogin(String username, String password, Scanner input) {
        System.out.print("Please type your username: ");
        String enteredUsername = getInput("", input);
        System.out.print("Please type your password: ");
        String enteredPassword = getInput("", input);
        Boolean hasPasswordToken = false;
        if(enteredUsername.equals(username) && enteredPassword.equals(password)) {
            hasPasswordToken = true;
        }
        return hasPasswordToken;
    }
    public static int getInput(int dataType, Scanner input) { //variable dataType used to overload the method
        int value = input.nextInt();
        return value;
    }
    public static String getInput(String dataType, Scanner input) { //variable dataType used to overload the method
        String value = input.nextLine();
        return value;
    }
}
