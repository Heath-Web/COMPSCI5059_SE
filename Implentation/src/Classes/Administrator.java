package Classes;

import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import java.util.Iterator;
import java.util.List;

/**
 * This Class represent the user administrator and inherit Staff class
 * @see Classes.Staff
 */
public class Administrator extends Staff{

    // Constructor
    public Administrator(){super();}
    public Administrator(String ID, String name) {
        super(ID, name);
    }

    /**
     * This method allocate a training to several PTTs
     * @param PTTs: An Arraylist of PTTs
     * @param Training: training (a simple string)
     */
    public void arrange_training(List<PTT> PTTs, String Training){
        Iterator i = PTTs.iterator(); // get iterator of PTT list
        while (i.hasNext()){
            // Add training to each PTT object
            ((PTT)i.next()).add_a_training(Training);
        }
    }

    /**
     * This method allocate several PTT to a teaching request
     * @param PTTs: An Arraylist of PTTs
     * @param teachingRequest: Teaching request
     */
    public void allocate(List<PTT> PTTs, TeachingRequest teachingRequest){
        Iterator i = PTTs.iterator();// get iterator of PTT list
        while (i.hasNext()){
            // Add each PTT to the teaching request object
            teachingRequest.add_one_ptt((PTT)i.next());
        }
    }

    /**
     * Override method. Using FormatIO output stream print this administrator
     * @see Classes.ClassFormatIO
     */
    @Override
    public void FormatIOPrint(FormatOutput output) {
        output.print("ID: " + this.getID() + ", ");
        output.print("Name: " + this.getName() + "\n");
    }

    /**
     * Override method.
     * Using FormatIO input stream read all the information
     * and store in this administrator
     * @see Classes.ClassFormatIO
     */
    @Override
    public ClassFormatIO FormatIORead(FormatInput input) {
        try {
            String str_administrator = input.readLine();
            String[] inputArray = str_administrator.split(", ");
            this.setID(inputArray[0].split(": ")[1]);
            this.setName(inputArray[1].split(": ")[1]);
        } catch (EofX eofX) {
            eofX.printStackTrace();
        }
        return this;
    }
}
