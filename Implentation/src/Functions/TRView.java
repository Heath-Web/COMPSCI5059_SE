package Functions;

import Classes.TeachingRequest;
import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import MainProgram.Main;

/**
 * View teaching request, Singleton
 * Show the information of all teaching request in the system
 */
public class TRView implements ClientFunction{
    private String simpleDescription = "View all Teaching Request";// simple description
    //Singleton
    private static TRView trView = new TRView();
    private TRView(){}
    public static TRView getInstance() { return trView; }

    // Show the information of  All teaching request
    private void ShowAll(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        for (TeachingRequest teachingRequest : Main.teachingRequestList)
            // for each teaching request in the list
            teachingRequest.FormatIOPrint(consoleOut); // print
    }

    @Override
    public String getSimpleDescription() {
        return this.simpleDescription;
    }

    // Execute teaching request view Process
    @Override
    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        this.ShowAll(consoleIn,consoleOut);
    }
}
