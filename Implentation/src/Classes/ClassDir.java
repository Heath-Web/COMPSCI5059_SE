package Classes;

import Builder.TRBuilder;
import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;

import java.util.Date;

public class ClassDir extends Staff{
    // Constructor
    public ClassDir(){super();}
    public ClassDir(String ID, String name) {
        super(ID, name);
    }


    // Produce a teaching request
    public TeachingRequest produce_one_request(TRBuilder builder){
        builder.setClassDir(this);
        return builder.getResult();
    }

    @Override
    public void FormatIOPrint(FormatOutput output) {
        output.print("ID: " + this.getID() + ", ");
        output.print("Name: " + this.getName() + "\n");
    }

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
