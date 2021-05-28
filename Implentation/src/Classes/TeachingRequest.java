package Classes;

import FormatIO.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represent the Teaching request
 */
public class TeachingRequest implements ClassFormatIO{
    private final String ID; // Unique Identification number
    private Date time; // Time for the lab
    private Float cost; // Cost of this Teaching request
    private String place; // Place
    private int ptt_num; // Number of PTT needed
    private ClassDir director; // Initiator of this teaching request
    private String training; // Training needed for this Teaching request
    private List<PTT> PTTs; // PTTs that are allocated to this request

    // Constructor
    public TeachingRequest(String ID){this.ID =ID; this.PTTs = new ArrayList<>(); director = new ClassDir();}
    public TeachingRequest(String ID, Date time, Float cost, String place, int ptt_num, ClassDir director,String training) {
        this.ID = ID;
        this.time = time;
        this.cost = cost;
        this.place = place;
        this.ptt_num = ptt_num;
        this.director = director;
        this.training = training;
        this.PTTs = new ArrayList<>(); // initialize the list of PTT
    }

    // add one Ptt to this Teaching request
    public void add_one_ptt(PTT ptt){
        if (ptt != null){
            this.PTTs.add(ptt);
        }
    }

    /**
     * Override method. Using FormatIO output stream print this Teaching request object
     * @see Classes.ClassFormatIO
     */
    @Override
    public void FormatIOPrint(FormatOutput output) {
        output.print("ID: " + this.ID + ", ");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.ENGLISH);
        output.print("Time: " + sdf.format(this.time) + ", ");
        output.print("Cost: " + String.valueOf(this.cost) + ", ");
        output.print("Place: " + this.place + ", ");
        output.print("PTT needed: " + String.valueOf(this.ptt_num) + ", ");
        output.print("Training: " + this.training + ", ");
        // using FormatIO print class director to String
        StringBuffer classDir = new StringBuffer();
        StringOut stringOut = new StringOut(classDir);
        this.director.FormatIOPrint(stringOut);
        output.print("ClassDir: (" + new String(classDir).replaceAll("\n","") + "), ");
        // Print PTT list
        output.print("PTTList: [" + PTTListToString() +"]\n");
    }

    /**
     * Override method.
     * Using FormatIO input stream read all the information
     * and store in this teaching request Object
     * @see Classes.ClassFormatIO
     */
    @Override
    public ClassFormatIO FormatIORead(FormatInput input) {
        try {
            String str_TeachingRequest = input.readLine();
            String[] inputArray = str_TeachingRequest.split(", ",6);

            // Convert time String to Data object
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.ENGLISH);
            this.time = sdf.parse(inputArray[0].split(": ")[1]);

            // read cost , place, ptt number, and training
            this.cost = Float.valueOf(inputArray[1].split(": ")[1]);
            this.place = inputArray[2].split(": ")[1];
            this.ptt_num = Integer.parseInt(inputArray[3].split(": ")[1]);
            this.training = inputArray[4].split(": ")[1];

            // process(read) Class Director
            Pattern r = Pattern.compile("ClassDir: \\([^\\)]*\\)");// regex
            Matcher m = r.matcher(inputArray[5]); //use regex to match class director object in the String
            if (m.find( )) {
                String str_dir = m.group(0).split(": ",2)[1];
                // use FormatIO read Class director object
                this.director.FormatIORead(new StringIn(str_dir.substring(1,str_dir.length()-1)));
            }else {
                System.out.println("Some error on the structure of Teaching Request data1");
                return null;
            }

            // process(read) Ptt list
            r = Pattern.compile("PTTList: \\[(.*)\\]"); // regex
            m = r.matcher(inputArray[5]); //use regex to match the list of PTT in the String
            if (m.find( )) {
                String str_ppts = m.group(0).split(": ",2)[1];
                if (str_ppts.length() <= 2){
                    this.PTTs = new ArrayList<>();
                }else {
                    String[] str_pptList = str_ppts.substring(2, str_ppts.length()-2).split("\\), \\("); // separate PTT form the String format list
                    for(int i=0; i<str_pptList.length; i++){
                        PTT ptt = new PTT();
                        ptt.FormatIORead(new StringIn(str_pptList[i])); // use FormatIO read PTT object
                        this.PTTs.add(ptt); // add PTT to the list
                    }
                }
            }else {
                System.out.println("Some error on the structure of Teaching Request data");
                return null;
            }
        } catch (EofX | ParseException eofX) {
            // Pass if meet the end of file
            if (!eofX.getMessage().equals("End of File")){
                eofX.printStackTrace();
            }
        }
        return this;
    }

    /**
     * This method convert the PTT list of this teaching request
     * to a String Format (PTTs Enclosed by parentheses and separated by comma and space)
     * @return : String format of the PTT list
     */
    private String PTTListToString(){
        StringBuffer re = new StringBuffer();
        StringOut out = new StringOut(re);
        for(int i=0 ; i<this.PTTs.size() ; i++){
            if(i == this.PTTs.size()-1) {
                // the last PTT in the list
                out.print("(");
                this.PTTs.get(i).FormatIOPrint(out); // Use FormatIO print PTT objject
                out.print(")");
            }
            else {
                // Enclosed by parentheses and separated by comma and space
                out.print("(");
                this.PTTs.get(i).FormatIOPrint(out);
                out.print("), ");
            }
        }
        return new String(re).replaceAll("\n","");
    }

    // Getter and Setter for each field
    public String getID() { return ID; }
    public Date getTime() { return time; }
    public void setTime(Date time) { this.time = time; }
    public Float getCost() { return cost; }
    public void setCost(Float cost) { this.cost = cost; }
    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }
    public int getPtt_num() { return ptt_num; }
    public void setPtt_num(int ptt_num) { this.ptt_num = ptt_num; }
    public ClassDir getDirector() { return director; }
    public void setDirector(ClassDir director) { this.director = director; }
    public String getTraining() { return training; }
    public void setTraining(String training) { this.training = training; }
    public List<PTT> getPTTs() { return PTTs; }
}
