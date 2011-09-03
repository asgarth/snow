package org.snow.util.cache;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;


/** A class implementing a singleton fonts cache. */
public class FontCache {

	public static final String BOLD = "BOLD";
	public static final String ITALIC = "ITALIC";
	public static final String SMALL = "SMALL";
	public static final String SMALL_BOLD = "SMALL_BOLD";
	public static final String LARGE = "LARGE";
	public static final String LARGE_BOLD = "LARGE_BOLD";

	private static final FontCache instance = new FontCache();

	/** font map */
	private final Map<String, Font> fontMap;


	private FontCache() {
		fontMap = new HashMap<String, Font>();

		// add BOLD font
		FontData[] fontData = Display.getDefault().getSystemFont().getFontData();
		for( int i = 0; i < fontData.length; i++ )
			fontData[i].setStyle( SWT.BOLD );
		putFont( BOLD, fontData );

		// add ITALIC font
		fontData = Display.getDefault().getSystemFont().getFontData();
		for( int i = 0; i < fontData.length; i++ )
			fontData[i].setStyle( SWT.ITALIC );
		putFont( ITALIC, fontData );

		// add SMALL font
		fontData = Display.getDefault().getSystemFont().getFontData();
		for( int i = 0; i < fontData.length; i++ )
			fontData[i].height = 7;
		putFont( SMALL, fontData );
		
		// add SMALL_BOLD font
		fontData = Display.getDefault().getSystemFont().getFontData();
		for( int i = 0; i < fontData.length; i++ ) {
			fontData[i].setStyle( SWT.BOLD );
			fontData[i].height = 7;
		}
		putFont( SMALL_BOLD, fontData );
		
		// add LARGE font
		fontData = Display.getDefault().getSystemFont().getFontData();
		for( int i = 0; i < fontData.length; i++ )
			fontData[i].height = fontData[i].height + 3;
		putFont( LARGE, fontData );

		// add LARGE_BOLD font
		fontData = Display.getDefault().getSystemFont().getFontData();
		for( int i = 0; i < fontData.length; i++ ) {
			fontData[i].setStyle( SWT.BOLD );
			fontData[i].height = fontData[i].height + 3;
		}
		putFont( LARGE_BOLD, fontData );

	}

	/** Returns the cache instance. */
	public static FontCache getInstance() {
		return instance;
	}

	/** Returns the font associated with the specified input string.
	 * If the cache contains the specified font this is returned. Otherwise in order 
	 * to create a new font the <code>font</code> string must describe all parameter 
	 * required to create a new font if not found in cache.
	 * 
	 *  Font string format for new font:
	 *  	font_name font_height font_style
	 *  
	 *  Example:
	 *  	"Arial 10 BOLD"
	 *  	"Courier 11 NORMAL"
	 * 
	 * <p>Allowed string value for style: NORMAL, BOLD, ITALIC.
	 */
	public Font getFont( final String font ) {
		if( fontMap.containsKey( font ) )
			return fontMap.get( font );

		final Font f = newFont( font );
		fontMap.put( font, f );

		return f;
	}

	/** Put the specified font into cache. This method is useful to add custom font with a 
	 * specified name to the cache.
	 * 
	 * <p>Font stored with this method does not have restriction on cached name and can be 
	 * retrieves later using the {@link #getFont(String))} method.
	 * 
	 * @param name font cached associated name
	 * @param font the new font to store.
	 */
	public void putFont( final String name, final Font font ) {
		fontMap.put( name, font );
	}

	/** Put a font with specified {@link FontData} into cache. This method is useful to add custom font with a 
	 * specified name to the cache.
	 * 
	 * <p>Font stored with this method does not have restriction on cached name and can be 
	 * retrieves later using the {@link #getFont(String))} method.
	 * 
	 * @param name font cached associated name
	 * @param fontData the font data used to create the new font.
	 */
	public void putFont( final String name, final FontData[] fontData ) {
		putFont( name, new Font( Display.getDefault(), fontData ) );
	}

	/** Put the specified font into cache. This method is useful to add custom font with a 
	 * specified name to the cache.
	 * 
	 * <p>Font stored with this method does not have restriction on cached name and can be 
	 * retrieves later using the {@link #getFont(String)} method.
	 * 
	 * @param name font cached associated name
	 * @param font the new font
	 * @param height the font height
	 * @param style the font style (NORMAL, BOLD, ITALIC).
	 */
	public void putFont( final String name, final String font, final int height, final int style ) {
		putFont( name, newFont( name, height, style ) );
	}

	/** Dispose the resource cached with the specified key.
	 * 
	 * @param name the cached font name.
	 */
	public void removeFont( final String name ) {
		final Font font = fontMap.remove( name );
		if( font != null )
			font.dispose();
	}

	/** Disposes all cached resources. */
	public void dispose() {
		for( Font font : fontMap.values() )
			font.dispose();
		fontMap.clear();
	}

	/** Create a new font according to specified input string. Valid format string <b>"font_name 10 BOLD"</b>. */
	private static Font newFont( final String font ) {
		if( ! font.matches( "\\w+ [0-9]+ [NORMAL|BOLD|ITALIC]" ) )
			throw new IllegalArgumentException( "Invalid font string format (valid string should be in the following format: \"font_name 10 BOLD\"" );

		final String[] split = font.split( " " );
		final String name = split[0];
		final int height = Integer.parseInt( split[1] );
		final int style = split[2].equals( "NORMAL" ) ? SWT.NORMAL : split[2].equals( "BOLD" ) ? SWT.BOLD : SWT.ITALIC;

		return newFont( name, height, style );
	}

	/** Create a new font according to specified input parameters. */
	private static Font newFont( final String name, final int height, final int style ) {
		if( style != SWT.NORMAL && style != SWT.BOLD && style != SWT.ITALIC )
			throw new IllegalArgumentException( "Invalid font type specified. Allowed values: SWT.NORMAL, SWT.BOLD, SWT.ITALIC" );

		return new Font( Display.getDefault(), name, height, style );
	}

}
