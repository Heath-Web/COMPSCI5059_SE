package Functions;

import Builder.TRBuilder;
import Classes.ClassDir;
import Classes.TeachingRequest;
import FormatIO.*;
import MainProgram.Main;

/**
 * Produce a Teaching request, Singleton
 * User need to input Time, Cost, Place, Number of PTT needed, Training first (separate by comma and space)
 * Teaching request ID will be automatic generate by TRbuilder based on timestamp
 */
public class TRProducer implements ClientFunction {
    private String simpleDescription = "Produce a teaching request"; // function simple description
    //Singleton
    private static TRProducer trProducer = new TRProducer();
    private TRProducer(){}
    public static TRProducer getInstance() { return trProducer; }

    // Execute Teaching request Producing process
    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX {
        consoleOut.print("Please enter the following information in order. Time, Cost, Place, Number of PTT needed, Training (Careful with the format)\n"
            +"Separate information with comma. For Instance : 10:00 25/05/2021, 100, Learning Hub, 3, basic teaching skills \n"
            + ": ");
        try {
            String Simple_str_TeachingReqeust = consoleIn.readLine(); // read all teaching request information (Time, Cost, Place, Number of PTT needed, Training)
            String[] informations = Simple_str_TeachingReqeust.split(", ");
            TRBuilder builder = new TRBuilder(); // create a teaching request builer
            builder.setTime(informations[0]);
            builder.setCost(informations[1]);
            builder.setPlace(informations[2]);
            builder.setPTT_num(informations[3]);
            builder.setTraining(informations[4]);
            // class director produce a request
            TeachingRequest teachingRequest = ((ClassDir)Main.currentUser).produce_one_request(builder);
            Main.teachingRequestList.add(teachingRequest); // add teaching request to the list
            consoleOut.print("Teaching Request has been created : ");
            teachingRequest.FormatIOPrint(consoleOut);
        } catch (Exception e) {
            // Wrong information Format or Lack of information
            e.printStackTrace();
            consoleOut.print("Wrong information Format or Lack of information! \n" +
                    "Please make sure the format is correct and enter all information (Time, Cost, Place, Number of PTT needed, Training ) \n");
            TRProducer.getInstance().ExecuteOnFormatIO(consoleIn, consoleOut);
        }
    }

    @Override
    public String getSimpleDescription() {
        return this.simpleDescription;
    }
}
