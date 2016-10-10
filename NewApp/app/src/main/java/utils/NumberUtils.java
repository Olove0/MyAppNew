package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Jayden on 2016/5/2.
 */
public class NumberUtils {

    public static float str2Float(String f) {
        try {
            return Float.parseFloat(f);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int str2Int(String i) {
        try {
            return Integer.parseInt(i);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    static DecimalFormat df = new DecimalFormat("#0.00");

    public static String getMoneyFormat(BigDecimal money) {
        return df.format(money);
    }

}
