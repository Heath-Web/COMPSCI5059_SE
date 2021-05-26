package Classes;

import FormatIO.EofX;
import FormatIO.Format;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import com.sun.xml.internal.bind.ValidationEventLocatorEx;

import java.util.Iterator;
import java.util.List;

public class Administrator extends Staff{

    // Constructor
    public Administrator(){super();}
    public Administrator(String ID, String name) {
        super(ID, name);
    }

    // Arrange a training to several PTTs
    public void arrange_training(List<PTT> PTTs, String Training){
        Iterator i = PTTs.iterator();
        while (i.hasNext()){
            // Add training to each PTT object
            ((PTT)i.next()).add_a_training(Training);
        }
    }
    // Allocate several PTT to a teaching request
    public void allocate(List<PTT> PTTs, TeachingRequest teachingRequest){
        Iterator i = PTTs.iterator();
        while (i.hasNext()){
            // Add each PTT to the teaching request object
            teachingRequest.add_one_ptt((PTT)i.next());
        }
    }

    @Override
    public void FormatIOPrint(FormatOutput output) {
        output.print("ID: " + this.getID() + ", ");
        output.print("Name: " + this.getName() + "\n");
    }

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
