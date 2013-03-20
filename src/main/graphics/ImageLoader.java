package main.graphics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import main.GameBase;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import util.Util;

public class ImageLoader {
	
	public static ArrayList<String> filenames = new ArrayList<String>();
	public static HashMap<String, Image> images = new HashMap<String, Image>();
	
	public static void init() {
		loadImages();
	}
	
	public static Image getByPath(String ref) {
		ref = ref.replace("/", "\\").trim().toLowerCase();
		
		Image out = images.get(ref);
		if(out == null) {
			System.out.println("ERROR: Image not found! ["+ref+"]");
			return null;
		} else return out.copy();
	}
	
	public static SpriteSheet getSheet(String ref, int w, int h) {
		try {
			return new SpriteSheet(getByPath(ref), w, h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static SpriteSheet getSheet(String ref) {
		try {
			return new SpriteSheet(getByPath(ref), getByPath(ref).getWidth(), getByPath(ref).getWidth());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static void loadImages() {
		try {
			filenames = Util.findFiles("lib/img/", "png");
			filenames.addAll(Util.findFiles("lib/img/", "tga"));
		} catch(Exception e) {
			System.out.println("Failed to scan sound directory");
		}
		int failiures = 0;
		for(int i = 0; i < filenames.size(); i++) {
			String path = filenames.get(i);
		//	String name = s.substring(s.lastIndexOf("\\")+1, s.lastIndexOf("."));
			try {
				images.put(new String(path).trim().toLowerCase(), new Image(path));
			//	System.out.println(images.get(path).toString());
			} catch(Exception e) {
				System.out.println("Failed to load sound: "+path);
				images.put(path, null);
				failiures++;
			}
			GameBase.updateProgressBar("Loading textures.", (i/(float)filenames.size()) * 0.4f);
		}
		if(failiures < 0) {
			System.out.println("Failed to load "+failiures+" sound files.");
		}
	}
}
