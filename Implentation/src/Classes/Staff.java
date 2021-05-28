package Classes;

/**
 * The Staff class is an abstract class
 * represent the current staff of the university
 * which is also considered as the user of this system
 */
public abstract class Staff implements ClassFormatIO {
    private String ID; // Staff ID number
    private String name; // Staff Name

    // Constructor
    public Staff(){}
    public Staff(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    // Getter and Setter
    public void setID(String ID){this.ID = ID;}
    public String getID(){return this.ID;}
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
}
