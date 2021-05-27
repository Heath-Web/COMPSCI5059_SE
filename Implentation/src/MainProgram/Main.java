package MainProgram;

import Classes.*;
import Factory.TeachingRequestFactory;
import FormatIO.*;
import Functions.*;

import java.util.*;

public class Main {
    // Global variables
    public static final String FILENAME = "data.txt"; // file path and name
    public static List<Staff> staffList = new ArrayList<>(); // all user
    public static List<TeachingRequest> teachingRequestList = new ArrayList<>(); // all Teaching request record
    public static Staff currentUser = null; // Current Login User


    public static void main(String args[]) throws EofX {
        System.out.println("Welcome to the System!");
        FileIO fileIO = new FileIO();
        fileIO.LoadData(); // Load all data

        StandardIn consoleIn = new StandardIn();
        StandardOut consoleOut = new StandardOut();

        Service.getInstance().Start(consoleIn,consoleOut);

        fileIO.StoreData(); // Store all information to file
    }

    /*private static void menu(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        String option = "0";
        switch (currentUser.getClass().getSimpleName()){
            case "ClassDir":
                consoleOut.print("You can 1. Produce a teaching request \n2. View all Teaching Request \n3. Log out \n4. Exist System (e.g. 1)\n");
                consoleOut.print("Please choose an action from above : ");
                option = consoleIn.readWord();
                switch (option){
                    case "1":
                        TRProducer.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
                        menu(consoleIn,consoleOut);
                        break;
                    case "2":
                        TRView.getInstance().ShowAll(consoleIn, consoleOut);
                        menu(consoleIn,consoleOut);
                        break;
                    case "3":
                        Logout.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
                        menu(consoleIn,consoleOut);
                        break;
                    case "4":
                        break;
                    default:
                        consoleOut.print("Invalid action!");
                        break;
                }
                break;
            case "Administrator":
                consoleOut.print("You can 1. Arrange a training \n2. Allocate PTT to Teaching request \n3. View All PTTs \n4. View All Teaching Request \n5. Log out \n6. Exist System (e.g. 1)\n");
                consoleOut.print("Please choose an action from above : ");
                option = consoleIn.readWord();
                switch (option){
                    case "1":
                        TrainingArrangement.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
                        menu(consoleIn,consoleOut);
                        break;
                    case "2":
                        PTTAllocation.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
                        menu(consoleIn,consoleOut);
                        break;
                    case "3":
                        PTTView.getInstance().ShowAll(consoleIn,consoleOut);
                        menu(consoleIn,consoleOut);
                        break;
                    case "4":
                        TRView.getInstance().ShowAll(consoleIn, consoleOut);
                        menu(consoleIn,consoleOut);
                        break;
                    case "5":
                        consoleOut.print("User " + currentUser.getID() + " Logout successfully! \n");
                        currentUser = null;
                        Authentication.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
                        // authentication(consoleIn,consoleOut);
                        menu(consoleIn,consoleOut);
                        break;
                    case "6":
                        break;
                    default:
                        consoleOut.print("Invalid action!");
                        break;
                }
                break;
            case "PTT":
                consoleOut.print("You can 1. Log out 2. Exist System (e.g. 1)\n");
                consoleOut.print("Please choose an action from above : ");
                option = consoleIn.readWord();
                switch (option){
                    case "1":
                        consoleOut.print("User " + currentUser.getID() + " Logout successfully! \n");
                        currentUser = null;
                        Authentication.getInstance().ExecuteOnFormatIO(consoleIn,consoleOut);
                        //authentication(consoleIn,consoleOut);
                        menu(consoleIn,consoleOut);
                        break;
                    case "2":
                        break;
                    default:
                        consoleOut.print("Invalid action!");
                        break;
                }
                break;
        }
    }*/
}

    /*private static void authentication(StandardIn consoleIn, StandardOut consoleOut) throws EofX {
        for(;;){
            consoleOut.print("Please enter your GUID here (e.g. adm001): ");
            String GUID = consoleIn.readWord();
            for (int i=0; i<staffList.size(); i++){
                if (staffList.get(i).getID().equals(GUID)){
                    currentUser = staffList.get(i);
                    consoleOut.print("Welcome! " + currentUser.getName() + " (" + currentUser.getID() + ") \n");
                    break;
                }
            }
            if (currentUser == null){
                consoleOut.print("Authentication failed. User doesn't exist \n");
            }else break;
        }
    }*/

    /*private static void produceTeachingRequest(StandardIn consoleIn, StandardOut consoleOut) {
        consoleOut.print("Please enter the following information in order. Time, Cost, Place, Number of PTT needed, Training (Careful with the format)\n");
        consoleOut.print("Separate information with comma. For Instance : 10:00 25/05/2021, 100, Learning Hub, 3, basic teaching skills \n");
        consoleOut.print(": ");
        try {
            String Simple_str_TeachingReqeust = consoleIn.readLine();
            String[] informations = Simple_str_TeachingReqeust.split(", ");

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.ENGLISH);
            TeachingRequest teachingRequest = ((ClassDir)currentUser).produce_one_request(sdf.parse(informations[0]),
                    Float.valueOf(informations[1]), informations[2], Integer.parseInt(informations[3]),informations[4]);
            teachingRequestList.add(teachingRequest);
            consoleOut.print("Teaching Request has been created : ");
            teachingRequest.FormatIOPrint(consoleOut);
        } catch (Exception e) {
            e.printStackTrace();
            consoleOut.print("Wrong information Format or Lack of information! \n" +
                    "Please make sure the format is correct and enter all information (Time, Cost, Place, Number of PTT needed, Training ) \n");
            produceTeachingRequest(consoleIn, consoleOut);
        }
    }*/

    /*private static void arrangeTraining(StandardIn consoleIn, StandardOut consoleOut) throws EofX {
        consoleOut.print("Please enter the training information (e.g. teaching skill training): ");
        String training = consoleIn.readLine();
        consoleOut.print("Please enter GUID of PTT (Separate ID with comma and space e.g. p001, p002, p005): \n");
        String[] str_PTTsID = consoleIn.readLine().split(", ");
        List<PTT> pttList = new ArrayList<>();
        for (String str_ptt : str_PTTsID) {
            boolean is_exist = false;
            for (Staff staff : staffList) {
                if (staff.getID().equals(str_ptt)) {
                    pttList.add((PTT) staff);
                    is_exist = true;
                    break;
                }
            }
            if (!is_exist)
                consoleOut.print("PTT " + str_ptt + " does not exist \n");
        }
        ((Administrator)currentUser).arrange_training(pttList,training);
        consoleOut.print("Arrange training " + training + " to ");
        for (PTT ptt : pttList){
            consoleOut.print(ptt.getID() + ", ");
            consoleOut.print(ptt.trainings.size());
        }
        consoleOut.print("\n");
    }*/

    /*private static void allocatePTT(StandardIn consoleIn, StandardOut consoleOut) throws EofX {
        consoleOut.print("Please enter the ID of Teaching request (e.g. tr001): ");
        String teachingRequest_ID = consoleIn.readLine();
        Integer aim_teachingRequest = null;
        for (TeachingRequest trs : teachingRequestList){
            if (trs.getID().equals(teachingRequest_ID)) {
                aim_teachingRequest = teachingRequestList.indexOf(trs);
                break;
            }
        }
        if ( aim_teachingRequest == null ){
            consoleOut.print("Teaching request "+ teachingRequest_ID +" doesn't exist \n");
            allocatePTT(consoleIn,consoleOut);
        }else {
            consoleOut.print("Please enter GUID of PTT who will be allocated to the teaching request \n(Separate ID with comma and space e.g. p002, p003, p005): \n");
            String[] str_PTTsID = consoleIn.readLine().split(", ");
            List<PTT> pttList = new ArrayList<>();
            for (String str_ptt : str_PTTsID) {
                boolean is_exist = false;
                for (Staff staff : staffList) {
                    if (staff.getID().equals(str_ptt)) {
                        pttList.add((PTT) staff);
                        is_exist = true;
                        break;
                    }
                }
                if (!is_exist)
                    consoleOut.print("PTT " + str_ptt + " does nor exist \n");
            }
            ((Administrator)currentUser).allocate(pttList,teachingRequestList.get(aim_teachingRequest));
            consoleOut.print("Allocate PTT ");
            for (PTT ptt : pttList)
                consoleOut.print(ptt.getID() + ", ");
            consoleOut.print(" to Teaching Request " + teachingRequestList.get(aim_teachingRequest).getID() + "\n");
        }
    }*/
