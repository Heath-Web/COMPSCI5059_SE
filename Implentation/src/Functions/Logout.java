package Functions;

import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import MainProgram.Main;

public class Logout {
    private static Logout pttAllocation = new Logout();

    private Logout(){}

    public static Logout getInstance() { return pttAllocation; }

    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        consoleOut.print("User " + Main.currentUser.getID() + " Logout successfully! \n");
        Main.currentUser = null;
        Authentication.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
    }
}
