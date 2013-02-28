package main;

import main.entity.*;
<<<<<<< HEAD
<<<<<<< HEAD
import main.graphics.FontLoader;
import main.graphics.ImageLoader;
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
import main.gui.*;
import main.map.*;
import util.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.EXTFramebufferObject.*;

import org.newdawn.slick.*;
<<<<<<< HEAD
import org.newdawn.slick.geom.Rectangle;
import util.Lagmeter;
=======
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.openal.*;

import util.Lagmeter;

import Dario.*;
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
 
public class GameBase {
	

<<<<<<< HEAD
<<<<<<< HEAD
	private static GameMap currentMap;
	private static String mapPath = "lib/map/graphics_test.tmx";
	private static String bgPath = "lib/map/menubg_01.tmx";
=======
	private static Tilemap currentMap;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
	private static Tilemap currentMap;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

	/** Current game render mode.
	 * 0: menu,
	 * 1: world,
	 * 2: console
	 */
	public static int viewMode = 0;
	
	public static final int VIEW_MENU = 0;
	public static final int VIEW_WORLD = 1;
	public static final int VIEW_CONS = 2;
	
	public static boolean closeRequested = false;
	
	//Debug switches, toggle console reporting and/or display modes.
<<<<<<< HEAD
<<<<<<< HEAD
	public static boolean debug = true;
	public static boolean debug_text = false;
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public static boolean debug_keyboard = 	false;
	public static boolean debug_mouse = 	false;
	public static boolean debug_graphics = 	false;
	public static boolean debug_menu = 		false;
	public static boolean debug_tileUtil = 	false;
	public static boolean debug_animation = false;
	public static boolean debug_pathfinder= true;
	public static boolean debug_entities  = true;
<<<<<<< HEAD
<<<<<<< HEAD
	public static boolean debug_AI 		= 	false;
	public static boolean debug_particles = false;
	public static boolean debug_lwjgl 	= 	false;
	public static boolean debug_audio =		false;

	//debug option for easy map visibility toggle
	public static boolean mapRendering = true;
	
	public static boolean startFullscreen = false;
	
	public static String pathPrefix = System.getProperties().getProperty("user.dir")+"\\";
	public static String classpath = System.getProperty("java.class.path");
	
	public static String version = "0.1x";
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	
	public static Graphics g;
	
	private static int colorTextureID;
	private static int framebufferID;
	private static int depthRenderBufferID;
	
	public static long startTime;
	public static long gameTime = 0;
	
	/** time at last frame */
	static long lastFrame;
	
	/** frames per second */
	static int fps;
	/** last fps */
	static long lastFPS;
	/** current fps */
	static int thisFPS;
	
	
	static util.Console console;
	
	static boolean vsync;
	
	public static boolean menuVisible;
	public static boolean paused = true;
	
	private static boolean disableFBO = true;
	
<<<<<<< HEAD
<<<<<<< HEAD
	public static Font debugFont;
	
	private static Image loadbar_decor;
	
	public static PerformanceMonitor cpuMonitor = new PerformanceMonitor();
	public static float cpu;
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

	//debug option for easy map visibility toggle
	public static boolean mapRendering = true;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	
	public static void main(String[] args) {
		GameBase.start();
	}
	
	@SuppressWarnings("all")
	public static void start() {
<<<<<<< HEAD
<<<<<<< HEAD
		System.out.println("[Demora v. "+version+"]");
		System.out.println("--------------------");
		
		if(debug_lwjgl) 
			System.setProperty("org.lwjgl.util.Debug", "true");
		else 
			System.setProperty("org.lwjgl.util.Debug", "false");
		
		
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
		
=======
		System.out.println("//--------------------");
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		System.out.println("//--------------------");
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.destroy();
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
<<<<<<< HEAD
<<<<<<< HEAD
		
		Display.setTitle("Demora");
		
		System.out.println(
				System.getProperties().getProperty("os.name") + " (" + 
				System.getProperties().getProperty("os.arch") + ")"
		);
		System.out.println(
				"JRE " + System.getProperties().getProperty("java.version") + " (" + 
				System.getProperties().getProperty("java.vendor") + ") VM: " +
				System.getProperties().getProperty("java.vm.version") + " Classformat: " +
				System.getProperties().getProperty("java.class.version")
		);

		System.out.println(
				glGetString(GL_VENDOR) + " " + 
				glGetString(GL_RENDERER) + "\n" + 
				"OpenGL "+glGetString(GL_VERSION)
		);
		
		System.out.println("--------------------");
		System.out.println("Game directory: " + System.getProperties().getProperty("user.dir"));
=======

		
=======

		
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		System.out.println(glGetString(GL_VENDOR));
		System.out.println(glGetString(GL11.GL_RENDERER));
		System.out.println("OpenGL "+glGetString(GL_VERSION));
		System.out.println("//--------------------");
		
		g = new Graphics();
		
		//Temporary stub to load map
		loadMap(new Tilemap("lib/map/graphics_testbed.tmx"));
		System.out.println("Map loaded: "+getMap().getFilepath());
		AIManager.generateNodeMap(32, 256, 256);
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
		org.newdawn.slick.util.Log.setVerbose(true);
		
		
		g = new Graphics();
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
		g.setFont(new TrueTypeFont(new java.awt.Font("Helvetica", 0, 10), true));
		debugFont =  new TrueTypeFont(new java.awt.Font("Courier", 0, 10), true);
		
		g.setAntiAlias(false);
		
		initGL();
		preRender();
		
		g.setColor(Color.white);
		g.setDrawMode(Graphics.MODE_NORMAL);
		
		try{loadbar_decor = new Image("lib/img/gui/loadbar_decor.png");} catch (SlickException e) {}
		updateProgressBar("Initializing.", 0.0f);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ImageLoader.init();
		FontLoader.init();
		AudioManager.init();
		
		//Temporary stub to load map
		try {
			loadMap(bgPath);
			updateProgressBar("Loading map.", 0.85f);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		GraphicsManager.init();
		updateProgressBar("Loading.", 0.87f);
		
		ControlManager.init();
		updateProgressBar("Loading controls.", 0.88f);
		
		EntityManager.init();
		updateProgressBar("Loading entities.", 0.89f);
		
		AIManager.init();
		updateProgressBar("Loading AI.", 0.90f);
		
		GUIManager.init();
<<<<<<< HEAD
<<<<<<< HEAD
		updateProgressBar("Loading GUI.", 0.95f);
		
		updateProgressBar("Loaded.", 1f);
		
		try { Thread.sleep(500); } catch (InterruptedException e) {	e.printStackTrace(); }
		
		Lagmeter.update(); //Required on first run
		
		g.setColor(Color.black);

		
	//	testQT = new QuadTree();
	//	testQT.init();
		
		
		GraphicsManager.setDebugMode(debug_graphics);
		
		startTime = System.currentTimeMillis();
		
=======
		Lagmeter.update(); //Required on first run to fix bugs.
		
	//	testQT = new QuadTree();
	//	testQT.init();
		
		g.setFont(new TrueTypeFont(new java.awt.Font("Helvetica", 10, 10), true));
		g.setAntiAlias(false);
		initGL();
		
		GraphicsManager.setDebugMode(debug_graphics);
		
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		Lagmeter.update(); //Required on first run to fix bugs.
		
	//	testQT = new QuadTree();
	//	testQT.init();
		
		g.setFont(new TrueTypeFont(new java.awt.Font("Helvetica", 10, 10), true));
		g.setAntiAlias(false);
		initGL();
		
		GraphicsManager.setDebugMode(debug_graphics);
		
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested() && !closeRequested) {
<<<<<<< HEAD
<<<<<<< HEAD
			float delta = getDelta() * 1f;
			if(paused) delta = 0;

			Display.setTitle("Demora [FPS: " + thisFPS + "]");
			
		//	testQT.scanEntities(EntityManager.entityTable);
			gameTime += delta;
			preRender();
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			float delta = getDelta();
			if(paused) delta = 0;

			Display.setTitle("Demora (Testbed) FPS: " + thisFPS + "  Particles: "+GraphicsManager.getParticleCount());

		//	testQT.scanEntities(EntityManager.entityTable);
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			update(delta);
			render(g, delta);

			Display.update();
			g.clear();
			Display.sync(100); // cap fps
		}
		System.out.println("Closing game...");
		AL.destroy();
		Display.destroy();
	}
	
	public static void update(float delta) {
		ControlManager.update(delta);
		TransitionManager.updateAll();
<<<<<<< HEAD
<<<<<<< HEAD
		EntityManager.update();
		TimerSet.updateAll();
		AudioManager.update();
		EnvObject.updateAll();
		getMap().update();
=======
		TimerSet.updateAll();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		TimerSet.updateAll();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
		if(viewMode == VIEW_MENU) {
			GUIManager.update();
		} else if(viewMode == VIEW_WORLD) {
<<<<<<< HEAD
<<<<<<< HEAD
			
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			EntityManager.update();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		} 
		
		if(!paused) {
			Lagmeter.update();
		}
		
		updateFPS(); // update FPS Counter
	}
<<<<<<< HEAD
<<<<<<< HEAD
	
	public static void updateProgressBar(String text, float amt) {
		g.clear();
		g.setColor(Color.darkGray);
		float mw = getWidth()/2, mh = getHeight()/2;
		g.drawImage(loadbar_decor, mw - 128, mh-22);
	//	g.drawLine(mw - 105, mh - 0, mw - 105, mh - 20);
		g.setColor(Color.lightGray);

		g.setAntiAlias(true);
		g.drawString(text, mw - 100, mh - 20);
		g.setAntiAlias(false);
		
		g.fill(new Rectangle(mw - 100, mh - 5, amt*200, 10));
		
		Display.update();
	}
	
	public static void preRender() {
=======

	public static void render(Graphics g, float delta) {	
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======

	public static void render(Graphics g, float delta) {	
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		if(!disableFBO && GLContext.getCapabilities().GL_EXT_framebuffer_object) {
			glViewport(0, 0, Display.getWidth(), Display.getHeight());
			glBindTexture(GL_TEXTURE_2D, 0);
			glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, framebufferID);
		}
			
<<<<<<< HEAD
<<<<<<< HEAD
		glClearColor (0.0f, 0.0f, 0.0f, 1f);
		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public static void render(Graphics g, float delta) {	
		
		switch(viewMode) {
		case VIEW_MENU:
			g.setDrawMode(Graphics.MODE_NORMAL);
			GraphicsManager.renderMenuBackground(g);
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		glClearColor (0.0f, 0.0f, 0.0f, 0.1f);
		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		switch(viewMode) {
		case VIEW_MENU:
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			menuVisible = true;
			break;
			
		case VIEW_WORLD:
	        g.setDrawMode(Graphics.MODE_NORMAL);
			GraphicsManager.renderGame(g, delta);
<<<<<<< HEAD
<<<<<<< HEAD
			AIManager.render(g);
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			if(menuVisible) {
				GraphicsManager.renderMenuOverlay(g);
				paused = true;
			} else {
				paused = false;
			}
			break;
			
		case VIEW_CONS:
			break;
			
		}
		
		if(menuVisible) {
			GUIManager.render(g, delta);
		}
<<<<<<< HEAD
<<<<<<< HEAD

		Mouse.setGrabbed(!menuVisible);
=======
		
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		Lagmeter.render(g);
		
	//	testQT.draw();
		
		if(!disableFBO && GLContext.getCapabilities().GL_EXT_framebuffer_object) {
			glEnable(GL_TEXTURE_2D);
			glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
			glClearColor (0.0f, 1.0f, 0.0f, 0.5f);
			glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glBindTexture(GL_TEXTURE_2D, colorTextureID); 
			
			glDisable(GL_TEXTURE_2D);
			glFlush();
		}
<<<<<<< HEAD
<<<<<<< HEAD
=======
		
		
		
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		
		
		
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	public static void setDisplayMode(int width, int height, boolean fullscreen) {

	    // return if requested DisplayMode is already set
	    if ((Display.getDisplayMode().getWidth() == width) && 
	        (Display.getDisplayMode().getHeight() == height) && 
		(Display.isFullscreen() == fullscreen)) {
		    return;
	    }

	    try {
	        DisplayMode targetDisplayMode = null;
			
		if (fullscreen) {
		    DisplayMode[] modes = Display.getAvailableDisplayModes();
		    int freq = 0;
					
		    for (int i=0;i<modes.length;i++) {
		        DisplayMode current = modes[i];
						
			if ((current.getWidth() == width) && (current.getHeight() == height)) {
			    if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
			        if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
				    targetDisplayMode = current;
				    freq = targetDisplayMode.getFrequency();
	                        }
	                    }

			    // if we've found a match for bpp and frequence against the 
			    // original display mode then it's probably best to go for this one
			    // since it's most likely compatible with the monitor
			    if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
	                        (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
	                            targetDisplayMode = current;
	                            break;
	                    }
	                }
	            }
	        } else {
	            targetDisplayMode = new DisplayMode(width,height);
	        }

	        if (targetDisplayMode == null) {
	            System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
	            return;
	        }

	        Display.setDisplayMode(targetDisplayMode);
	        Display.setFullscreen(fullscreen);
	        
				
	    } catch (LWJGLException e) {
	        System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
	    }
	}
	
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public static float getDelta() {
	    long time = System.nanoTime();
	    long delta = (time - lastFrame);
	    lastFrame = time;
	    return (delta/1000000f);
	}
	
	public static float getCpuUsage() {
		return cpu;
	}

	/**
	 * Open a simple console
	 */
	public static void openConsole() {
		if(console == null) {
			try {
				util.Console console = new util.Console();
			}catch (Exception e) {}
		}
	}
	
	public static void toggleFullscreen() {
	 	setDisplayMode(800, 600, !Display.isFullscreen());
	}
	
	public static void toggleVSync() {
		  vsync = !vsync;
		  Display.setVSyncEnabled(vsync);
	}
	
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public static long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public static int getFPS() {
		return thisFPS;
	}
	
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public static void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			thisFPS = fps;
			fps = 0;
			lastFPS += 1000;
			cpu = (float)cpuMonitor.getCpuUsage();
		}
		fps++;
	}
	
	public static void initGL() {
		glMatrixMode(GL11.GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 600, 0, 1, -1);
		glMatrixMode(GL11.GL_MODELVIEW);
		
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
		
		
		//Fix transparent pixels being black
		glEnable(GL11.GL_BLEND);
		glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ZERO);
		
		if (GLContext.getCapabilities().GL_ARB_vertex_buffer_object) System.out.println("VBO available :D");
		
		//override, disabled for now
		if(disableFBO && !GLContext.getCapabilities().GL_EXT_framebuffer_object) {
		//	System.out.println("\nFBO not supported :C");
		} else {
			System.out.println("FBO supported :D");
			framebufferID = glGenFramebuffersEXT();
			colorTextureID = glGenTextures();
			depthRenderBufferID = glGenRenderbuffersEXT();
			
			glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, framebufferID);
			glBindTexture(GL_TEXTURE_2D, colorTextureID);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR); 
			
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, 512, 512, 0,GL_RGBA, GL_INT, (java.nio.ByteBuffer) null);
			glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT,GL_COLOR_ATTACHMENT0_EXT,GL_TEXTURE_2D, colorTextureID, 0); 
			
			glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, depthRenderBufferID); 
			glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL14.GL_DEPTH_COMPONENT24, 512, 512);
			glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT,GL_DEPTH_ATTACHMENT_EXT,GL_RENDERBUFFER_EXT, depthRenderBufferID);
			
			glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
		}
	}
	
	public static int getWidth() {
		return Display.getWidth();
<<<<<<< HEAD
<<<<<<< HEAD
	}
	
	public static int getHeight() {
		return Display.getHeight();
	}
	
	public static void toggleIngameMenu() {
		if(viewMode != VIEW_MENU) {
			menuVisible = !menuVisible;
		}
		if(menuVisible) openMenu();
	}
	
	public static void openMenu() {
		GUIManager.show();
	}
	
	public static void loadMap(GameMap newMap) throws SlickException {
		currentMap = newMap;
		currentMap.init();
		EntityManager.playerSpawned = false;
		if(!getMap().getFilepath().equals(bgPath)) {
			EntityManager.playerSpawned = true;
			EntityManager.spawn(new Entity_player());
			AudioManager.playSound("forest_ambience", 1f, 0.04f, true, false);
			AudioManager.addAmbience("birdchirp", 0.2f, 0.15f);
		}
		AIManager.generateNodeMap(currentMap.getData().getTileHeight(), currentMap.getWidth(), currentMap.getHeight());
		System.out.println("Map loaded: "+getMap().getFilepath());
		
	}
	
	public static void loadMap(String path) throws SlickException {
		loadMap(new GameMap(path));
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	public static String getCurrentBackground() {
		return bgPath;
	}
	
<<<<<<< HEAD
	public static String getCurrentMap() {
		return mapPath;
=======
	public static void toggleIngameMenu() {
		if(viewMode != VIEW_MENU) {
			menuVisible = !menuVisible;
		}
	}
	
<<<<<<< HEAD
	public static void loadMap(Tilemap newMap) {
		currentMap = newMap;
		currentMap.init();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
	public static void toggleIngameMenu() {
		if(viewMode != VIEW_MENU) {
			menuVisible = !menuVisible;
		}
	}
	
	public static void loadMap(Tilemap newMap) {
		currentMap = newMap;
		currentMap.init();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	public static int createVBOID() {
		  if (GLContext.getCapabilities().GL_ARB_vertex_buffer_object) {
		    IntBuffer buffer = BufferUtils.createIntBuffer(1);
		    ARBBufferObject.glGenBuffersARB(buffer);
		    return buffer.get(0);
		  }
		  return 0;
	}
	
	public static void bufferData(int id, FloatBuffer buffer) {
		if (GLContext.getCapabilities().GL_ARB_vertex_buffer_object) {
		    ARBBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, id);
		    ARBBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, buffer, ARBBufferObject.GL_STATIC_DRAW_ARB);
		  }
	}
	
	public static void bufferElementData(int id, IntBuffer buffer) {
	  if (GLContext.getCapabilities().GL_ARB_vertex_buffer_object) {
		    ARBBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, id);
		    ARBBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, buffer, ARBBufferObject.GL_STATIC_DRAW_ARB);
		  }
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
	public static GameMap getMap() {
=======
	public static Tilemap getMap() {
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
	public static Tilemap getMap() {
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		return currentMap;
	}
	
	public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

	public static void quit() {
		closeRequested = true;
	}

	public static String getVersion() {
<<<<<<< HEAD
<<<<<<< HEAD
		return version;
	}
	
	public static boolean ingame() {
		return viewMode == VIEW_WORLD;
	}
	
	public static long runningTime() {
		return System.currentTimeMillis() - startTime;
	}

	public static long gameTime() {
		return gameTime;
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		return "0.0x";
	}
	
	public static boolean isInGame() {
		return viewMode == VIEW_WORLD;
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
}