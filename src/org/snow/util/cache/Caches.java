package org.snow.util.cache;


public class Caches {

	private Caches() {}
	
	public static void disposeAll() {
		ImageCache.getInstance().dispose();
		CursorCache.getInstance().dispose();
		FontCache.getInstance().dispose();
		ColorCache.getInstance().dispose();
	}
}
