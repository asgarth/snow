package org.snow.util;

public class Constants {

	/** Utility class, prevent instantiation. */
	private Constants() { }

	/** common path */
	public static final String HOME = System.getProperty( "user.home" );

	public static final String DESKTOP = System.getProperty( "user.home" ) + "/Desktop";

	/** common resources */
	public static final String CURSOR_IMAGE_HAND = "./images/hand.png";
	public static final String FOLDER_ICON_IMAGE = "./images/folder.png";
	public static final String UNKNOWN_ICON_IMAGE = "./images/unknown.png";

}
