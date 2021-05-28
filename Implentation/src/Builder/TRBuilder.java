package Builder;

import Classes.ClassDir;
import Classes.TeachingRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class is a Teaching Request Builder
 * ID number of teaching request is automatically generated
 * based on timestamp.
 */

public class TRBuilder{

    private TeachingRequest teachingRequest;

    // Constructor
    public TRBuilder() {
        this.reset();
    }

    // Reset builder
    public void reset(){
        teachingRequest = new TeachingRequest("tr" + String.valueOf(new Date().getTime()));
    }

    // acquire teaching request object (result) from builder
    public TeachingRequest getResult(){
        return this.teachingRequest;
    }

    public void setTime(String str_time) throws ParseException {
        // parse String format to Date object
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.ENGLISH);
        this.teachingRequest.setTime(sdf.parse(str_time));
    }
    public void setCost(String cost){
        this.teachingRequest.setCost(Float.valueOf(cost));
    }
    public void setPlace(String place){
        this.teachingRequest.setPlace(place);
    }
    public void setPTT_num(String ptt_num){
        this.teachingRequest.setPtt_num(Integer.parseInt(ptt_num));
    }
    public void setClassDir(ClassDir classDir){
        this.teachingRequest.setDirector(classDir);
    }
    public void setTraining(String training){
        this.teachingRequest.setTraining(training);
    }
}
