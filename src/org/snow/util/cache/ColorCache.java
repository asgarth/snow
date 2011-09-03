package org.snow.util.cache;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;


/** A class implementing a singleton color cache. */
public class ColorCache {

	private static final ColorCache instance = new ColorCache();

	/** color cache */
	private final Map<RGB, Color> colorMap;


	private ColorCache() {
		colorMap = new HashMap<RGB, Color>();		
	}

	/** Returns a new instance. */
	public static ColorCache getInstance() {
		return instance;
	}

	/** Returns the color associated with the specified values. If color is not found a new color is created, cached and then returned.
	 * 
	 * @param r red value.
	 * @param g green value.
	 * @param b blue value.
	 * @return the color associated with specified values.
	 */
	public Color getColor( final int r, final int g, final int b ) {
		if( ! checkColorRange( r, g, b ) )
			throw new IllegalArgumentException( "invalid color range -> allowed value 0 - 255" );

		return getColor( new RGB( r, g, b ) );
	}

	/** Returns the color associated with the specified values. If color is not found a new color is created, cached and then returned.
	 * 
	 * @param rgb RGB values.
	 * @return the color associated with specified values.
	 */
	public Color getColor( final RGB rgb ) {
		Color color = colorMap.get( rgb );
		if( color == null ) {
			color = new Color( Display.getDefault(), rgb );

			colorMap.put( rgb, color );
		}

		return color;
	}

	/** Dispose the resource cached with the input RGB. */
	public void removeColor( final int r, final int g, final int b ) {
		if( ! checkColorRange( r, g, b ) )
			throw new IllegalArgumentException( "invalid color range -> allowed value 0 - 255" );

		removeColor( new RGB( r, g, b ) );
	}

	/** Dispose the resource cached with the input RGB. */
	public void removeColor( final RGB rgb ) {
		final Color color = colorMap.remove( rgb );
		if( color != null )
			color.dispose();
	}

	/** Disposes cached resources */
	public void dispose() {
		for( Color color: colorMap.values() )
			color.dispose();

		colorMap.clear();
	}

	/** Check if specified color component values are in valid range (0 - 255). */
	private boolean checkColorRange( final int r, final int g, final int b ) {
		return (r > 0 && r < 255) && (g > 0 && g < 255) && (b > 0 && b < 255);
	}

	/** Returns the color associated with the input RGB object with all color components shifted by the specified offset value.
	 * 
	 * @param rgb original RGB value.
	 * @param offset offset to be used to modify color RGB components.
	 * @return the new color generated.
	 */
	public static RGB getColorWithOffset( final RGB rgb, final int offset ) {
		if( offset < -255 || offset > 255 )
			throw new IllegalArgumentException( "Invalid offset specified (-255, 255 offset allowed)" );

		final int red = rgb.red + offset < 0 ? 0 : rgb.red + offset > 255 ? 255 : rgb.red + offset;
		final int green = rgb.green + offset < 0 ? 0 : rgb.green + offset > 255 ? 255 : rgb.green + offset;
		final int blue = rgb.blue + offset < 0 ? 0 : rgb.blue + offset > 255 ? 255 : rgb.blue + offset;

		return new RGB( red, green, blue );
	}

}
