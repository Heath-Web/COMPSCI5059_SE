package Functions;

import Classes.Administrator;
import Classes.PTT;
import Classes.Staff;
import FormatIO.*;
import MainProgram.Main;

import java.util.ArrayList;
import java.util.List;

public class TrainingArrangement {
    private static TrainingArrangement trainingArrangement = new TrainingArrangement();

    private TrainingArrangement(){}

    public static TrainingArrangement getInstance() { return trainingArrangement; }

    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        consoleOut.print("Please enter the training information (e.g. teaching skill training): ");
        String training = consoleIn.readLine();
        consoleOut.print("Please enter GUID of PTT (Separate ID with comma and space e.g. p001, p002, p005): \n");
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
                consoleOut.print("PTT " + str_ptt + " does not exist \n");
        }
        ((Administrator)Main.currentUser).arrange_training(pttList,training);
        consoleOut.print("Arrange training " + training + " to ");
        for (PTT ptt : pttList){
            consoleOut.print(ptt.getID() + ", ");
            consoleOut.print(ptt.trainings.size());
        }
        consoleOut.print("\n");
    }
}
