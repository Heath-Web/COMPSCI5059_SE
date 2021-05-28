package MainProgram;

import Classes.*;
import FormatIO.*;
import Functions.*;

import java.util.*;

/**
 * Main program
 * contains global variables
 */
public class Main {
    // Global variables
    public static final String FILENAME = "data.txt"; // file path and name
    public static List<Staff> staffList = new ArrayList<>(); // all user
    public static List<TeachingRequest> teachingRequestList = new ArrayList<>(); // all Teaching request record
    public static Staff currentUser = null; // Current Login User


    public static void main(String args[]) throws EofX {
        System.out.println("Welcome to the System!");
        FileIO fileIO = new FileIO();
        fileIO.LoadData(); // Load all data

        Console console = new Console(); // load console
        Service.getInstance().Start(console, console); // start service
    }
}
