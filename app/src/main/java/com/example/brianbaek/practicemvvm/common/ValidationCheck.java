package com.example.brianbaek.practicemvvm.common;

public class ValidationCheck{

    public ValidationCheck() {

    }

    public static boolean isSpaceInText(String text){

        if(text == null || text.equals("")){
            return false;
        }
        int checkSpace = text.indexOf(" "); // if text doesn't have space return -1;
        boolean isSpace = checkSpace == -1 ? false : true;

        return isSpace;
    }
}
