package Functions;

import FormatIO.EofX;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;

public interface ClientFunction {
    public String getSimpleDiscription();
    public void ExecuteOnFormatIO(FormatInput consoleIn, FormatOutput consoleOut) throws EofX;
}
