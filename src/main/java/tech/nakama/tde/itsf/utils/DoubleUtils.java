package tech.nakama.tde.itsf.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DoubleUtils {

    private static DecimalFormat configureAndGetDecimalFormat() {
        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);

        return decimalFormat;
    }

    public static double formatDouble(double value) {
        BigDecimal db = new BigDecimal(value);
        db = db.setScale(2, RoundingMode.HALF_EVEN);
        return db.doubleValue();
    }

    public static String displayDouble(double value) {
        return configureAndGetDecimalFormat().format(value);
    }
}
