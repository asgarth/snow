package org.snow.util;

import java.io.File;

/** Provide simplified platform information. */
public class Platform {
    public static final int UNSPECIFIED = -1;
    public static final int MAC = 0;
    public static final int LINUX = 1;
    public static final int WINDOWS = 2;

    private static final int osType;

    static {
        String osName = System.getProperty("os.name");
        
        if (osName.startsWith("Linux")) {
            osType = LINUX;
        }
        else if (osName.startsWith("Mac") || osName.startsWith("Darwin")) {
            osType = MAC;
        }
        else if (osName.startsWith("Windows")) {
            osType = WINDOWS;
        }
        else {
            osType = UNSPECIFIED;
        }
    }
    
    /** Utility class, prevent instantiation. */
    private Platform() { }
    
    public static int getOSType() {
        return osType;
    }
    
    public static boolean isMac() {
        return osType == MAC;
    }
    
    public static boolean isLinux() {
        return osType == LINUX;
    }
    
    public static final boolean isWindows() {
        return osType == WINDOWS;
    }
    
    public static String getUserHome() {
    	if (isWindows()) {
    		final String userProfile = System.getenv("USERPROFILE");
    		if (userProfile != null && ! userProfile.isEmpty())
    			return userProfile;

    		return System.getenv("HOMEDRIVE") + System.getenv("HOMEPATH");
    	}
    	else {
    		return System.getProperty("user.home");
    	}
    }
    
    public static String getPathSeparator() {
		return System.getProperty("file.separator");
	}
    
    public static String getUserApp(final String appName) {
    	if (isWindows()) {
    		final String addData = System.getenv("APPDATA");
    		if (addData != null && ! addData.isEmpty() && new File(addData).exists())
    			return addData + getPathSeparator() + appName;
    		
    		return getUserHome() + getPathSeparator() + appName;
    	}
    	else {
    		return getUserHome() + getPathSeparator() + "." + appName;
    	}
    }

}