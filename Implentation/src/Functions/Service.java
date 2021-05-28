package Functions;

import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import MainProgram.Main;
import java.util.*;

/**
 * Service, Singleton
 * Define the functions for different type of Staff
 * Facade of all functions
 */
public class Service {
    // HashMap form Staff name (Administrator, class director, and PTT) to their corresponding functions
    private HashMap<String, ClientFunction[]> optionList = new HashMap<>();
    // all function instances for Class director
    private final ClientFunction[] ClassDirOptions = {TRProducer.getInstance(), TRView.getInstance(), Logout.getInstance(), ExitSys.getInstance()};
    // all functions instances for Administrator
    private final ClientFunction[] AdmOptions = {TrainingArrangement.getInstance(), PTTAllocation.getInstance(), PTTView.getInstance(),TRView.getInstance(),Logout.getInstance(), ExitSys.getInstance()};
    // all functions instances for PTT
    private final ClientFunction[] PTTOptions = {Logout.getInstance(), ExitSys.getInstance()};

    //Singleton
    private static Service service = new Service();
    public static Service getInstance() { return service; }
    public Service() {
        // initialize option list
        this.optionList.put("Administrator", AdmOptions);
        this.optionList.put("ClassDir",ClassDirOptions);
        this.optionList.put("PTT",PTTOptions);
    }

    // Start Service
    public void Start(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        // Check whether user has past authentication or not
        if (Main.currentUser == null){
            // if not, start authentication process
            Authentication.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
        }
        // get corresponding alternative functions list according to the type of current user
        ClientFunction[] currentUserOptions = this.optionList.get(Main.currentUser.getClass().getSimpleName());
        consoleOut.print("You can ");
        for (int i=0;i<currentUserOptions.length;i++) {
            consoleOut.print(i+1); // print sequence number
            consoleOut.print(". ");
            consoleOut.print(currentUserOptions[i].getSimpleDescription() + "\n"); // print options
        }
        consoleOut.print("Please choose an action from above : ");
        try {
            int option = Integer.parseInt(consoleIn.readWord()); // read option code that user chooses
            currentUserOptions[option-1].ExecuteOnFormatIO(consoleIn,consoleOut); // start corresponding function process
            Service.getInstance().Start(consoleIn,consoleOut); // restart service when client function finishes
        }catch (Exception e){
            // invalid option number
            consoleOut.print("Invalid action! \n");
            Service.getInstance().Start(consoleIn,consoleOut);
        }

    }


}
