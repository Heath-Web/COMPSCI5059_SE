import FormatIO.*;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileIOTest {
    public static void main(String args[]) throws EofX {
        Main main = new Main();
        main.LoadData();

        main.staffList.get(0).setName("zhangsan");
        main.teachingRequestList.get(0).setPtt_num(100);

        main.StoreData();
    }


}
