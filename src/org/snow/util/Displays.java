package org.snow.util;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

/** Utility class with common method on display and screen resolution. */
public class Displays {

	/** Utility class, prevent instantiation. */
	private Displays() { }

	/** Check if required window resolution is allowed on current display, otherwise an allowed dimension is returned. */
	public static Point computeAllowedSize( final Display display, final int width, final int height ) {
		final Rectangle rect = display.getBounds();

		int allowedWidth = rect.width - 50;
		int allowedHeight = rect.height - 50;
		allowedWidth = width > allowedWidth ? allowedWidth : width;
		allowedHeight = height > allowedHeight ? allowedHeight : height;

		return new Point( allowedWidth, allowedHeight );
	}

	/** Return the relative size for a window taking the specified screen percentage. */
	public static Point computeRelativeSize( final Display display, final int hperc, final int vperc ) {
		if( hperc < 0 || hperc > 100 || vperc < 0 || vperc > 100 )
			throw new IllegalArgumentException( "Invalid percetage specified. Allowed value between 0 and 100" );

		final Rectangle rect = display.getBounds();
		int width = rect.width / 100 * hperc;
		int height = rect.height / 100 * vperc;

		return computeAllowedSize( display, width, height );
	}

	/** Compute the coordinate to open a shell of specified dimension in the middle of the screen. */
	public static Point getDisplayCenter( final Display display, final Rectangle shellRect ) {
		final Rectangle displayRect = display.getBounds();
		int x = ( displayRect.width - shellRect.width ) / 2;
		int y = ( displayRect.height - shellRect.height ) / 2;

		return new Point( x, y );
	}

	/** Build the BOLD version of default system font. */
	public static Font getSystemFontBold( final Display display ) {
		final FontData[] fontData = display.getSystemFont().getFontData();
		for( int i = 0; i < fontData.length; i++ ) {
			final FontData data = fontData[i];
			data.setStyle( SWT.BOLD );
		}

		return new Font( display, fontData );
	}

	/** Build the BOLD version of default system font. */
	public static Font getSystemFontItalic( final Display display ) {
		final FontData[] fontData = display.getSystemFont().getFontData();
		for( int i = 0; i < fontData.length; i++ ) {
			final FontData data = fontData[i];
			data.setStyle( SWT.ITALIC );
		}

		return new Font( display, fontData );
	}

	/** Copy the file at specified path into the clipboard. */
	public static void copyToClipboard( final Display display, final String path ) {
		if( !new File( path ).exists() )
			throw new IllegalArgumentException( "Input file path not found" );

		final Clipboard clipboard = new Clipboard( display );
		clipboard.setContents( new Object[] { path }, new Transfer[] { FileTransfer.getInstance() } );
		clipboard.dispose();
	}

}
