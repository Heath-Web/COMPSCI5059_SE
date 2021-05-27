package Functions;

import FormatIO.*;
import MainProgram.Main;

public class Authentication {
    private static Authentication authentication = new Authentication();

    private Authentication(){}

    public static Authentication getInstance() { return authentication; }

    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX{
        for(;;){
            consoleOut.print("Please enter your GUID here (e.g. adm001): ");
            String GUID = consoleIn.readWord();
            for (int i=0; i< Main.staffList.size(); i++){
                if (Main.staffList.get(i).getID().equals(GUID)){
                    Main.currentUser = Main.staffList.get(i);
                    consoleOut.print("Welcome! " + Main.currentUser.getName() + " (" + Main.currentUser.getID() + ") \n");
                    break;
                }
            }
            if (Main.currentUser == null){
                consoleOut.print("Authentication failed. User doesn't exist \n");
            }else break;
        }
    }
}
