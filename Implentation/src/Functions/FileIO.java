package Functions;

import Classes.Administrator;
import Classes.ClassDir;
import Classes.PTT;
import Classes.TeachingRequest;
import FormatIO.EofX;
import FormatIO.FileIn;
import FormatIO.FileOut;
import FormatIO.StandardOut;
import MainProgram.Main;

/**
 * FileIO contains two methods LoadData() and StoreData()
 * LoadData() is called when the system start to load all permanent information into the system
 * and StoreData() when the program finishes to store all permanent information into file
 */
public class FileIO {
    public void LoadData(){
        System.out.println("----- Loading all data from file " + Main.FILENAME + " .....");
        FileIn fileIn = new FileIn(Main.FILENAME); // open file
        StandardOut out = new StandardOut();
        for(;;) {
            try {
                String type = fileIn.readWord(); // identify type of the data
                switch (type){
                    case "Administrator":
                        Administrator administrator = new Administrator();
                        // user FormatIO read administrator information and add it to the staff list
                        Main.staffList.add((Administrator)administrator.FormatIORead(fileIn));
                        out.print("Administrator ");
                        administrator.FormatIOPrint(out);
                        break;
                    case "PTT":
                        PTT ptt = new PTT();
                        // user FormatIO read PTT information and add it to the staff list
                        Main.staffList.add((PTT)ptt.FormatIORead(fileIn));
                        out.print("PTT ");
                        ptt.FormatIOPrint(out);
                        break;
                    case "ClassDir":
                        ClassDir classDir = new ClassDir();
                        // user FormatIO read Class director information and add it to the staff list
                        Main.staffList.add((ClassDir)classDir.FormatIORead(fileIn));
                        out.print("ClassDir ");
                        classDir.FormatIOPrint(out);
                        break;
                    case "TeachingRequest":
                        fileIn.readWord();
                        // user FormatIO read teaching request information
                        // read ID number first anc create a teaching request object
                        TeachingRequest teachingRequest = new TeachingRequest(fileIn.readWord().split(",",2)[0]);
                        // read other information and add it to the teaching request list
                        Main.teachingRequestList.add((TeachingRequest)teachingRequest.FormatIORead(fileIn));
                        out.print("Teaching Request ");
                        teachingRequest.FormatIOPrint(out);
                        break;
                    default:
                        out.print("Unknown object \n");
                        break;
                }
            } catch (EofX eofX) {
                // pass if meet the end of file
                if (!eofX.getMessage().equals("End of File"))
                    eofX.printStackTrace();
                else break;
            }
        }
        fileIn.close(); // close fileIO
        System.out.println("----- Loading Complete ");
    }

    public void StoreData(){
        System.out.println("----- Storing all data to file " + Main.FILENAME + " .....");
        FileOut fileOut = new FileOut(Main.FILENAME); // open file
        StandardOut out = new StandardOut();
        // write all staff information  to the file
        for(int i = 0; i< Main.staffList.size(); i++){
            switch (Main.staffList.get(i).getClass().getSimpleName()){
                case "Administrator":
                    fileOut.print("Administrator ");// add type title
                    // use FormatIO write Administrator object to the file
                    ((Administrator) Main.staffList.get(i)).FormatIOPrint(fileOut);
                    out.print("Administrator ");
                    Main.staffList.get(i).FormatIOPrint(out);
                    break;
                case "PTT":
                    fileOut.print("PTT ");// add type title
                    // use FormatIO write PTT object to the file
                    ((PTT) Main.staffList.get(i)).FormatIOPrint(fileOut);
                    out.print("PTT ");
                    Main.staffList.get(i).FormatIOPrint(out);
                    break;
                case "ClassDir":
                    fileOut.print("ClassDir ");// add type title
                    // use FormatIO write Class director object to the file
                    ((ClassDir) Main.staffList.get(i)).FormatIOPrint(fileOut);
                    out.print("ClassDir ");
                    Main.staffList.get(i).FormatIOPrint(out);
                    break;
                default:
                    break;
            }
        }
        // write all teaching request data to the file
        for (int i = 0; i< Main.teachingRequestList.size(); i++){
            fileOut.print("TeachingRequest "); // add type title
            // use FormatIO write each teaching request object
            Main.teachingRequestList.get(i).FormatIOPrint(fileOut);
            out.print("TeachingRequest ");
            Main.teachingRequestList.get(i).FormatIOPrint(out);
        }
        fileOut.close(); // close file
        System.out.println("----- Storing Complete");
    }
}
