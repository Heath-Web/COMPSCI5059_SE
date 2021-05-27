package Functions;

import Classes.ClassDir;
import Classes.TeachingRequest;
import FormatIO.*;
import MainProgram.Main;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TRProducer {
    private static TRProducer trProducer = new TRProducer();

    private TRProducer(){}

    public static TRProducer getInstance() { return trProducer; }

    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        consoleOut.print("Please enter the following information in order. Time, Cost, Place, Number of PTT needed, Training (Careful with the format)\n");
        consoleOut.print("Separate information with comma. For Instance : 10:00 25/05/2021, 100, Learning Hub, 3, basic teaching skills \n");
        consoleOut.print(": ");
        try {
            String Simple_str_TeachingReqeust = consoleIn.readLine();
            String[] informations = Simple_str_TeachingReqeust.split(", ");

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.ENGLISH);
            TeachingRequest teachingRequest = ((ClassDir)Main.currentUser).produce_one_request(sdf.parse(informations[0]),
                    Float.valueOf(informations[1]), informations[2], Integer.parseInt(informations[3]),informations[4]);
            Main.teachingRequestList.add(teachingRequest);
            consoleOut.print("Teaching Request has been created : ");
            teachingRequest.FormatIOPrint(consoleOut);
        } catch (Exception e) {
            e.printStackTrace();
            consoleOut.print("Wrong information Format or Lack of information! \n" +
                    "Please make sure the format is correct and enter all information (Time, Cost, Place, Number of PTT needed, Training ) \n");
            TRProducer.getInstance().ExecuteOnFormatIO(consoleIn, consoleOut);
        }
    }
}
