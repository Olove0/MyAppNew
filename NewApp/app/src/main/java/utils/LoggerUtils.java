package utils;

import com.orhanobut.logger.Logger;

/**
 * 统一管理Util 在正式网不显示
 *
 * @author: ltx
 * @date: 2016-10-08 18:02
 */
public class LoggerUtils {
    private static boolean isRelease=false;
    public static void loggerErro(String message){
        if(isRelease){
            return;
        }
        Logger.e(message);
    }
    public static void loggerDebug(String message){
        if(isRelease){
            return;
        }
        Logger.d(message);
    }
    public static void loggerInfor(String message){
        if(isRelease){
            return;
        }
        Logger.i(message);
    }

}
