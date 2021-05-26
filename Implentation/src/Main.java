import Classes.*;
import FormatIO.*;

import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws EofX {
        System.out.println("等待输入：");
        StandardIn in = new StandardIn();
        String str = in.readLine();
        System.out.println("等待输入：" + str);


        String input = readDataFromConsole("Welcome to the System!\n" +
                "Please choose your identity: 1. Administrator 2. Class director 3. PTT");
        System.out.println("Chose: " + input);
        switch (input){
            case "1":
                input = readDataFromConsole("Please enter your GUID: ");
                System.out.println("GUID: " + input);

                //

                break;
            case "2":
                break;
            case "3":
                break;
            default:
                break;
        }
    }
    private static final String FILENAME = "data.txt";
    // 全局变量
    public List<Staff> staffList = new ArrayList<>(); // all user
    public List<TeachingRequest> teachingRequestList = new ArrayList<>(); // all Teaching request record

    private static String readDataFromConsole(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void LoadData(){
        System.out.println("----- Loading all data from file " + FILENAME + " .....");
        FileIn fileIn = new FileIn(FILENAME);
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
                        staffList.add(administrator);
                        break;
                    case "PTT":
                        PTT ptt = new PTT();
                        ptt.FormatIORead(fileIn);
                        out.print("PTT ");
                        ptt.FormatIOPrint(out);
                        staffList.add(ptt);
                        break;
                    case "ClassDir":
                        ClassDir classDir = new ClassDir();
                        classDir.FormatIORead(fileIn);
                        out.print("ClassDir ");
                        classDir.FormatIOPrint(out);
                        staffList.add(classDir);
                        break;
                    case "TeachingRequest":
                        fileIn.readWord();
                        TeachingRequest teachingRequest = new TeachingRequest(fileIn.readWord().split(",")[0]);
                        teachingRequest.FormatIORead(fileIn);
                        out.print("----- Teaching Request ");
                        teachingRequest.FormatIOPrint(out);
                        teachingRequestList.add(teachingRequest);
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
        System.out.println("----- Storing all data to file " + FILENAME + " .....");
        FileOut fileOut = new FileOut(FILENAME);
        StandardOut out = new StandardOut();
        // write all staff data to the file
        for(int i=0; i<staffList.size();i++){
            //System.out.println("test!!!!" + staffList.get(i).getClass().getSimpleName());
            switch (staffList.get(i).getClass().getSimpleName()){
                case "Administrator":
                    fileOut.print("Administrator ");
                    ((Administrator)staffList.get(i)).FormatIOPrint(fileOut);
                    out.print("Administrator ");
                    staffList.get(i).FormatIOPrint(out);
                    break;
                case "PTT":
                    fileOut.print("PTT ");
                    ((PTT)staffList.get(i)).FormatIOPrint(fileOut);
                    out.print("PTT ");
                    staffList.get(i).FormatIOPrint(out);
                    break;
                case "ClassDir":
                    fileOut.print("ClassDir ");
                    ((ClassDir)staffList.get(i)).FormatIOPrint(fileOut);
                    out.print("ClassDir ");
                    staffList.get(i).FormatIOPrint(out);
                    break;
                default:
                    break;
            }
        }
        // write all teaching request data to the file
        for (int i=0; i<teachingRequestList.size(); i++){
            fileOut.print("TeachingRequest ");
            teachingRequestList.get(i).FormatIOPrint(fileOut);
            out.print("TeachingRequest ");
            teachingRequestList.get(i).FormatIOPrint(out);
        }
        fileOut.close();
        System.out.println("----- Storing Complete");
    }
}
