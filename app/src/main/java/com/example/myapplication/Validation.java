package com.example.myapplication;

import android.graphics.Color;
import android.util.Pair;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static HashMap<String, Pair<String,String>> users = new HashMap<>();


    public static Pair<String, Boolean> passwordValidation(String password){
        Pattern pattern = Pattern.compile("[A-Z]+");
        Pattern pattern1 = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(password);
        Matcher matcher1 = pattern1.matcher(password);
        if(password.equals("")) {
            return new Pair<>("the password filed is Empty", false);
        }
        else if(password.length() < 4) {
            return new Pair<> ("The password must be Up to 4 Characters", false);
        }
        else if(!matcher.find() || !matcher1.find()){
            return new Pair<> ("The password must mix of Uppercase and lower case and numbers",
                    false);
        }
        return new Pair<>("Done", true);
    }

    public static Pair<String, Boolean> usernameValidation(String username){
        if(username.equals("")){
            return new Pair<>("the user filed is Empty", false);
        }
        return new Pair<>("Done", true);
    }

    public static Pair<String, Boolean> conformPassword(String password1, String password2){
        if(!passwordValidation(password1).second)
            return passwordValidation(password1);
        else if(!password1.equals(password2))
            return new Pair<>("The password not match", false);
        return new Pair<>("Done", true);
    }

    public static Pair<String, Boolean> emailValidation(String email){
        if (!email.contains("@") || !email.contains(".com"))
            return new Pair<>("Email is in valid", false);
        return new Pair<>("Done", true);
    }

    public static void changeText(TextView txt, String message){
        txt.setText(message);
        txt.setTextColor(Color.RED);
    }

    public static boolean userExist(String username, String password){
        try {
            if(password.equals(users.get(username.toLowerCase()).first))
                return true;
            return false;
        }
        catch (Exception e){
            return false;
        }
    }

    public static void addUser(String username, String password, String email){
        users.put(username.toLowerCase(), new Pair<>(password, email));
    }

}
