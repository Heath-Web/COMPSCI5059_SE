package Classes;

import Builder.TRBuilder;
import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;

/**
 * This Class represent the user Class Director and inherit Staff class
 * @see Classes.Staff
 */
public class ClassDir extends Staff{
    // Constructor
    public ClassDir(){super();}
    public ClassDir(String ID, String name) {
        super(ID, name);
    }

    /**
     * This method produce a teaching request
     * @param builder : Teaching request builder
     * @return : teaching request object
     */
    public TeachingRequest produce_one_request(TRBuilder builder){
        // add this class director to the teaching request builder
        builder.setClassDir(this);
        return builder.getResult();
    }

    /**
     * Override method. Using FormatIO output stream print this Class director object
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
     * and store in this classDir
     * @see Classes.ClassFormatIO
     */
    @Override
    public ClassFormatIO FormatIORead(FormatInput input) {
        try {
            String str_ClassDir = input.readLine();
            String[] inputArray = str_ClassDir.split(", ");
            this.setID(inputArray[0].split(": ")[1]);
            this.setName(inputArray[1].split(": ")[1]);
        } catch (EofX eofX) {
            eofX.printStackTrace();
        }
        return this;
    }
}
