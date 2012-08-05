package org.snow.util;

import java.io.File;
import java.io.InputStream;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.snow.util.cache.ImageCache;

/** {@link Image} related utility methods. */
public class ImageUtils {

	/** Utility class, prevent instantiation. */
	private ImageUtils() { }
	
	/** Returns a new Image object build from input path (searching input as file or resource). Image received with this method must be
	 * disposed after use from the caller.
	 * 
	 * @param path the image file.
	 * @return the image associated with specified file. */
	public static Image loadImageFromFile( final String path ) {
		return loadImageFromFile( Display.getDefault(), path );
	}
	
	/** Returns a new Image object build from input path (searching input as file or resource). Image received with this method must be
	 * disposed after use from the caller.
	 * 
	 * @param path the SWT {@link Display}.
	 * @param path the image file.
	 * @return the image associated with specified file. */
	public static Image loadImageFromFile( final Display display, final String path ) {
		if( new File( path ).exists() )
			return new Image( display, path );

		return new Image( display, getResourceAsStream( path ) );
	}

	/** Returns a new ImageData object build from input path (searching input as file or resource).
	 * 
	 * @param path the image file.
	 * @return the ImageData object associated with specified file. */
	public static ImageData loadImageDataFromFile( final String path ) {
		if( new File( path ).exists() )
			return new ImageData( path );

		return new ImageData( getResourceAsStream( path ) );
	}

	private static InputStream getResourceAsStream( final String resource ) {
		if( resource.startsWith( "." ) )
			return ImageCache.class.getResourceAsStream( resource.substring( 1 ) );

		return ImageCache.class.getResourceAsStream( resource );
	}
	
}
