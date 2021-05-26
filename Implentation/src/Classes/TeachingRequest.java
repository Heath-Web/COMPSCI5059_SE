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

public class TeachingRequest implements ClassFormatIO{
    private final String ID; // Unique Identification number
    private Date time; // Time for the lab
    private Float cost; // Cost of this Teaching request
    private String place; // Place
    private int ptt_num; // Number of PTT needed
    private ClassDir director; // Initiator
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
        this.PTTs = new ArrayList<>();
    }

    // Add one Ptt to this Teaching request
    public void add_one_ptt(PTT ptt){
        if (ptt != null){
            this.PTTs.add(ptt);
        }
    }

    // Getter and Setter
    public String getID() {
        return ID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPtt_num() {
        return ptt_num;
    }

    public void setPtt_num(int ptt_num) {
        this.ptt_num = ptt_num;
    }

    public ClassDir getDirector() {
        return director;
    }

    public void setDirector(ClassDir director) {
        this.director = director;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public List<PTT> getPTTs() {
        return PTTs;
    }

    @Override
    public void FormatIOPrint(FormatOutput output) {
        output.print("ID: " + this.ID + ", ");
        output.print("Time: " + this.time.toString() + ", ");
        output.print("Cost: " + String.valueOf(this.cost) + ", ");
        output.print("Place: " + this.place + ", ");
        output.print("PTT needed: " + String.valueOf(this.ptt_num) + ", ");
        output.print("Training: " + this.training + ", ");
        StringBuffer classDir = new StringBuffer();
        StringOut stringOut = new StringOut(classDir);
        this.director.FormatIOPrint(stringOut);
        output.print("ClassDir: (" + new String(classDir).replaceAll("\n","") + "), ");
        output.print("PTTList: [" + PTTListToString() +"]\n");
    }

    @Override
    public ClassFormatIO FormatIORead(FormatInput input) {
        // doesn't include ID
        try {
            String str_TeachingRequest = input.readLine();
            String[] inputArray = str_TeachingRequest.split(", ",6);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            this.time = sdf.parse(inputArray[0].split(": ")[1]);
            this.cost = Float.valueOf(inputArray[1].split(": ")[1]);
            this.place = inputArray[2].split(": ")[1];
            this.ptt_num = Integer.parseInt(inputArray[3].split(": ")[1]);
            this.training = inputArray[4].split(": ")[1];
            // process Class Director
            Pattern r = Pattern.compile("ClassDir: \\([^\\)]*\\)");
            Matcher m = r.matcher(inputArray[5]);
            if (m.find( )) {
                String str_dir = m.group(0).split(": ",2)[1];
                this.director.FormatIORead(new StringIn(str_dir.substring(1,str_dir.length()-1)));
            }else {
                System.out.println("Some error on the structure of Teaching Request data1");
                return null;
            }
            // process Ptt list
            r = Pattern.compile("PTTList: \\[(.*)\\]");
            m = r.matcher(inputArray[5]);
            if (m.find( )) {
                String str_ppts = m.group(0).split(": ",2)[1];
                if (str_ppts.length() <= 2){
                    this.PTTs = new ArrayList<>();
                }else {
                    String[] str_pptList = str_ppts.substring(2, str_ppts.length()-2).split("\\), \\(");
                    for(int i=0; i<str_pptList.length; i++){
                        PTT ptt = new PTT();
                        ptt.FormatIORead(new StringIn(str_pptList[i]));
                        this.PTTs.add(ptt);
                    }

                }
            }else {
                System.out.println("Some error on the structure of Teaching Request data");
                return null;
            }
        } catch (EofX | ParseException eofX) {
            if (!eofX.getMessage().equals("End of File")){
                eofX.printStackTrace();
            }
        }
        return this;
    }

    private String PTTListToString(){
        StringBuffer re = new StringBuffer();
        StringOut out = new StringOut(re);
        for(int i=0 ; i<this.PTTs.size() ; i++){
            if(i == this.PTTs.size()-1) {
                out.print("(");
                this.PTTs.get(i).FormatIOPrint(out);
                out.print(")");
            }
            else {
                out.print("(");
                this.PTTs.get(i).FormatIOPrint(out);
                out.print("), ");
            }
        }
        return new String(re).replaceAll("\n","");
    }
}
