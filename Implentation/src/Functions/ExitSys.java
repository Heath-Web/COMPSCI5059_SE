package Functions;

import FormatIO.Console;
import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;

/**
 *  Exit System, Singleton
 *  Do Exit process
 *  Store all data into file before Exit
 */
public class ExitSys implements ClientFunction{
    private final String simpleDescription = "Exist System"; // define the simple description

    // Singleton
    private static ExitSys existSys = new ExitSys();
    private ExitSys(){}
    public static ExitSys getInstance() { return existSys; }

    // Execute Exit process
    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        FileIO fileIO = new FileIO(); // Create a FileIO
        fileIO.StoreData(); // Store all information to file
    }

    @Override
    public String getSimpleDescription() {
        return this.simpleDescription;
    }
}
