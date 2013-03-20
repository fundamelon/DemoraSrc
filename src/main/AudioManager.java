package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.util.ResourceLoader;

import util.Util;

public class AudioManager {
	public static ArrayList<String> filenames;
	public static HashMap<String, Audio> sounds;
	public static HashMap<String, ArrayList<String>> soundGroups;
	public static ArrayList<Ambience> ambience;
	public static String[] footstep, explosion;
	
	//master levels
	public static float music_vol = 1f;
	public static float sfx_vol = 0.8f;
	
	
	public static void init() {
		try {
			filenames = new ArrayList<String>();
			sounds = new HashMap<String, Audio>();
			soundGroups = new HashMap<String, ArrayList<String>>();
			ambience = new ArrayList<Ambience>();
			
			loadSounds();			
			
			//Create groups that contain sound names, used for random sounds
			//TODO: Create separate function
		/*	soundGroups.put("footstep", new ArrayList<String>());
			soundGroups.get("footstep").add("footstep_grass01_01");
			soundGroups.get("footstep").add("footstep_grass01_02");
			soundGroups.get("footstep").add("footstep_grass01_03");
			soundGroups.get("footstep").add("footstep_grass01_04");
			soundGroups.get("footstep").add("footstep_grass01_05");
		*/	
			createGroup("footstep", "footstep_grass01_");
			createGroup("grassy_footstep", "footstep_grass02_");
			createGroup("birdchirp", "birdchirp");
			createGroup("hit_flesh_medium", "hit_flesh_medium_");
			createGroup("whoosh_medium", "whoosh_medium");
			createGroup("coin", "coin_");
			
			/*
			soundGroups.put("grassy_footstep", new ArrayList<String>());
			soundGroups.get("grassy_footstep").add("footstep_grass02_01");
			soundGroups.get("grassy_footstep").add("footstep_grass02_02");
			soundGroups.get("grassy_footstep").add("footstep_grass02_03");
			soundGroups.get("grassy_footstep").add("footstep_grass02_04");
			soundGroups.get("grassy_footstep").add("footstep_grass02_05");
			
			soundGroups.put("birdchirp", new ArrayList<String>());
			soundGroups.get("birdchirp").add("birdchirp01");
			soundGroups.get("birdchirp").add("birdchirp02");
			soundGroups.get("birdchirp").add("birdchirp03");
			soundGroups.get("birdchirp").add("birdchirp04");
			
			soundGroups.put("hit_flesh_medium", new ArrayList<String>());
			soundGroups.get("hit_flesh_medium").add("hit_flesh_medium_01");
			soundGroups.get("hit_flesh_medium").add("hit_flesh_medium_02");
			soundGroups.get("hit_flesh_medium").add("hit_flesh_medium_03");
			*/
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update() {
		for(Ambience a : ambience) {
			if(Math.random() < a.frequency * 0.01f) {
				playSound(a.name, 1f, a.gain, false, false);
			//	System.out.println("ambience: "+a.name);
			}
		}
	}
	
	public static Audio getSound(String name) {
		Audio sound = null;
		if(sounds.containsKey(name)) {
			sound = sounds.get(name);
		} else if(soundGroups.containsKey(name)) {
			ArrayList<String> group = soundGroups.get(name);
			sound = sounds.get(group.get((int)Math.floor(Math.random() * group.size())));
		} else {
			System.out.println("Invalid sound name");
			sound = null;
		}
		return sound;
	}
	
	public static void playSound(String name, float pitch, float gain, boolean loop, boolean overlap) {
		Audio sound = getSound(name);
		if(overlap) {
			sound.playAsSoundEffect(pitch, gain * sfx_vol, loop);
		} else if(!sound.isPlaying()) {
			sound.playAsSoundEffect(pitch, gain * sfx_vol, loop);
		}
	}
	
	public static void playSound(String name, float pitch, float gain, boolean loop) {
		playSound(name, 1f, gain, false, true);
	}
	
	public static void playSound(String name, float pitch, float gain) {
		playSound(name, pitch, gain, false, true);
	}
	
	public static void playSound(String name, float gain) {
		playSound(name, 1f, gain, false, true);
	}
	
	public static void playSound(String name) {
		playSound(name, 1f, 1f, false, true);
	} 

	public static void stopSound(String name) {
		Audio sound = getSound(name);
		if(sound != null)
			sound.stop();
	}
	
	public static void addAmbience(String name, float gain, float frequency) {
		for(Ambience a : ambience)
			if(a.name.equals(name)) return;
		
		ambience.add(new Ambience(name, gain, frequency));
	}

	public static void setSFXVol(float value) {
		sfx_vol = value;
	}
	
	//TODO: Load sound config file
	public static void loadSoundConfig() throws IOException {
		Path file = FileSystems.getDefault().getPath("lib/sound/config.txt");
		Charset charset = Charset.forName("US-ASCII");
		List<String> fileData = Files.readAllLines(file, charset);
		for(String s : fileData) {
			
		}
	}
	
	public static void loadSounds() {
		try {
			filenames = Util.findFiles("lib/sound/", "ogg");
		} catch(Exception e) {
			System.out.println("Failed to scan sound directory");
		}
		int failiures = 0;
		for(int i = 0; i < filenames.size(); i++) {
			String s = filenames.get(i);
			String name = s.substring(s.lastIndexOf("\\")+1, s.lastIndexOf("."));
			try {
				Audio sound = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(GameBase.pathPrefix+s));
				sounds.put(name, sound);
			} catch(Exception e) {
				System.out.println("Failed to load sound: "+name);
				sounds.put(name, null);
				failiures++;
			}
			GameBase.updateProgressBar("Loading sounds.", (i/(float)filenames.size()) * 0.3f + 0.5f);
		}
		if(failiures < 0) {
			System.out.println("Failed to load "+failiures+" sound files.");
		}
	}
	
	public static void createGroup(String name, String prefix) {
		soundGroups.put(name, new ArrayList<String>());
		for(String s : filenames) {
			s = s.substring(s.lastIndexOf("\\")+1, s.lastIndexOf("."));
			if(s.startsWith(prefix)) {
				soundGroups.get(name).add(s);
			}
		}
	}
	
	public static void createGroup(String name) {
		createGroup(name, name);
	}
}

class Ambience {
	public String name;
	public float gain, frequency;
	
	public Ambience(String name, float gain, float frequency) {
		this.name = name;
		this.gain = gain;
		this.frequency = frequency;
	}
}
