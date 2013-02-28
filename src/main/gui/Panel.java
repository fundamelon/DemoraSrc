package main.gui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import main.Event;
import main.GameBase;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Panel {
	public static final int PRESET_MAIN = 0;
<<<<<<< HEAD
	public static final int PRESET_INGAME = 1;
	public static final int PRESET_OPTIONS = 2;
	public static final int PRESET_CONTROLS = 3;
	public static final int PRESET_GRAPHICS = 4;
	public static final int PRESET_DEBUG = 5;
	private int currentID = -1;
	
	private ArrayList<GUIObject> items = new ArrayList<GUIObject>();
	
	public static Font TitleMain;
	public static Font Title01;
	public static Font Title02;
	public static Font Label01;
=======
	public static final int PRESET_OPTIONS = 1;
	private int preset;
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	
	@SuppressWarnings("deprecation")
	public static void init() {
		try {

			TitleMain = new TrueTypeFont(
						java.awt.Font.createFont(
								java.awt.Font.TRUETYPE_FONT, 
								new BufferedInputStream(new FileInputStream("lib/font/old-english-fonts/SAXON.ttf"))).deriveFont(60f), 
								true);
			
			Title01 = new TrueTypeFont(
						java.awt.Font.createFont(
								java.awt.Font.TRUETYPE_FONT, 
								new BufferedInputStream(new FileInputStream(GameBase.pathPrefix+"lib/font/RomanUncialModern/RomanUncialModern.ttf"))).deriveFont(40f), 
								true);

			Title02 = new TrueTypeFont(
						java.awt.Font.createFont(
								java.awt.Font.TRUETYPE_FONT, 
								new BufferedInputStream(new FileInputStream(GameBase.pathPrefix+"lib/font/old-english-fonts/JUNIMRG_.ttf"))).deriveFont(20f), 
								true);

			Label01 = new TrueTypeFont(
						java.awt.Font.createFont(
								java.awt.Font.TRUETYPE_FONT, 
								new BufferedInputStream(new FileInputStream(GameBase.pathPrefix+"lib/font/old-english-fonts/JUNIMRG_.ttf"))).deriveFont(20f), 
								true);
			
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
<<<<<<< HEAD
<<<<<<< HEAD
			createMainMenu();
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
						Event.GAME_START
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			items.add(	
				new Button(	
					items.size(), 
					"START GAME",
					GUIManager.screenMidX - 100, 
					GUIManager.screenMidY - 25, 
					200, 
					50,
					Event.GAME_START
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
				));
		
		items.add(
				new Button(
<<<<<<< HEAD
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
						Event.GAME_START
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
=======
					items.size(),
					"OPTIONS",
					GUIManager.screenMidX - 100,
					GUIManager.screenMidY + 50,
					200,
					50,
					Event.OPEN_OPTIONS
			));
			
			items.add(	
				new Button(	
					items.size(), 
					"QUIT",
					GUIManager.screenMidX - 100, 
					GUIManager.screenMidY + 125, 
					200, 
					50,
					Event.GAME_QUIT
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			));
		}
<<<<<<< HEAD
<<<<<<< HEAD
		
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
=======
		this.preset = preset;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		this.preset = preset;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
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

	public int getPreset() {
		return preset;
	}

	public int getPreset() {
		return preset;
	}
}
