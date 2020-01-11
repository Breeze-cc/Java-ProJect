import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    public static String getTime(){
        SimpleDateFormat formmatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String timeStr = formmatter.format(new Date());
        return timeStr;
    }
}
