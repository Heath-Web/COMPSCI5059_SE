package Functions;

import Classes.Administrator;
import Classes.PTT;
import Classes.Staff;
import FormatIO.*;
import MainProgram.Main;
import java.util.ArrayList;
import java.util.List;

/**
 * Arrange a training , Singleton
 * Arrange a training to at least one Part Time Teacher.
 * User need to input the training  which they want to arrange first
 * Then input the ID number of PTT.
 * If more than one, the ID should be separate by comma and space
 */
public class TrainingArrangement implements ClientFunction {
    private String simpleDescription = "Arrange a training";// function simple description

    //Singleton
    private static TrainingArrangement trainingArrangement = new TrainingArrangement();
    private TrainingArrangement(){}
    public static TrainingArrangement getInstance() { return trainingArrangement; }

    // Execute arrangement process
    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        consoleOut.print("Please enter the training information (e.g. teaching skill training): ");
        String training = consoleIn.readLine(); // Read training form console
        consoleOut.print("Please enter GUID of PTT (Separate ID with comma and space e.g. p001, p002, p005): \n");
        String[] str_PTTsID = consoleIn.readLine().split(", "); // Read the ID number of PTTs who are waiting for being allocated the training
        List<PTT> pttList = new ArrayList<>();
        // Check if the PTT exist in the system
        for (String str_ptt : str_PTTsID) {
            // for each PTT entered
            boolean is_exist = false; // flag, if the PTT exist in the system
            // search in the staff list
            for (Staff staff : Main.staffList) {
                if (staff.getID().equals(str_ptt)) { // if exist
                    pttList.add((PTT) staff);
                    is_exist = true;
                    break;
                }
            }
            if (!is_exist)
                // if not exist in the system, give feed back
                consoleOut.print("PTT " + str_ptt + " does not exist \n");
        }
        // Arrange a training to all PTTs entered by user
        ((Administrator)Main.currentUser).arrange_training(pttList,training);
        consoleOut.print("Arrange training " + training + " to ");
        for (PTT ptt : pttList){
            consoleOut.print(ptt.getID() + ", ");
            consoleOut.print(ptt.trainings.size());
        }
        consoleOut.print("\n");
    }

    @Override
    public String getSimpleDescription() {
        return this.simpleDescription;
    }
}
