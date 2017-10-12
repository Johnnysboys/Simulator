package Helpers;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Logger {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
    public static void Log(Types type, String message ){
        String time = sdf.format(new Timestamp(System.currentTimeMillis()));
        System.out.println("["+time+"]" + type.toString() + message);
    }
}
