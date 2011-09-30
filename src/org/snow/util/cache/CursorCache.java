package org.snow.util.cache;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.snow.util.Constants;

/** A class implementing a singleton mouse cursors cache. */
public class CursorCache {

	private static final CursorCache instance = new CursorCache();

	/** mouse cursor */
	private final Map<String, Cursor> cursorMap;

	/** Private constructor -> prevent instantiation */
	private CursorCache() {
		cursorMap = new HashMap<String, Cursor>();
	}

	/** Returns the cache instance. */
	public static CursorCache getInstance() {
		return instance;
	}

	/** Returns the image associated with the specified input file. If cursor is not found a new cursor is created, cached and then returned.
	 * 
	 * @param path the image file used to build the cursor.
	 * @return the cursor required. */
	public Cursor getCursor( final String path ) {
		if( cursorMap.containsKey( path ) )
			return cursorMap.get( path );

		final ImageData data = ImageCache.loadImageDataFromFile( path );
		final Cursor cursor = new Cursor( Display.getDefault(), data, 0, 0 );
		cursorMap.put( path, cursor );

		return cursor;
	}

	/** Returns the a custom hand cursor.
	 * 
	 * @return a custom hand cursor. */
	public Cursor getHandCursor() {
		return getCursor( Constants.CURSOR_IMAGE_HAND );
	}

	/** Dispose the resource cached with the input filename.
	 * 
	 * @param path the file associated with the cursor to be disposed. */
	public void removeCursor( final String path ) {
		final Cursor cursor = cursorMap.remove( path );
		if( cursor != null )
			cursor.dispose();
	}

	/** Disposes all cached resources. */
	public void dispose() {
		for( Cursor cursor : cursorMap.values() )
			cursor.dispose();
		cursorMap.clear();
	}

}
