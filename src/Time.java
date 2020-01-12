import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    public static String getTime(){
        //格式化时间，只显示日期，不显示时分秒
        SimpleDateFormat formmatter = new SimpleDateFormat("yyyy/MM/dd");
        String timeStr = formmatter.format(new Date());
        return timeStr;
    }
}
