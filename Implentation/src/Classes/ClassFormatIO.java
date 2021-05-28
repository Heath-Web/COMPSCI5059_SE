package Classes;

import FormatIO.FormatInput;
import FormatIO.FormatOutput;

/**
 * This interface defines two methods for each class
 * (class director, administrator, PTT, and teaching request )
 * that allows the class can read and write through FormatIO
 */
public interface ClassFormatIO {
    /**
     * considering FormatOutput as a parameter
     * Using FormatIO output stream print the object
     * @param output : FormatIO Output
     */
    public void FormatIOPrint(FormatOutput output);

    /**
     * Override method.
     * Using FormatIO input stream read all the information
     * and store in the object
     * @param input : FormatIO input
     * @return : the object
     */
    public ClassFormatIO FormatIORead(FormatInput input);
}
