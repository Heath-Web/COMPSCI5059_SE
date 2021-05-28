package Classes;

import FormatIO.Format;
import FormatIO.FormatOutput;
import Functions.ClientFunction;

public abstract class Staff implements ClassFormatIO {
    private String ID; // Staff ID number
    private String name; // Staff name

    // Constructor
    public Staff(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    public Staff(){}

    // Getter and Setter
    public void setID(String ID){this.ID = ID;}
    public String getID(){return this.ID;}
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
}
