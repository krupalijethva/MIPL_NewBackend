package com.monarch.utils;

public class HashUtil {
	
	
	public static String hashString(String strToHash) {
        String generatedHash = BCrypt.hashpw(strToHash, BCrypt.gensalt(12));
        return generatedHash;
    }
    public static boolean checkpw(String originalPassword, String hashedPassword) {    
        boolean matched = BCrypt.checkpw(originalPassword, hashedPassword);
        return matched;
    }

}
