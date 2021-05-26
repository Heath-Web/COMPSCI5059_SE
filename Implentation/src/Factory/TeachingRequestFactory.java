package Factory;

import Classes.PTT;

import java.util.Date;
import java.util.List;

public class TeachingRequestFactory {
    private String place; // Place
    private int ptt_num; // Number of PTT needed

    public String makeID(){
        String ID = "ID";
        return ID;
    }
    public Date makeTime(Date time){
        return time;
    }
    public String makeCost(String cost){
        return cost;
    }
    public List<PTT> makePTTs(List<PTT> PTTs){
        return PTTs;
    }
    public String makeTraining(String training){
        return training;
    }
}
