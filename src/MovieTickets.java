/*
    Created By: Elliot J Scribner on 11/30/17
    Student ID: ejs320
    Final Project
    MovieTickets: Machine to purchase movie tickets
 */


import java.util.Scanner;

public class MovieTickets {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("***********");
        System.out.println("Powering On");
        System.out.println("***********");
        System.out.println("");
        Boolean loginToken = adminLogin("admin", "123", input);
        String movieName = null; //set initial movie name
        double ticketPrice = 0.0; //set initial ticket price
        int seatCount = 50; //set initial seat count
        //check if login is authenticated
        if(loginToken) {
            movieName = getMovieName(input);
            ticketPrice = getTicketPrice(input);
            System.out.println("Manager setup complete!");
            lineBreak();
        }
        //run customer interactions until manager quits the program
        do {
            int seatsSold = customerInteraction(movieName, ticketPrice, seatCount, input);
            seatCount = seatCount - seatsSold;
        } while (true);
    }

    //adds a line break for easier user experience
    public static void lineBreak() {
        System.out.println("-----------------------------------");
    }

    //runs through steps needed to interact with a customer purchasing tickets, returns number purchases and prints change
    public static int customerInteraction(String movieName, double ticketPrice, int seatCount, Scanner input) {
        System.out.println("");
        customerDisplay(movieName, ticketPrice, seatCount, input);
        int numTix = customerTicketNumber(input);
        double change = cashOut(numTix, ticketPrice, input);
        System.out.println("Your change is $" + change);
        lineBreak();
        return numTix;
    }

    //asks admin for movie title of the day
    public static String getMovieName(Scanner input) {
        System.out.print("Name of movie: ");
        String movieName = getInput("", input);
        return movieName;
    }

    //asks admin for ticket price for the day
    public static double getTicketPrice(Scanner input) {
        System.out.print("Price: ");
        double ticketPrice = getInput(10.01, input);
        return ticketPrice;
    }

    //displays prompts for customer ticket buying experience
    public static void customerDisplay (String movieName, double ticketPrice, int seatCount, Scanner input) {
        System.out.println("Welcome to the theater! Press '1' to purchase tickets or '2' to access the manager dashboard");
        int purchaseAuth = getInput(6, input);
        if (purchaseAuth == 2) {
            lineBreak();
            System.out.println("Please log in to view dashboard");
            Boolean logoutToken = adminPannelAccess("admin", "123", movieName, ticketPrice, seatCount, input);
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

    //displays the manager dashboard
    public static void mgrDash(String movieName, double ticketPrice, int seatCount, Scanner input) {
        int seatsSold = 50-seatCount;
        double cashMade = seatsSold*ticketPrice;
        System.out.println("");
        System.out.println("Manager Dashboard");
        System.out.println("-------------------");
        System.out.println("Today's Movie: " + movieName);
        System.out.println("Ticket Price: $" + ticketPrice);
        System.out.println("Tickets sold: " + seatsSold);
        System.out.println("Money earned: $" + cashMade);
        System.out.println("Press 1 to return to customer mode, or press 2 to power off the machine");
        int offToken = getInput(1, input);
        if(offToken == 2) {
            System.out.println("");
            System.out.println("*************");
            System.out.println("Powering Down");
            System.out.println("*************");
            System.exit(0);
        } else if(offToken == 1) {
            customerDisplay(movieName, ticketPrice, seatCount, input);
        } else {
            System.out.println("Invalid entry");
            mgrDash(movieName, ticketPrice, seatCount, input);
        }
    }

    //asks user how many tickets they want to buy
    public static int customerTicketNumber (Scanner input) {
        System.out.println("How many tickets would you like to purchase?");
        int numTix = getInput(4, input);
        return numTix;
    }

    //calculates change
    public static double cashOut(int numTix, double ticketPrice, Scanner input) {
        double cashCharged = 0;
        double cashIn = 0;
        double cashOut = 0;
        cashCharged = numTix*ticketPrice;
        System.out.println("Your total is $" + cashCharged);
        System.out.println("Please insert your cash. (Type value)");
        cashIn = getInput(1.1, input);
        cashOut = cashIn-cashCharged;
        if(cashIn < cashCharged) {
            System.out.print("Not enough cash inserted. ");
            return cashOut(numTix, ticketPrice, input);
        }
        return cashOut;
    }

    //authenticates admin login
    public static Boolean adminLogin(String username, String password, Scanner input) {
        System.out.print("Please type your username: ");
        String enteredUsername = getInput("", input);
        System.out.print("Please type your password: ");
        String enteredPassword = getInput("", input);
        Boolean hasPasswordToken = false;
        System.out.println("");
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

    //authenticates admin pannel login
    public static Boolean adminPannelAccess(String username, String password, String movieName, double ticketPrice, int seatCount, Scanner input) {
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

    //get inputs of various types
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
