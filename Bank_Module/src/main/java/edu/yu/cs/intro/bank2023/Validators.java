package edu.yu.cs.intro.ga;
public class Validators{

public static final String A_SEARCH = "search";
public static final String A_DIRECT = "direct";
public static final String A_REFERRAL = "referral";

/**
* @param acquisition
* @return If acquisition is null, return false. If acquisition is one of search, direct, 
or referral, return true. Otherwise, return false.
*/
public static boolean isValidAcquisition(String acquisition){

if (acquisition==null) {
        return false;
        }
if (acquisition.equals(A_SEARCH) || acquisition.equals(A_DIRECT) || acquisition.equals(A_REFERRAL)) {
    return true; 
} else {
    return false;
}

}


/**
* If the path is null, return false.
* See below for valid path contents and endings. If path doesn't have a valid ending or 
has disallowed characters, return false.
* @param path path to file. May contain letters, numbers, dashes, periods and slashes; 
anything else is invalid. Must end in one of: ".html", ".mp3", ".m4a", or ".mp4" (each 
letter of which can be uppercase OR lowercase).
* @return true if the path is valid, false if it is not.
*/
public static boolean isValidPath(String path){

        if (path == null){
            return false;
        }
        for (int i = 0; i < path.length(); i++) {
            char ch = path.charAt(i);
            if (!(Character.isLetterOrDigit(ch) || ch == '-' || ch == '.' || ch == '/')) {
            
                return false;
            }
        } 
        if(!path.toLowerCase().endsWith(".html") && !path.toLowerCase().endsWith(".mp3") && !path.toLowerCase().endsWith(".m4a") && !path.toLowerCase().endsWith(".mp4")) {
            return false;
        }
        return true;


    }
}