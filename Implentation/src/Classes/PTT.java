package Classes;

import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * This Class represent the user Part Time Teacher and inherit Staff class
 * @see Classes.Staff
 */
public class PTT extends Staff{
    public List<String> trainings = null; // A list of training PTT has attended

    // Constructor
    public PTT(){trainings = new ArrayList<String>();}
    public PTT(String ID, String name) {
        super(ID,name);
        trainings = new ArrayList<String>(); // initialize training list
    }

    // Add a training to this PTT
    public void add_a_training(String training){
        if (trainings == null){
            trainings = new ArrayList<String>();
        }else {
            this.trainings.add(training);
        }
    }

    // Override method. Using FormatIO output stream print this PTT object
    @Override
    public void FormatIOPrint(FormatOutput output) {
        output.print("ID: " + this.getID() + ", ");
        output.print("Name: " + this.getName() + ", ");
        output.print("Training: [" + TrainingsToString() + "]");
        output.print("\n");
    }

    // Override method. Using FormatIO input stream read all the information and store in this PTT object
    @Override
    public ClassFormatIO FormatIORead(FormatInput input) {
        try {
            String str_PTT = input.readLine();
            String[] inputArray = str_PTT.split(", ",3);
            this.setID(inputArray[0].split(": ")[1]);
            this.setName(inputArray[1].split(": ")[1]);
            String trainings = inputArray[2].split(": ")[1];
            String[] trainingList = trainings.substring(1,trainings.length()-1).split(", ");
            for( int i=0 ; i<trainingList.length ; i++ ){
                if (trainingList[i].length()>1)
                    this.add_a_training(trainingList[i]);
            }
        } catch (EofX eofX) {
            eofX.printStackTrace();
        }
        return this;
    }

    /**
     * This method convert the training list of this PTT
     * to a String Format (trainings separated by comma and space)
     * @return : String format of the training list
     */
    private String TrainingsToString(){
        String re = ""; // result (Target string)
        for(int i=0 ; i<this.trainings.size() ; i++){
            if(i == this.trainings.size()-1)
                // the last training
                re = re + trainings.get(i);
            else
                // separate by comma and space
                re = re + trainings.get(i) + ", ";
        }
        return re;
    }

}
