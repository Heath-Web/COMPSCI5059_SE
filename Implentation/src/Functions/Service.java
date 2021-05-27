package Functions;

import FormatIO.EofX;
import FormatIO.Format;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import MainProgram.Main;

import java.util.*;

public class Service {
    private HashMap<String, String[]> optionList = new HashMap<>();

    private final String OP_PTTALLOCATION = "Allocate PTT to Teaching request";
    private final String OP_TRPRODUCE = "Produce a teaching request";
    private final String OP_TRAININGARRANGEMENT = "Arrange a training";
    private final String OP_TRVIEW = "View all Teaching Request";
    private final String OP_PTTVIEW = "View All PTTs";
    private final String OP_LOGOUT = "Log out";
    private final String OP_EXISTSYS = "Exist System";

    private final String[] ClassDirOptions = {OP_TRPRODUCE, OP_TRVIEW, OP_LOGOUT,OP_EXISTSYS};
    private final String[] AdmOptions = {OP_TRAININGARRANGEMENT, OP_PTTALLOCATION, OP_PTTVIEW,OP_TRVIEW,OP_LOGOUT,OP_EXISTSYS};
    private final String[] PTTOptions = {OP_LOGOUT,OP_EXISTSYS};

    private static Service service = new Service();
    public static Service getInstance() { return service; }

    public Service() {
        this.optionList.put("Administrator", AdmOptions);
        this.optionList.put("ClassDir",ClassDirOptions);
        this.optionList.put("PTT",PTTOptions);
    }

    public void Start(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        if (Main.currentUser == null){
            Authentication.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
        }
        String[] currentUserOptions = this.optionList.get(Main.currentUser.getClass().getSimpleName());
        consoleOut.print("You can ");
        for (int i=0;i<currentUserOptions.length;i++) {
            consoleOut.print(i+1);
            consoleOut.print(". ");
            consoleOut.print(currentUserOptions[i] + "\n");
        }
        consoleOut.print("Please choose an action from above : ");
        int option = Integer.parseInt(consoleIn.readWord());
        switch (currentUserOptions[option-1]){
            case OP_TRPRODUCE:
                TRProducer.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
                Service.getInstance().Start(consoleIn,consoleOut);
                break;
            case OP_PTTALLOCATION:
                PTTAllocation.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
                Service.getInstance().Start(consoleIn,consoleOut);
                break;
            case OP_TRVIEW:
                TRView.getInstance().ShowAll(consoleIn, consoleOut);
                Service.getInstance().Start(consoleIn,consoleOut);
                break;
            case OP_TRAININGARRANGEMENT:
                TrainingArrangement.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
                Service.getInstance().Start(consoleIn,consoleOut);
                break;
            case OP_LOGOUT:
                Logout.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
                Service.getInstance().Start(consoleIn,consoleOut);
                break;
            case OP_EXISTSYS:
                break;
            default:
                consoleOut.print("Invalid action!");
                break;
        }
    }

    /*private class option {
        public int option_code;
        public String str_option;

        public option(int option_code, String str_option) {
            this.option_code = option_code;
            this.str_option = str_option;
        }
    }*/

}
