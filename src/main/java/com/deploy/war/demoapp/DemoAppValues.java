package com.deploy.war.demoapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoAppValues {
    public static Integer CURRENT_PERSON_ID;
    public static boolean isLogGetIn(){
        return CURRENT_PERSON_ID == null;
    }

    private static final String EMAIL_REGEX_PATTERN = "^.+@.+\\.[a-zA-Z]{2,}$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
