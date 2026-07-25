package ru.stqa.common;

import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n){
        var r = new Random();
        var result = "";
        for (int i =0;i<n;i++){
            result = result+(char)('a'+r.nextInt(26/*33*/));
        }
        return result;
    }
}
