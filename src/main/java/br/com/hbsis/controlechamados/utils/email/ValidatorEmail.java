package br.com.hbsis.controlechamados.utils.email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorEmail {
    public static boolean isValidEmail(String email) {
        boolean isEmailIdValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            isEmailIdValid = true;
        }

        return isEmailIdValid;
    }
}
