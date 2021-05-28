package Functions;

import Classes.Administrator;
import Classes.PTT;
import Classes.Staff;
import Classes.TeachingRequest;
import FormatIO.*;
import MainProgram.Main;

import java.util.ArrayList;
import java.util.List;

public class PTTAllocation implements ClientFunction{
    private String simpleDiscription = "Allocate PTT to Teaching request";

    private static PTTAllocation pttAllocation = new PTTAllocation();

    private PTTAllocation(){}

    public static PTTAllocation getInstance() { return pttAllocation; }

    @Override
    public String getSimpleDiscription() {
        return this.simpleDiscription;
    }

    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        consoleOut.print("Please enter the ID of Teaching request (e.g. tr001): ");
        String teachingRequest_ID = consoleIn.readLine();
        Integer aim_teachingRequest = null;
        for (TeachingRequest trs : Main.teachingRequestList){
            if (trs.getID().equals(teachingRequest_ID)) {
                aim_teachingRequest = Main.teachingRequestList.indexOf(trs);
                break;
            }
        }
        if ( aim_teachingRequest == null ){
            consoleOut.print("Teaching request "+ teachingRequest_ID +" doesn't exist \n");
            PTTAllocation.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
        }else {
            consoleOut.print("Please enter GUID of PTT who will be allocated to the teaching request \n(Separate ID with comma and space e.g. p002, p003, p005): \n");
            String[] str_PTTsID = consoleIn.readLine().split(", ");
            List<PTT> pttList = new ArrayList<>();
            for (String str_ptt : str_PTTsID) {
                boolean is_exist = false;
                for (Staff staff : Main.staffList) {
                    if (staff.getID().equals(str_ptt)) {
                        pttList.add((PTT) staff);
                        is_exist = true;
                        break;
                    }
                }
                if (!is_exist)
                    consoleOut.print("PTT " + str_ptt + " does nor exist \n");
            }
            ((Administrator)Main.currentUser).allocate(pttList,Main.teachingRequestList.get(aim_teachingRequest));
            consoleOut.print("Allocate PTT ");
            for (PTT ptt : pttList)
                consoleOut.print(ptt.getID() + ", ");
            consoleOut.print(" to Teaching Request " + Main.teachingRequestList.get(aim_teachingRequest).getID() + "\n");
        }
    }
}
