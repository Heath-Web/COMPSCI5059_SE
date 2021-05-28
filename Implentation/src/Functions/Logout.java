package Functions;

import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import MainProgram.Main;

public class Logout implements ClientFunction {
    private String simpleDiscription = "Log out";

    private static Logout pttAllocation = new Logout();

    private Logout(){}

    public static Logout getInstance() { return pttAllocation; }

    @Override
    public String getSimpleDiscription() {
        return this.simpleDiscription;
    }

    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        consoleOut.print("User " + Main.currentUser.getID() + " Logout successfully! \n");
        Main.currentUser = null;
        Authentication.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
    }
}
