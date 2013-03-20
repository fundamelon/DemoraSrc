package main.graphics;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.GameBase;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import util.Util;

public class FontLoader {

	//Paths of the .fnt and .png font components, mapped to font name
	public static ArrayList<String> filenames = new ArrayList<String>();
	
	//Fonts mapped to font names
//	public static HashMap<String, Font> fonts = new HashMap<String, AngelCodeFont>();
	public static HashMap<String, Font> fonts = new HashMap<String, Font>();
	public static BiHashMap<String, Float, TrueTypeFont> ttfFonts = new BiHashMap<String, Float, TrueTypeFont>();
	
	public static void init() {
		loadFonts();
	}
	
	public static TrueTypeFont getFont(String name) {
		return ttfFonts.get(name, 18f);
	}
	
	public static TrueTypeFont getFont(String name, float size) {
		TrueTypeFont out = ttfFonts.get(name, size);
		if(out == null) {
			out = createTTF(name, size);
			ttfFonts.put(name, size, out);
		}
		return out;
	}
		

	public static void loadFonts() {
		try {
			filenames = Util.findFiles("lib/font/", "ttf");
		} catch(Exception e) {
			System.out.println("Failed to scan font directory");
		}
		int failiures = 0;
		for(int i = 0; i < filenames.size(); i++) {
			String path = filenames.get(i);
			String name = path.substring(path.lastIndexOf("\\")+1, path.lastIndexOf("."));
			File fontFile;
			try {
				fontFile = new File(GameBase.pathPrefix+path);
				fonts.put(name, java.awt.Font.createFont(
									java.awt.Font.TRUETYPE_FONT, 
									new BufferedInputStream(new FileInputStream(fontFile))));
				ttfFonts.put(name, 18f, createTTF(name, 18f));
			} catch(Exception e) {
				System.out.println("Failed to load font: "+path);
				ttfFonts.put(path, 10f, null);
				failiures++;
				e.printStackTrace();
			}
			GameBase.updateProgressBar("Loading fonts.", (i/(float)filenames.size()) * 0.1f + 0.4f);
		}
		if(failiures < 0) {
			System.out.println("Failed to load "+failiures+" font files.");
		}
	}
	
	public static TrueTypeFont createTTF(String name, float size) {
		return new TrueTypeFont(fonts.get(name).deriveFont(size), true);
	}
}

class BiHashMap<K1, K2, V> {

private final Map<K1, Map<K2, V>> mMap;

public BiHashMap() {
    mMap = new HashMap<K1, Map<K2, V>>();
}

/**
 * Associates the specified value with the specified keys in this map (optional operation). If the map previously
 * contained a mapping for the key, the old value is replaced by the specified value.
 * 
 * @param key1
 *            the first key
 * @param key2
 *            the second key
 * @param value
 *            the value to be set
 * @return the value previously associated with (key1,key2), or <code>null</code> if none
 * @see Map#put(Object, Object)
 */
public V put(K1 key1, K2 key2, V value) {
    Map<K2, V> map;
    if (mMap.containsKey(key1)) {
        map = mMap.get(key1);
    } else {
        map = new HashMap<K2, V>();
        mMap.put(key1, map);
    }

    return map.put(key2, value);
}

/**
 * Returns the value to which the specified key is mapped, or <code>null</code> if this map contains no mapping for
 * the key.
 * 
 * @param key1
 *            the first key whose associated value is to be returned
 * @param key2
 *            the second key whose associated value is to be returned
 * @return the value to which the specified key is mapped, or <code>null</code> if this map contains no mapping for
 *         the key
 * @see Map#get(Object)
 */
public V get(K1 key1, K2 key2) {
    if (mMap.containsKey(key1)) {
        return mMap.get(key1).get(key2);
    } else {
        return null;
    }
}

/**
 * Returns <code>true</code> if this map contains a mapping for the specified key
 * 
 * @param key1
 *            the first key whose presence in this map is to be tested
 * @param key2
 *            the second key whose presence in this map is to be tested
 * @return Returns true if this map contains a mapping for the specified key
 * @see Map#containsKey(Object)
 */
public boolean containsKeys(K1 key1, K2 key2) {
    return mMap.containsKey(key1) && mMap.get(key1).containsKey(key2);
}

public void clear() {
    mMap.clear();
}

}
