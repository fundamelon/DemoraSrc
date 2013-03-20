package main;

import org.newdawn.slick.SlickException;

import main.gui.GUIManager;
import main.gui.Panel;

public class Event {
	public static final int GAME_QUIT = 0;
	public static final int GAME_START = 1;
	public static final int GAME_RESUME = 2;
	
	public static final int OPEN_MAINMENU = 10;
	public static final int OPEN_LOADMENU = 11;
	
	public static final int OPEN_OPTIONS_MAIN = 20;	
	public static final int OPEN_OPTIONS_CONTROLS = 21;	
	public static final int OPEN_OPTIONS_GRAPHICS = 22;	
	public static final int OPEN_OPTIONS_DEBUG = 23;	
	
	public static final int SETTINGS_APPLY = 100;
	public static final int SETTINGS_CANCEL = 101;
	
	public static final int SETTINGS_TOGGLE_VSYNC = 120;
	public static final int SETTINGS_TOGGLE_FULLSCREEN = 121;
	public static final int SETTINGS_SET_SFX_VOL = 122;
	public static final int SETTINGS_SET_GAMMA = 123;
	
	public static final int DEBUG_TOGGLE_MAPRENDERING = 200;
	public static final int DEBUG_TOGGLE_GRAPHICS = 201;
	public static final int DEBUG_TOGGLE_AI = 202;
	public static final int DEBUG_TOGGLE_PARTICLES = 203;
	public static final int DEBUG_TOGGLE_TEXT = 204;
	
	
	
	public static final int TRIGGER_KILL_PLAYER = 500;
	public static final int TRIGGER_TEST = 599;
	
	
	/**
	 * Fires an event with specified action ID and input value
	 * @param action
	 * @param value
	 */
	public static void fire(int action, Object value) {
		switch(action) {
		
			//Main game controls.
			case GAME_QUIT:
				GameBase.quit();
				break;
			case GAME_START:
				GameBase.viewMode = GameBase.VIEW_WORLD;
				GameBase.menuVisible = false;
				String mapname = (String)value;
				try {
					GameBase.loadMap(mapname);
				} catch (SlickException e) {
					System.out.println("Failed to load map: "+mapname);
					e.printStackTrace();
				}
				break;
			
			case GAME_RESUME: 
				GameBase.viewMode = GameBase.VIEW_WORLD;
				GameBase.menuVisible = false;
				break;
			
			//Menu controls.
			case OPEN_MAINMENU:
				GameBase.viewMode = GameBase.VIEW_MENU;
				break;
			case OPEN_LOADMENU:
				GUIManager.setMenuPanel(Panel.PRESET_LOADGAME);
				break;
			case OPEN_OPTIONS_MAIN:
				GUIManager.setMenuPanel(Panel.PRESET_OPTIONS);
				break;
			case OPEN_OPTIONS_CONTROLS:
				GUIManager.setMenuPanel(Panel.PRESET_CONTROLS);
				break;
			case OPEN_OPTIONS_GRAPHICS:
				GUIManager.setMenuPanel(Panel.PRESET_GRAPHICS);
				break;
			case OPEN_OPTIONS_DEBUG:
				GUIManager.setMenuPanel(Panel.PRESET_DEBUG);
				break;
				
			//Main options.
			case SETTINGS_APPLY:
				GUIManager.setMenuPanel(Panel.PRESET_MAIN);
				break;
			case SETTINGS_CANCEL:
				
				GUIManager.setMenuPanel(Panel.PRESET_MAIN);
				break;
				
			case SETTINGS_TOGGLE_FULLSCREEN:
				GameBase.toggleFullscreen();
				break;
			case SETTINGS_TOGGLE_VSYNC:
				GameBase.toggleVSync();
				break;
				
			case SETTINGS_SET_SFX_VOL:
				AudioManager.setSFXVol((Float)value);
				break;
				
			case SETTINGS_SET_GAMMA:
				GameBase.gamma = (Float)value;
				break;
				
			//Triggers
			case TRIGGER_TEST:
				System.out.println("Trigger activated");
				break;
				
			//Dev controls.
			case DEBUG_TOGGLE_MAPRENDERING:
				GameBase.mapRendering = !GameBase.mapRendering;
				break;
				
			case DEBUG_TOGGLE_GRAPHICS:
				GameBase.debug_graphics = !GameBase.debug_graphics;
				GraphicsManager.setDebugMode(GameBase.debug_graphics);
				break;
			case DEBUG_TOGGLE_AI:
				GameBase.debug_AI = !GameBase.debug_AI;
				AIManager.debug = GameBase.debug_AI;
				break;
			case DEBUG_TOGGLE_PARTICLES:
				GameBase.debug_particles = !GameBase.debug_particles;
				break;
			case DEBUG_TOGGLE_TEXT:
				GameBase.debug_text = !GameBase.debug_text;
				break;
				
			default:
				System.out.println("*** Invalid or unimplemented action! ***");
		}
	}
	
	/**
	 * Fires an event with specified action ID
	 * @param action
	 */
	public static void fire(int action) {
		fire(action, null);
	}
}
