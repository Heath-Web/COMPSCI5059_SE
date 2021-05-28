package Functions;

import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;

/**
 * This interface represent the client function.
 * (all the options or actions that the system user can choose)
 * Define two methods.
 */
public interface ClientFunction {

    /**
     * @return : the simple description of the function
     */
    public String getSimpleDescription();

    /**
     * Execute function on FormatIO
     * @param consoleIn : FormatIO input
     * @param consoleOut : FormatIO output
     * @throws EofX :　FormatIO Exception
     */
    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX;
}
