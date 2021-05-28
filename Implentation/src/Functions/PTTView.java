package Functions;

import Classes.Staff;
import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import MainProgram.Main;

/**
 * PTT View function, Singleton
 * Show the information of all PTT in the system
 */
public class PTTView implements ClientFunction{
    private String simpleDescription = "View All PTTs";// function simple description

    //Singleton
    private static PTTView pttView = new PTTView();
    private PTTView(){}
    public static PTTView getInstance() { return pttView; }

    // Show the information of  All PTT
    public void ShowAll(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        for(Staff staff : Main.staffList){
            // for each staff in the staff list
            if (staff.getClass().getSimpleName().equals("PTT")){ // if staff is PTT
                staff.FormatIOPrint(consoleOut); // print all information
            }
        }
    }

    // Execute PTT view Process
    @Override
    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        this.ShowAll(consoleIn,consoleOut);
    }

    @Override
    public String getSimpleDescription() {
        return this.simpleDescription;
    }
}
