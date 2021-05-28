package Functions;

import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import MainProgram.Main;

public class ExistSys implements ClientFunction{
    private String simpleDiscription = "Exist System";

    private static ExistSys existSys = new ExistSys();

    private ExistSys(){}

    public static ExistSys getInstance() { return existSys; }

    @Override
    public String getSimpleDiscription() {
        return this.simpleDiscription;
    }

    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        FileIO fileIO = new FileIO();
        fileIO.StoreData(); // Store all information to file
    }
}
