package org.snow.util.cache;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;

/** A class implementing a singleton cache for images and icons. */
public class ImageCache {

	private static final ImageCache instance = new ImageCache();

	/** image cache */
	private final Map<String, Image> imageMap;

	/** icon cache */
	private final Map<Program, Image> iconMap;

	private ImageCache() {
		imageMap = new HashMap<String, Image>();
		iconMap = new HashMap<Program, Image>();
	}

	public static ImageCache getInstance() {
		return instance;
	}

	/** Returns a new Image object build from input path (searching input as file or resource). Image received with this method must be
	 * disposed after use from the caller.
	 * 
	 * @param path the image file.
	 * @return the image associated with specified file. */
	public static Image loadImageFromFile( final String path ) {
		if( new File( path ).exists() )
			return new Image( Display.getDefault(), path );

		return new Image( Display.getDefault(), ImageCache.class.getResourceAsStream( path ) );
	}

	/** Returns a new ImageData object build from input path (searching input as file or resource).
	 * 
	 * @param path the image file.
	 * @return the ImageData object associated with specified file. */
	public static ImageData loadImageDataFromFile( final String path ) {
		if( new File( path ).exists() )
			return new ImageData( path );

		return new ImageData( ImageCache.class.getResourceAsStream( path ) );
	}

	/** Returns the image associated with the specified input file. If image is not found a new image is created, cached and then returned.
	 * 
	 * @param path the image file.
	 * @return the image associated with specified file. */
	public Image getImage( final String path ) {
		Image image = imageMap.get( path );
		if( image == null ) {
			image = loadImageFromFile( path );
			imageMap.put( path, image );
		}

		return image;
	}

	/** Dispose the resource cached with the input filename. */
	public void removeImage( final String path ) {
		final Image image = imageMap.remove( path );
		if( image != null )
			image.dispose();
	}

	/** Returns icon associated with the program used to open the input filename. If icon is not found a new image icon is created, cached
	 * and then returned.
	 * 
	 * @param filename the image file.
	 * @return the image associated with specified program. */
	public Image getIcon( final String filename ) {
		final Program program = Program.findProgram( getFileExtension( filename ) );
		return getIcon( program );
	}

	/** Returns icon associated with the specified program. If icon is not found a new image icon is created, cached and then returned. */
	public Image getIcon( final Program program ) {
		if( program == null )
			return null;

		Image image = iconMap.get( program );
		if( image == null ) {
			image = new Image( Display.getDefault(), program.getImageData() );
			iconMap.put( program, image );
		}

		return image;
	}

	/** Dispose the resource cached with the input filename. */
	public void removeIcon( final String filename ) {
		removeIcon( Program.findProgram( getFileExtension( filename ) ) );
	}

	/** Dispose the resource cached with the input program. */
	public void removeIcon( final Program program ) {
		if( program == null )
			return;

		final Image image = iconMap.remove( program );
		if( image != null )
			image.dispose();
	}

	/** Disposes all cached resources. */
	public void dispose() {
		for( Image image : imageMap.values() )
			image.dispose();
		imageMap.clear();

		for( Image image : iconMap.values() )
			image.dispose();
		iconMap.clear();
	}

	/** Utility method to extract a filename extension. */
	private String getFileExtension( final String filename ) {
		final int dot = filename.lastIndexOf( '.' );
		if( dot == -1 )
			return "";

		return filename.substring( dot + 1 );
	}

}
