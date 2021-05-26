package MainProgram;

import Classes.Administrator;
import Classes.ClassDir;
import Classes.PTT;
import Classes.TeachingRequest;
import FormatIO.EofX;
import FormatIO.FileIn;
import FormatIO.FileOut;
import FormatIO.StandardOut;

public class FileIO {

    public void LoadData(){
        System.out.println("----- Loading all data from file " + Main.FILENAME + " .....");
        FileIn fileIn = new FileIn(Main.FILENAME);
        StandardOut out = new StandardOut();
        for(;;) {
            try {
                String type = fileIn.readWord();
                switch (type){
                    case "Administrator":
                        Administrator administrator = new Administrator();
                        administrator.FormatIORead(fileIn);
                        out.print("Administrator ");
                        administrator.FormatIOPrint(out);
                        Main.staffList.add(administrator);
                        break;
                    case "PTT":
                        PTT ptt = new PTT();
                        ptt.FormatIORead(fileIn);
                        out.print("PTT ");
                        ptt.FormatIOPrint(out);
                        Main.staffList.add(ptt);
                        break;
                    case "ClassDir":
                        ClassDir classDir = new ClassDir();
                        classDir.FormatIORead(fileIn);
                        out.print("ClassDir ");
                        classDir.FormatIOPrint(out);
                        Main.staffList.add(classDir);
                        break;
                    case "TeachingRequest":
                        fileIn.readWord();
                        TeachingRequest teachingRequest = new TeachingRequest(fileIn.readWord().split(",")[0]);
                        teachingRequest.FormatIORead(fileIn);
                        out.print("Teaching Request ");
                        teachingRequest.FormatIOPrint(out);
                        Main.teachingRequestList.add(teachingRequest);
                        break;
                    default:
                        out.print("Unknown object \n");
                        break;
                }
            } catch (EofX eofX) {
                if (!eofX.getMessage().equals("End of File"))
                    eofX.printStackTrace();
                else break;
            }
        }
        fileIn.close();
        System.out.println("----- Loading Complete ");
    }

    public void StoreData(){
        System.out.println("----- Storing all data to file " + Main.FILENAME + " .....");
        FileOut fileOut = new FileOut(Main.FILENAME);
        StandardOut out = new StandardOut();
        // write all staff data to the file
        for(int i=0; i<Main.staffList.size();i++){
            //System.out.println("test!!!!" + staffList.get(i).getClass().getSimpleName());
            switch (Main.staffList.get(i).getClass().getSimpleName()){
                case "Administrator":
                    fileOut.print("Administrator ");
                    ((Administrator)Main.staffList.get(i)).FormatIOPrint(fileOut);
                    out.print("Administrator ");
                    Main.staffList.get(i).FormatIOPrint(out);
                    break;
                case "PTT":
                    fileOut.print("PTT ");
                    ((PTT)Main.staffList.get(i)).FormatIOPrint(fileOut);
                    out.print("PTT ");
                    Main.staffList.get(i).FormatIOPrint(out);
                    break;
                case "ClassDir":
                    fileOut.print("ClassDir ");
                    ((ClassDir)Main.staffList.get(i)).FormatIOPrint(fileOut);
                    out.print("ClassDir ");
                    Main.staffList.get(i).FormatIOPrint(out);
                    break;
                default:
                    break;
            }
        }
        // write all teaching request data to the file
        for (int i=0; i<Main.teachingRequestList.size(); i++){
            fileOut.print("TeachingRequest ");
            Main.teachingRequestList.get(i).FormatIOPrint(fileOut);
            out.print("TeachingRequest ");
            Main.teachingRequestList.get(i).FormatIOPrint(out);
        }
        fileOut.close();
        System.out.println("----- Storing Complete");
    }

}
