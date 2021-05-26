package Classes;

import FormatIO.Format;
import FormatIO.FormatInput;
import FormatIO.FormatOutput;

public interface ClassFormatIO {
    public void FormatIOPrint(FormatOutput output);
    public ClassFormatIO FormatIORead(FormatInput input);
}
