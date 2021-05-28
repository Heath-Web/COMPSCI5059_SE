package Functions;

import Classes.Administrator;
import Classes.PTT;
import Classes.Staff;
import Classes.TeachingRequest;
import FormatIO.*;
import MainProgram.Main;
import java.util.ArrayList;
import java.util.List;

/**
 *  PTT allocation function, Singleton
 *  Allocate at least one Part Time Teacher to a teaching request
 *  User need to input the id of the teaching request which they want to allocate PTT to first
 *  Then input the ID of PTT.
 *  If more than one, the ID should be separate by comma and space
 */
public class PTTAllocation implements ClientFunction{
    private String simpleDescription = "Allocate PTT to Teaching request"; // function simple description

    // Singleton
    private static PTTAllocation pttAllocation = new PTTAllocation();
    private PTTAllocation(){}
    public static PTTAllocation getInstance() { return pttAllocation; }

    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        consoleOut.print("Please enter the ID of Teaching request (e.g. tr001): ");
        String teachingRequest_ID = consoleIn.readLine(); // read teaching request ID
        Integer aim_teachingRequest = null; // index number of the target teaching request
        for (TeachingRequest trs : Main.teachingRequestList){
            // search in the teaching request list
            if (trs.getID().equals(teachingRequest_ID)) {
                aim_teachingRequest = Main.teachingRequestList.indexOf(trs); // if match
                break;
            }
        }
        if ( aim_teachingRequest == null ){
            // teaching request doesn't exist and restart PTT allocation process
            consoleOut.print("Teaching request "+ teachingRequest_ID +" doesn't exist \n");
            PTTAllocation.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
        }else {
            consoleOut.print("Please enter GUID of PTT who will be allocated to the teaching request \n(Separate ID with comma and space e.g. p002, p003, p005): \n");
            String[] str_PTTsID = consoleIn.readLine().split(", "); // read ID number of PPTs
            List<PTT> pttList = new ArrayList<>(); // the list of PTT that will be allocated to the teaching request
            // Check if the PTT exist in the system
            for (String str_ptt : str_PTTsID) {
                // for each PTT entered
                boolean is_exist = false; // flag, if the PTT exist in the system
                // search in the staff list
                for (Staff staff : Main.staffList) {
                    if (staff.getID().equals(str_ptt)) {
                        // if exist
                        pttList.add((PTT) staff); // add ptt to the list
                        is_exist = true;
                        break;
                    }
                }
                if (!is_exist)
                    // if not exist in the system, give feed back
                    consoleOut.print("PTT " + str_ptt + " does nor exist \n");
            }
            // Allocate all ptt in the list to the teaching request
            ((Administrator)Main.currentUser).allocate(pttList,Main.teachingRequestList.get(aim_teachingRequest));
            consoleOut.print("Allocate PTT ");
            for (PTT ptt : pttList)
                consoleOut.print(ptt.getID() + ", ");
            consoleOut.print(" to Teaching Request " + Main.teachingRequestList.get(aim_teachingRequest).getID() + "\n");
        }
    }

    @Override
    public String getSimpleDescription() {
        return this.simpleDescription;
    }
}
