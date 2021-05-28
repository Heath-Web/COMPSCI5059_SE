package Functions;

import Classes.TeachingRequest;
import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import MainProgram.Main;

public class TRView implements ClientFunction{
    private String simpleDiscription = "View all Teaching Request";
    private static TRView trView = new TRView();

    private TRView(){}

    public static TRView getInstance() { return trView; }

    private void ShowAll(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        for (TeachingRequest teachingRequest : Main.teachingRequestList)
            teachingRequest.FormatIOPrint(consoleOut);
    }

    @Override
    public String getSimpleDiscription() {
        return this.simpleDiscription;
    }

    @Override
    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        this.ShowAll(consoleIn,consoleOut);
    }
}
