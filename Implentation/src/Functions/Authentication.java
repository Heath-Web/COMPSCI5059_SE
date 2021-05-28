package Functions;

import FormatIO.*;
import MainProgram.Main;

/**
 *  Authentication function, Singleton
 *  User need to enter their ID fist.
 *  This class will match it to the ID of each staff in the Staff list
 */
public class Authentication{
    // Singleton
    private static Authentication authentication = new Authentication();
    private Authentication(){} // Constructor
    public static Authentication getInstance() { return authentication; }

    // Execute Authentication process
    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX{
        for(;;){
            consoleOut.print("Please enter your GUID here (e.g. adm001): ");
            String GUID = consoleIn.readWord(); // read the input (ID number)
            for (int i=0; i< Main.staffList.size(); i++){
                // search in the Staff list
                if (Main.staffList.get(i).getID().equals(GUID)){ // if exist
                    Main.currentUser = Main.staffList.get(i); // get current user
                    consoleOut.print("Welcome! " + Main.currentUser.getName() + " (" + Main.currentUser.getID() + ") \n");
                    break;
                }
            }
            if (Main.currentUser == null){
                // user does not exist, do loop
                consoleOut.print("Authentication failed. User doesn't exist \n");
            }else break;
        }
    }
}
