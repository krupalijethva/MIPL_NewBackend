package com.monarch.service;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class LicenseManager {
	
	
	 public interface CLibrary extends Library {
	        
	        public String generateLicenseKey(String registrationKey, String validityTime);
	        
	        public String getApplicationName(String regKey);
	 }

	 
	 public static String getApplicationName2(String registrationKey) {
	        try {
	            //CLibrary clib = (CLibrary) Native.loadLibrary("E:\\MonarchDEV\\1.LicenseManager\\LicenseManagerLib\\LicenseManagerLib\\bin\\x64\\Debug\\LicenseManagerLib.dll", CLibrary.class);
	            CLibrary clib = (CLibrary) Native.loadLibrary("D:\\mech software\\LicenseManagerLib.dll", CLibrary.class);
	            
	           // try {
	                //CLibrary clib = (CLibrary) Native.loadLibrary("E:\\MonarchDEV\\1.Commons\\1.LicenseManager\\LicenseManagerLib\\LicenseManagerLib\\bin\\x64\\Debug\\LicenseManagerLib.dll", CLibrary.class);
	                String appName = clib.getApplicationName(registrationKey);
	                return appName;
//	            } catch (Exception e) {
//	                System.out.println(e.getMessage());
//	            }
	            
	        } catch (Exception e) {
	            System.out.println("Error loading dll: " + e.getMessage());
	        }
	        
	        return "";
	        
	    }
	    
	    public static String generateLicenseKeyPerpetual(String registrationKey) {
	        
	        try {
	            //CLibrary clib = (CLibrary) Native.loadLibrary("E:\\MonarchDEV\\1.LicenseManager\\LicenseManagerLib\\LicenseManagerLib\\bin\\x64\\Debug\\LicenseManagerLib.dll", CLibrary.class);
	            CLibrary clib = (CLibrary) Native.loadLibrary("D:\\mech software\\LicenseManagerLib.dll", CLibrary.class);
	            //CLibrary clib = (CLibrary) Native.loadLibrary("E:\\MonarchDEV\\1.Commons\\1.LicenseManager\\LicenseManagerLib\\LicenseManagerLib\\bin\\x64\\Debug\\LicenseManagerLib.dll", CLibrary.class);
	            String timeStr = "-perpetual";
	            
	            String licenseKey = clib.generateLicenseKey(registrationKey, timeStr);
	            return licenseKey;
	            
	        } catch (Exception e) {
	            System.out.println("Error " + e.getMessage());
	        }
	        
	        return "";
	    }
	    
	    public static String generateLicenseKey2(String registrationKey, long timeInMillis) {
	        
	        try {
	            //CLibrary clib = (CLibrary) Native.loadLibrary("E:\\MonarchDEV\\1.LicenseManager\\LicenseManagerLib\\LicenseManagerLib\\bin\\x64\\Debug\\LicenseManagerLib.dll", CLibrary.class);
	            CLibrary clib = (CLibrary) Native.loadLibrary("D:\\\\mech software\\\\LicenseManagerLib.dll", CLibrary.class);
	            //CLibrary clib = (CLibrary) Native.loadLibrary("E:\\MonarchDEV\\1.Commons\\1.LicenseManager\\LicenseManagerLib\\LicenseManagerLib\\bin\\x64\\Debug\\LicenseManagerLib.dll", CLibrary.class);
	            long vbTime = javaToVbTime(timeInMillis);
	            String timeMilis = "-" + String.valueOf(vbTime);
	            
	            String licenseKey = clib.generateLicenseKey(registrationKey, timeMilis);
	            return licenseKey;
	            
	        } catch (Exception e) {
	            System.out.println("Error " + e.getMessage());
	        }
	        
	        return "";
	    }
	    
	    private static long javaToVbTime(long javaTime) {
	        long TICKS_AT_EPOCH = 621355968000000000L;
	        long tick = javaTime * 10000 + TICKS_AT_EPOCH;
	        return tick;
	    }
}
