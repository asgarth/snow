package org.snow.util;

import java.util.ResourceBundle;


public class LocalizationManager {

	private static final String LANG_DIR = "lang";

	private static final LocalizationManager instance = new LocalizationManager();
	
	private final ResourceBundle bundle;


	private LocalizationManager() {
		bundle = ResourceBundle.getBundle( LANG_DIR + "/Locale" );
	}

	public static ResourceBundle getBundle() {
		return instance.bundle;
	}
	
}
