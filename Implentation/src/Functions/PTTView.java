package Functions;

import Classes.Staff;
import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import MainProgram.Main;

public class PTTView {
    private static PTTView pttView = new PTTView();

    private PTTView(){}

    public static PTTView getInstance() { return pttView; }

    public void ShowAll(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        for(Staff staff : Main.staffList){
            if (staff.getClass().getSimpleName().equals("PTT")){
                staff.FormatIOPrint(consoleOut);
            }
        }
    }
}
