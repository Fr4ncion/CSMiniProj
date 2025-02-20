package com.example.miniproject_orifrommer;

import android.widget.EditText;

public class Utils {
    public static final String ERROR_EMPTY = "Error: Empty fields";
    public static String TEXT_HIDE = "Hide";
    public static final String TEXT_SHOW = "Show";
    public static String getEditTextValue(EditText input)
    {
        return input.getText().toString();
    }
    public static boolean foundEmptyField(String[] inputs)
    {
        for(String input : inputs)
        {
            if(input.isEmpty())
            {
                return true;
            }
        }
        return false;
    }
}


