package org.snow.util.cache;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.snow.util.Constants;
import org.snow.util.ImageUtils;

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

	/** Returns the image associated with the specified input file. If image is not found a new image is created, cached and then returned.
	 * 
	 * @param path the image file.
	 * @return the image associated with specified file. */
	public Image getImage( final String path ) {
		Image image = imageMap.get( path );
		if( image == null ) {
			image = ImageUtils.loadImageFromFile( path );
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

	/** Returns an image that can be associated to filesystem folders. */
	public Image getFolderIcon() {
		return getImage( Constants.FOLDER_ICON_IMAGE );
	}

	/** Returns an icon that can be associated to file with unknown extension. */
	public Image getUnknownIcon() {
		return getImage( Constants.UNKNOWN_ICON_IMAGE );
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
