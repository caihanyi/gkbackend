package com.chy.gk.util;

import java.util.regex.Pattern;

public class PhoneNumCheckUtil {
    private final static String  PHONEPATTERN = "^1[3456789]\\d{9}$";
    public final static String  CODEKEY = "yiCece!";
    public static boolean checkPhoneNum(String phoneNum){
        return Pattern.matches(PHONEPATTERN, phoneNum);
    }
}
