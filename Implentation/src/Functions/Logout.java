package Functions;

import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import MainProgram.Main;

/**
 *  Log out function, Singleton
 *  logout and Jump to the authentication function
 */
public class Logout implements ClientFunction {
    private final String simpleDescription = "Log out"; // function simple description

    //Singleton
    private static Logout logout = new Logout();
    private Logout(){}
    public static Logout getInstance() { return logout; }

    // Execute Logout process
    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        consoleOut.print("User " + Main.currentUser.getID() + " Logout successfully! \n");
        Main.currentUser = null; // clear current user
        Authentication.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut); // start Authentication process
    }

    @Override
    public String getSimpleDescription() {
        return this.simpleDescription;
    }
}
