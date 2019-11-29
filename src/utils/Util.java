package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Util {

    static void show(double[] inputArray){
        for (double input : inputArray) {
            double rounded = round(input);
            show(input + "\t" + rounded);
        }
    }

    static void show(String string){
        System.out.println(string);
    }

    static double round(double notRounded){
        return new BigDecimal(notRounded).setScale(3, RoundingMode.UP).doubleValue();
    }
}
