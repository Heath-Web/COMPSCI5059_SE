package Functions;

import Classes.TeachingRequest;
import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import MainProgram.Main;

public class TRView {
    private static TRView trView = new TRView();

    private TRView(){}

    public static TRView getInstance() { return trView; }

    public void ShowAll(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        for (TeachingRequest teachingRequest : Main.teachingRequestList)
            teachingRequest.FormatIOPrint(consoleOut);
    }
}
