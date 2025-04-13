package utilities;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    public static String getCurrentDate() {
        Date date = new Date();
        return date.toString().replace(":", "_").replace(" ", "_");
    }
    public static String getCurrentDateTime(String formatTime) {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(formatTime);
        return formatter.format(now);
    }
    public static int getSeconds(String timePlay) {
        int totalSeconds = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse("00:" + timePlay, formatter);
        System.out.println("Converted time: " + time); // Output: 15:30
        totalSeconds += time.getSecond();
        totalSeconds += (time.getMinute() * 60);
        return totalSeconds;
    }
    public static int getMonthNumber(String monthName) {
        Calendar calendar = Calendar.getInstance();
        Date date;
        try {
            date = new SimpleDateFormat("MMMM").parse(monthName);
            calendar.setTime(date);
        }
        catch (Exception e){
        }
        System.out.println("calendar: " + calendar.get(Calendar.MONTH) + 1);
        return calendar.get(Calendar.MONTH) + 1;
    }
    public static String getMonthNameInThreeChars(String date) {
        return date.replaceAll("\\d+","").substring(date.indexOf(",") + 1).trim();
    }
}
