package main.gui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import main.Event;
import main.GameBase;
import main.graphics.FontLoader;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import util.Util;

public class Panel {
	public static final int PRESET_MAIN = 0;
	public static final int PRESET_INGAME = 1;
	public static final int PRESET_OPTIONS = 2;
	public static final int PRESET_CONTROLS = 3;
	public static final int PRESET_GRAPHICS = 4;
	public static final int PRESET_DEBUG = 5;
	public static final int PRESET_LOADGAME = 6;
	private int currentID = -1;
	
	private ArrayList<GUIObject> items = new ArrayList<GUIObject>();
	
	public static Font TitleMain;
	public static Font Title01;
	public static Font Title02;
	public static Font Label01;
	
	@SuppressWarnings("deprecation")
	public static void init() {
		try {

			TitleMain = FontLoader.getFont("SAXON", 60f);
			
			Title01 = FontLoader.getFont("RomanUncialModern", 40f);

			Title02 = FontLoader.getFont("JUNIMRG_", 20f);

			Label01 = FontLoader.getFont("JUNIMRG_", 20f);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Panel(int preset) {
		setPreset(preset);
	}
	
	public void setPreset(int preset) {
		if(preset == currentID) return;
		items.clear();
		switch(preset)
		{
		case PRESET_MAIN:
			createMainMenu();
			break;
		case PRESET_LOADGAME:
			createGameSelection();
			break;
		case PRESET_INGAME:
			createIngameMenu();
			break;
		case PRESET_OPTIONS:
			createOptionsBase();
			createOptionsMenu();
			break;
		case PRESET_CONTROLS:
			createOptionsBase();
			createControlsMenu();
			break;
		case PRESET_GRAPHICS:
			createOptionsBase();
			createGraphicsMenu();
			break;
		case PRESET_DEBUG:
			createOptionsBase();
			createDebugMenu();
			break;
		}
		this.currentID = preset;
	}
	
	public void update() {
		for(GUIObject e : items)
			e.update();
	}
	
	public ArrayList<GUIObject> getItems() {
		return items;
	}
	
	public GUIObject getItemByID(int id) {
		for(GUIObject e : items)
			if(e.getID() == id)
				return e;
		
		return null;
	}
	
	public GUIObject getItemByText(String text) {
		for(GUIObject e : items)
			if(e.getText().equals(text))
				return e;
		
		return null;
	}

	public int getPreset() {
		return currentID;
	}
	
	public void createMainMenu() {
		
		items.add(
				new Label(
						items.size(),
						"DEMORA",
						GUIManager.screenMidX,
						GUIManager.screenMidY - 100,
						Color.black,
						TitleMain,
						"center"
		));
		
		items.add(	
				new Button(	
						items.size(), 
						"Load Game",
						GUIManager.screenMidX - 100, 
						GUIManager.screenMidY - 25, 
						200, 
						50,
						Event.OPEN_LOADMENU
				));
		
		items.add(
				new Button(
						items.size(),
						"Options",
						GUIManager.screenMidX - 100,
						GUIManager.screenMidY + 50,
						200,
						50,
						Event.OPEN_OPTIONS_MAIN
		));
		
		items.add(	
				new Button(	
						items.size(), 
						"Quit",
						GUIManager.screenMidX - 100, 
						GUIManager.screenMidY + 125, 
						200, 
						50,
						Event.GAME_QUIT
		));
	}	
	
	public void createIngameMenu() {
		items.add(	
				new Button(	
						items.size(), 
						"Resume",
						GUIManager.screenMidX - 100, 
						GUIManager.screenMidY - 75, 
						200, 
						50,
						Event.GAME_RESUME
				));
		
		items.add(	
				new Button(	
						items.size(), 
						"Load Game",
						GUIManager.screenMidX - 100, 
						GUIManager.screenMidY, 
						200, 
						50,
						Event.OPEN_LOADMENU
				));
		
		items.add(
				new Button(
						items.size(),
						"Options",
						GUIManager.screenMidX - 100,
						GUIManager.screenMidY + 75,
						200,
						50,
						Event.OPEN_OPTIONS_MAIN
		));
		
		items.add(	
				new Button(	
						items.size(), 
						"Quit",
						GUIManager.screenMidX - 100, 
						GUIManager.screenMidY + 150, 
						200, 
						50,
						Event.GAME_QUIT
		));
	}
	
	public void createGameSelection() {
		ArrayList<String> maps = Util.findFiles("lib/map", "tmx");
		for(int i = 0; i < maps.size(); i++) {
			String s = maps.get(i);
			items.add(
					new Button(
							items.size(),
							s,
							GUIManager.screenMidX - 150,
							GUIManager.screenMidY - 100 + 40*i,
							300,
							30,
							Event.GAME_START,
							s
			));
		}

		items.add(
				new Button(
						items.size(),
						"Back",
						GUIManager.screenMidX + 175,
						GUIManager.screenMidY + 145,
						100,
						40,
						Event.SETTINGS_CANCEL
		));
	}
	
	public void createOptionsBase() {
		
		//-----------
		//Top tabs
		items.add(
				new Button(
						items.size(),
						"Main",
						GUIManager.screenMidX - 230,
						GUIManager.screenMidY - 200,
						150,
						30,
						Event.OPEN_OPTIONS_MAIN
		));
		
		items.add(
				new Button(
						items.size(),
						"Controls",
						GUIManager.screenMidX - 75,
						GUIManager.screenMidY - 200,
						150,
						30,
						Event.OPEN_OPTIONS_CONTROLS
		));

		items.add(
				new Button(
						items.size(),
						"Graphics",
						GUIManager.screenMidX + 80,
						GUIManager.screenMidY - 200,
						150,
						30,
						Event.OPEN_OPTIONS_GRAPHICS
		));
		
		if(main.GameBase.debug) { 
			items.add(
					new Button(
							items.size(),
							"Debug",
							GUIManager.screenMidX + 255,
							GUIManager.screenMidY - 200,
							110,
							30,
							Event.OPEN_OPTIONS_DEBUG
			));
		}
		
		//------------
		//Bottom buttons
		items.add(
				new Button(
						items.size(),
						"Ok",
						GUIManager.screenMidX + 65,
						GUIManager.screenMidY + 250,
						75,
						40,
						Event.SETTINGS_APPLY
		));
		
		items.add(
				new Button(
						items.size(),
						"Cancel",
						GUIManager.screenMidX + 145,
						GUIManager.screenMidY + 250,
						75,
						40,
						Event.SETTINGS_CANCEL
		));
	}
	
	public void createOptionsMenu() {
		((Button)getItemByText("Main")).lock();
		
		items.add(
				new Label(
						items.size(),
						"Options",
						GUIManager.screenMidX,
						GUIManager.screenMidY - 250,
						Color.black,
						Title01,
						"center"
		));
		
		items.add(
				new Label(
						items.size(),
						"SFX volume",
						GUIManager.screenMidX - 00, 
						GUIManager.screenMidY - 125, 
						Color.black,
						Title02,
						"left"
		));

		items.add(	
				new Slider(	
						items.size(), 
						"Audio level",
						GUIManager.screenMidX + 128, 
						GUIManager.screenMidY - 120, 
						128, 
						0f,
						1f,
						main.AudioManager.sfx_vol,
						Event.SETTINGS_SET_SFX_VOL
		));
		
	}
	
	public void createControlsMenu() {
		((Button)getItemByText("Controls")).lock();
		
		items.add(
				new Label(
						items.size(),
						"Controls",
						GUIManager.screenMidX,
						GUIManager.screenMidY - 250,
						Color.black,
						Title01,
						"center"
		));
	}
	
	public void createGraphicsMenu() {
		((Button)getItemByText("Graphics")).lock();
		
		items.add(
				new Label(
						items.size(),
						"Graphics",
						GUIManager.screenMidX,
						GUIManager.screenMidY - 250,
						Color.black,
						Title01,
						"center"
		));

		items.add(	
				new Button(	
						items.size(), 
						"VSync",
						GUIManager.screenMidX - 300, 
						GUIManager.screenMidY - 120, 
						120, 
						40,
						Event.SETTINGS_TOGGLE_VSYNC
		));
		
		items.add(	
				new Button(	
						items.size(), 
						"Fullscreen",
						GUIManager.screenMidX - 300, 
						GUIManager.screenMidY - 80, 
						120, 
						40,
						Event.SETTINGS_TOGGLE_FULLSCREEN
		));
		
		items.add(
				new Label(
						items.size(),
						"Brightness",
						GUIManager.screenMidX - 00, 
						GUIManager.screenMidY - 125, 
						Color.black,
						Title02,
						"left"
		));

		items.add(	
				new Slider(	
						items.size(), 
						"Brightness",
						GUIManager.screenMidX + 128, 
						GUIManager.screenMidY - 120, 
						128, 
						0f,
						1f,
						main.GameBase.gamma,
						Event.SETTINGS_SET_GAMMA
		));
	}
	
	public void createDebugMenu() {
		((Button)getItemByText("Debug")).lock();
		
		items.add(
				new Label(
						items.size(),
						"Debug",
						GUIManager.screenMidX,
						GUIManager.screenMidY - 250,
						Color.black,
						Title01,
						"center"
		));

		items.add(
				new Label(
						items.size(),
						"Switches",
						GUIManager.screenMidX - 300, 
						GUIManager.screenMidY - 125, 
						Color.black,
						Title02,
						"left"
		));

		items.add(	
				new Button(	
						items.size(), 
						"Render Map",
						GUIManager.screenMidX - 300, 
						GUIManager.screenMidY - 100, 
						120, 
						40,
						Event.DEBUG_TOGGLE_MAPRENDERING
		));
		
		items.add(	
				new Button(	
						items.size(), 
						"Graphics",
						GUIManager.screenMidX - 300, 
						GUIManager.screenMidY - 60, 
						120, 
						40,
						Event.DEBUG_TOGGLE_GRAPHICS
		));
		
		items.add(	
				new Button(	
						items.size(), 
						"AI",
						GUIManager.screenMidX - 300, 
						GUIManager.screenMidY - 20, 
						120, 
						40,
						Event.DEBUG_TOGGLE_AI
		));
		
		items.add(	
				new Button(	
						items.size(), 
						"Particles",
						GUIManager.screenMidX - 300, 
						GUIManager.screenMidY + 20, 
						120, 
						40,
						Event.DEBUG_TOGGLE_PARTICLES
		));
		
		items.add(	
				new Button(	
						items.size(), 
						"Labels",
						GUIManager.screenMidX - 100, 
						GUIManager.screenMidY - 100, 
						120, 
						40,
						Event.DEBUG_TOGGLE_TEXT
		));
	}
}
