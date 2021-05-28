package Functions;

import Builder.TRBuilder;
import Classes.ClassDir;
import Classes.TeachingRequest;
import FormatIO.*;
import MainProgram.Main;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TRProducer implements ClientFunction {
    private String simpleDiscription = "Produce a teaching request";
    private static TRProducer trProducer = new TRProducer();

    private TRProducer(){}

    public static TRProducer getInstance() { return trProducer; }

    @Override
    public String getSimpleDiscription() {
        return this.simpleDiscription;
    }

    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        consoleOut.print("Please enter the following information in order. Time, Cost, Place, Number of PTT needed, Training (Careful with the format)\n"
            +"Separate information with comma. For Instance : 10:00 25/05/2021, 100, Learning Hub, 3, basic teaching skills \n"
            + ": ");
        try {
            String Simple_str_TeachingReqeust = consoleIn.readLine();
            String[] informations = Simple_str_TeachingReqeust.split(", ");
            TRBuilder builder = new TRBuilder();
            builder.setTime(informations[0]);
            builder.setCost(informations[1]);
            builder.setPlace(informations[2]);
            builder.setPTT_num(informations[3]);
            builder.setTraining(informations[4]);
            TeachingRequest teachingRequest = ((ClassDir)Main.currentUser).produce_one_request(builder);
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
