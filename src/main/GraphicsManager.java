package main;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

import javax.imageio.ImageIO;

<<<<<<< HEAD
<<<<<<< HEAD
import main.graphics.*;
import main.item.Coin;
import main.item.Item;
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
import main.ai.*;
import main.entity.*;
import main.map.*;
import main.particles.*;
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
<<<<<<< HEAD
import org.newdawn.slick.geom.Shape;
import util.Util;
=======
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.particles.*;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

import util.Util;

import util.Util;

public class GraphicsManager {
	
	static Image[] texture;
	static GameBase panel;
	public static main.particles.ParticleSystem particle_system_fire;
	public static main.particles.ParticleSystem particle_system_smoke;
	public static main.particles.ParticleSystem particle_system_magic;
	private static int sparkct = 0, particle_count = 0;
	public static boolean first_run = true;
	
	private static Color fadeCol = new Color(0, 0, 0);
	private static Color overlayCol = new Color(fadeCol.getRed(), fadeCol.getGreen(), fadeCol.getBlue(), 0);
<<<<<<< HEAD
<<<<<<< HEAD
	
=======
	//Vars with preceding underscore are to be values for render options.  :O
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
	//Vars with preceding underscore are to be values for render options.  :O
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	private static boolean fadeMode = false, helperText = false, shake = false;
	
	static Pathfinder_AStar pathfinderTest = new Pathfinder_AStar(AIManager.getNodeMap());
	
	public static HashMap<Vector2f, String> stringsToDraw = new HashMap<Vector2f, String>();
	
	private static boolean debug = false;
	public static boolean lighting = false;
	
	private static SparkEmitter sparktest = new SparkEmitter_FireMed();
	
<<<<<<< HEAD
	public static ArrayList<RenderCandidate> renderQueue = new ArrayList<RenderCandidate>();
	
	
<<<<<<< HEAD
	public static Image alphaMap;
	public static Image post_lighting;
=======
	private static SparkEmitter sparktest = new SparkEmitter();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
	private static SparkEmitter sparktest = new SparkEmitter();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	
	/**
	 * Initialize state
	 */
	public static void init() {		
		try {
			particle_system_fire = new ParticleSystem(new Image("lib/img/particle/flamelrg_02.tga"));
			particle_system_smoke = new ParticleSystem(new Image("lib/img/particle/smoke_02.tga"));
			particle_system_magic = new ParticleSystem(new Image("lib/img/particle/smoke_02.tga"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		particle_system_smoke.setBlendingMode(ParticleSystem.BLEND_COMBINE);
		particle_system_fire.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
		particle_system_magic.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
		
		
<<<<<<< HEAD
=======
		try {
			grassblade_tex0 = new Image("lib/img/tilesets/individual/grass_blade0a_tint1.png");
			grassblade_tex1 = new Image("lib/img/tilesets/individual/grass_blade1.png");
		} catch(Exception e) {
			e.printStackTrace();
		}
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

	//	Debug grass
	//	GameBase.getMap().createTallGrass(0, new Rectangle(0, 0, 3000, 2000), 15000);
	//	GameBase.getMap().createTallGrass(0, new Rectangle(0, 0, 3000, 2000), 15000);
<<<<<<< HEAD
<<<<<<< HEAD
		
		
		sparktest.init(0.2f, 90, 45, 1, "Fire");
		Overlay_fog.init();
	//	Overlay_color.setColor(Overlay_color.MURKY01, 1);
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
		TiledMap map = GameBase.getMap().getData();
		int groupID = map.getObjectGroupID("grass");
		for(int i = 0; i < map.getObjectCount(groupID); i++) {
			GameBase.getMap().createTallGrass(0, map.getObjectX(groupID, i), map.getObjectY(groupID, i));
		}
		
		sparktest.init(5f, 90, 45, 10, "Fire");
		Detail_fog_overlay.init();
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	/**
	 * Main function that redraws all graphics on a GameBase.
	 * @param g - graphics context
	 * @param delta - update delta
	 */
	public static void renderGame(Graphics g, float delta) {

	//	main.GameBase.g.setDrawMode(main.GameBase.g.MODE_ALPHA_MAP);
	//	main.GameBase.g.setDrawMode(main.GameBase.g.MODE_ALPHA_BLEND);
		
		
		Color oldCol;
		oldCol = g.getColor();

		
		g.setColor(Color.white);
		
		int width = GameBase.getWidth();
		int height = GameBase.getHeight();

	//	Camera.followPlayer();
		Camera.update();
		if(ControlManager.keyStatus(Keyboard.KEY_5)) {
			Camera.moveToPos(
					Util.toWorldX(ControlManager.getMouseX()), 
					Util.toWorldY(ControlManager.getMouseY()), 0.01f);
		} else {
			Camera.followPlayer();
		}
		
		if(GameBase.mapRendering) {
			if(lighting) {
				g.setDrawMode(Graphics.MODE_ALPHA_MAP);
				g.clearAlphaMap();
				g.setColor(new Color(0f, 0f, 0f, 0f));
				g.fillRect(0, 0, GameBase.getWidth()+1, GameBase.getHeight()+1);
				try {
					Image light = new Image("lib/img/particle/flamelrg_02.tga").getScaledCopy(5f);
					light.setAlpha(0.4f);
					g.drawImage(light, 
							Util.toScreenX(EntityManager.getPlayer().getX()) - light.getWidth()/2, 
							Util.toScreenY(EntityManager.getPlayer().getY()) - light.getHeight()/2);
				} catch (SlickException e) {e.printStackTrace();}
				g.setDrawMode(Graphics.MODE_ALPHA_BLEND);
			}
			renderMap(g);
<<<<<<< HEAD
		}
		g.setDrawMode(Graphics.MODE_NORMAL);
=======

<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		

		
		//Set the camera to follow the player with the players coordinates, then update the camera.
		
		
		g.translate(-Camera.getAnchorX(), -Camera.getAnchorY());

		if(!GameBase.mapRendering)
			renderDebugMap(g);

		if(debug) {
<<<<<<< HEAD
<<<<<<< HEAD
			g.setColor(Color.gray);
			for(Object s : EntityManager.getPlayer().getNearbyTiles().values().toArray()) {
				g.draw((Shape)s);
			}
			
			g.setColor(Color.yellow);
			for(Object s : GameBase.getMap().getObstacleArray(
					EntityManager.getPlayer().getBounds())) {
				g.draw((Shape)s);
			}
			
			g.setColor(Color.white);
			g.drawLine(
					EntityManager.getPlayer().getAttackArea().getCenterX(),
					EntityManager.getPlayer().getAttackArea().getCenterY(),
					EntityManager.getPlayer().pos.x,
					EntityManager.getPlayer().pos.y
					);
			
			g.setColor(Color.red);
			if(EntityManager.getPlayer().attacked)
				g.fill(EntityManager.getPlayer().getAttackArea());
			else
				g.draw(EntityManager.getPlayer().getAttackArea());
			
			g.setColor(Color.white);
			
			
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			for(Shape r : GameBase.getMap().getNearbyObstacles(
					EntityManager.getPlayer().tilepos.x, EntityManager.getPlayer().tilepos.y)) {
				if(r != null) {
					g.draw(r);
				}
			}
			
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		//	AIManager.renderNodeMap(g);		
			for(Entity e : EntityManager.entityTable)
				if(e.getCurrentPath() != null) {
					e.getCurrentPath().render(g);		
				}
		}
		

		if(ControlManager.mouseButtonClicked(ControlManager.mousePrimary)) {
			for(int c = 0; c < 5; c++) {
				EntityManager.spawn(
					new Coin(
						new Vector2f(
								Util.toWorldX(ControlManager.getMouseX()), 
								Util.toWorldY(ControlManager.getMouseY())), 
						new Vector3f(
								(float)(Math.random()-0.5) * 0.2f,
								(float)(Math.random()-0.5) * 0.2f,
								(float)(Math.random()+0.5) * 0.2f),
						(int)Math.round(Math.random() * 2))
					);
			}
		}
		
		if(ControlManager.mouseButtonClicked(ControlManager.mouseSecondary)) {
			for(int c = 0; c < 1; c++) {
				EntityManager.spawn(
					new Coin(
						new Vector3f(
								Util.toWorldX(ControlManager.getMouseX()), 
								Util.toWorldY(ControlManager.getMouseY()),
								40), 
						new Vector3f(
								(float)(Math.random()-0.5) * 0.3f,
								(float)(Math.random()-0.5) * 0.3f,
								(float)(Math.random()+0.5) * 0.1f),
						Coin.BAG)
					);
			}
		}
		
		if(debug) {
			Entity_player player = EntityManager.getPlayer();
			g.draw(new Circle(player.getX(), player.getY(), player.getBounds().getBoundingCircleRadius()+4));
			
			if(ControlManager.mouseButtonStatus(ControlManager.mousePrimary)) {
				g.setColor(Color.red);
			}
			
			if(ControlManager.mouseButtonStatus(ControlManager.mouseSecondary)) {
				int tileX = GameBase.getMap().getTileAtX(Util.toWorldX(ControlManager.getMouseX()));
				int tileY = GameBase.getMap().getTileAtY(Util.toWorldY(ControlManager.getMouseY()));
				Color oldColor = g.getColor();
				g.setColor(Color.black);
				g.drawRect(Util.toScreenX(tileX * 32), Util.toScreenY(tileY * 32), 32, 32);
				g.setColor(oldColor);
			//	System.out.println("ID: "+(GameBase.getZone().getData().getTileId(tileX, tileY, 2)));
			}
			
<<<<<<< HEAD
<<<<<<< HEAD
			g.drawRect(Util.toWorldX(ControlManager.getMouseX() - 8),  Util.toWorldY(ControlManager.getMouseY() - 8), 16, 16);
			

			
			
			if(!GameBase.menuVisible && GameBase.debug_particles) {
				if(ControlManager.mouseButtonClicked(ControlManager.mousePrimary)) {
					
					for(int i = 0; i < 5; i++) {
						particle_system_smoke.addEmitter(new Emitter_Smoke_ContinuousMed());
						particle_system_smoke.getEmitter(particle_system_smoke.getEmitterCount()-1).setPos(Util.toWorldX(ControlManager.getMouseX()), Util.toWorldY(ControlManager.getMouseY()));
					}
					for(int i = 0; i < 5; i++) {
						particle_system_fire.addEmitter(new Emitter_FireMed());
						particle_system_fire.getEmitter(particle_system_fire.getEmitterCount()-1).setPos(Util.toWorldX(ControlManager.getMouseX()), Util.toWorldY(ControlManager.getMouseY()));
					}
				
	
				//	sparktest.createSparksAt(new Vector2f(Util.toWorldX(ControlManager.getMouseX()), Util.toWorldY(ControlManager.getMouseY())), 100);
				}
				
				if(ControlManager.mouseButtonClicked(ControlManager.mouseSecondary)) {
				//	System.out.println("clicky");
					for(int i = 0; i < 5; i++) {
						particle_system_magic.addEmitter(new Emitter_UmbrinSmoke_1());
						particle_system_magic.getEmitter(particle_system_magic.getEmitterCount()-1).setPos(Util.toWorldX(ControlManager.getMouseX()), Util.toWorldY(ControlManager.getMouseY()));
					}
					AudioManager.playSound("large_explosion", 3f, 1f);
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		//	g.drawRect(Util.toWorldX(ControlManager.getMouseX() - 8),  Util.toWorldY(ControlManager.getMouseY() - 8), 16, 16);

			if(ControlManager.mouseButtonClicked(ControlManager.mousePrimary)) {
				/*
				for(int i = 0; i < 5; i++) {
					particle_system_smoke.addEmitter(new Emitter_Smoke_ContinuousMed());
					((main.particles.ParticleEmitter)particle_system_smoke.getEmitter(particle_system_smoke.getEmitterCount()-1)).setPos(Util.toWorldX(ControlManager.getMouseX()), Util.toWorldY(ControlManager.getMouseY()));
				}
				for(int i = 0; i < 5; i++) {
					particle_system_fire.addEmitter(new Emitter_FireMed());
					((main.particles.ParticleEmitter)particle_system_fire.getEmitter(particle_system_fire.getEmitterCount()-1)).setPos(Util.toWorldX(ControlManager.getMouseX()), Util.toWorldY(ControlManager.getMouseY()));
				}
				*/

				sparktest.createSparksAt(new Vector2f(Util.toWorldX(ControlManager.getMouseX()), Util.toWorldY(ControlManager.getMouseY())), 100);
			}
			
			if(ControlManager.mouseButtonClicked(ControlManager.mouseSecondary)) {
				System.out.println("clicky");
				for(int i = 0; i < 5; i++) {
					particle_system_magic.addEmitter(new Emitter_Magic_BurstSmall());
					((main.particles.ParticleEmitter)particle_system_magic.getEmitter(particle_system_magic.getEmitterCount()-1)).setPos(Util.toWorldX(ControlManager.getMouseX()), Util.toWorldY(ControlManager.getMouseY()));
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
				}
			}
		}
		
	//	for(int i = 0; i < particle_system_magic.getEmitterCount(); i++) {
	//		((main.particles.ParticleEmitter)particle_system_magic.getEmitter(i)).setPos(ControlManager.getMouseX(), ControlManager.getMouseY());
	//	}
		
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		if(GameBase.mapRendering) {
			for(ArrayList<Detail_grassblade_med> grass_group : GameBase.getMap().getTallGrass()) {
				for(Detail_grassblade_med grass_blade : grass_group)
					if(grass_blade.y <= EntityManager.getPlayer().getY()) 
						grass_blade.draw();
			}
		}

>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		renderEntities(g);

		if(GameBase.mapRendering) { 
			for(ArrayList<Detail_grassblade_med> grass_group : GameBase.getMap().getTallGrass()) {
				for(Detail_grassblade_med grass_blade : grass_group)
<<<<<<< HEAD
<<<<<<< HEAD
					grass_blade.draw();
=======
					if(grass_blade.y >= EntityManager.getPlayer().getY()) 
						grass_blade.draw();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
					if(grass_blade.y >= EntityManager.getPlayer().getY()) 
						grass_blade.draw();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			}
		}

		
		sparktest.update();
		particle_system_smoke.update((int)delta);
		particle_system_fire.update((int)delta);
		particle_system_magic.update((int)delta);

		sparktest.render();
		particle_system_smoke.render();
		particle_system_fire.render();
		particle_system_magic.render();
		
<<<<<<< HEAD
<<<<<<< HEAD
		for(EnvObject env : EnvObject.envTable) {
		//	System.out.println(env.getID());
			submitToQueue(env.getImg(), env.getX()+env.imgOffset.x, env.getY()+env.imgOffset.y, env.getY()+32);
		}
		

		if(debug) {
			drawString("render queue: "+renderQueue.size(), Util.toWorld(new Vector2f(GameBase.getWidth()-150, 300)));
			drawString("    mem free: "+Math.round(Runtime.getRuntime().freeMemory()*(0.000000953674)*10000)/10000+" MB", Util.toWorld(new Vector2f(GameBase.getWidth()-150, 310)));
			drawString("   mem total: "+Math.round(Runtime.getRuntime().totalMemory()*(0.000000953674)*10000)/10000+" MB", Util.toWorld(new Vector2f(GameBase.getWidth()-150, 320)));
			drawString("         cpu: "+(100-GameBase.getCpuUsage()*100)+"%", Util.toWorld(new Vector2f(GameBase.getWidth()-150, 330)));
		

		}
			
		Font oldFont = g.getFont();
		Color oldColor = g.getColor();
		if(!GameBase.mapRendering) 
			g.setColor(Color.white);
		
		g.setFont(GameBase.debugFont);
		for(int i = 0; i < stringsToDraw.size(); i++) {
			Vector2f pos = (Vector2f)stringsToDraw.keySet().toArray()[i];
			g.drawString(stringsToDraw.get(pos), pos.x, pos.y);
		}
		stringsToDraw.clear();
		g.setColor(oldColor);
		g.setFont(oldFont);
		
		finalRender(g);
		

		g.translate(Camera.getAnchorX(), Camera.getAnchorY());
		GameBase.getMap().renderLast(0, 0);

		Overlay_fog.update();
		Overlay_fog.render();
		Overlay_color.update();
		Overlay_color.render(g);

		//Stamina bar
		float staminaFraction = EntityManager.getPlayer().getStaminaFraction();
		g.setColor(Color.white);
		g.drawRect(18, 498, 204, 14);
		g.drawRect(20, 500, 200 * staminaFraction, 10);
		g.setColor(new Color(0f, 0.5f, 0f, 0.5f));
		g.fillRect(21, 501, 198 * staminaFraction, 8);
		g.setColor(new Color(1f, 0f, 0f, (0.5f - (0.2f * (float)Math.sin(GameBase.runningTime() * 0.008f))) * (1f - staminaFraction)));
		g.setLineWidth(3f);
		g.drawRect(17,  497, 205, 16);
		g.setLineWidth(1f);
=======
		

		g.translate(Camera.getAnchorX(), Camera.getAnchorY());
=======
		

		g.translate(Camera.getAnchorX(), Camera.getAnchorY());
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

		Detail_fog_overlay.update();
		Detail_fog_overlay.render();

		//Stamina bar
		g.setColor(Color.white);
		g.drawRect(18, 498, 204, 14);
		g.drawRect(20, 500, 200 * EntityManager.getPlayer().getStaminaFraction(), 10);
		g.setColor(new Color(0f, 0.5f, 0f, 0.5f));
		g.fillRect(21, 501, 198 * EntityManager.getPlayer().getStaminaFraction(), 8);
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
		//Health bar
		float healthFraction = EntityManager.getPlayer().getHealth()/EntityManager.getPlayer().getTotalHealth();
		g.setColor(Color.white);
		g.drawRect(18, 528, 204, 14);
		g.drawRect(20, 530, 200 * healthFraction, 10);
		g.setColor(new Color(0.5f, 0f, 0f, 0.5f));
		g.fillRect(21, 531, 198 * healthFraction, 8);
<<<<<<< HEAD
<<<<<<< HEAD
		
		//Money
		g.drawImage(ImageLoader.getSheet("lib/img/item/coin/gold.png").getSprite(0,  0), 20, 460, new Color(1f, 1f, 1f, 0.7f));
		g.setFont(FontLoader.getFont("JUNIB___", 18f));
		g.setColor(new Color(0f, 0f, 0f, 0.6f));
		g.drawString(""+EntityManager.getPlayer().getMoney(), 38, 455);
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
		g.setColor(oldCol);
		g.setFont(oldFont);
		g.clearWorldClip();
		
		
		if(ControlManager.keyPressed(Keyboard.KEY_F2)) {
			
			ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);
			GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer );
			
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			 
			for(int x = 0; x < width; x++)
				for(int y = 0; y < height; y++)
				{
					int i = (x + (width * y)) * 4;
					int r = buffer.get(i) & 0xFF;
					int g1 = buffer.get(i + 1) & 0xFF;
					int b = buffer.get(i + 2) & 0xFF;
					image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g1 << 8) | b);
				}
			try {
				String date = GameBase.getDateTime().replace("/", "-").replace(":", "-").replace(" ", "_");
				ImageIO.write(image, "PNG", new File("lib/img/screenshots/"+date+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			System.out.println("Saved screenshot");
		}
	}
	
	public static void finalRender(Graphics g) {
		//final render
		renderAllInQueue(g);
		renderQueue.clear();
		renderQueue.trimToSize();
	}
	
	public static void renderMenuBackground(Graphics g) {	
		if(GameBase.mapRendering) {
			renderMap(g);
			for(ArrayList<Detail_grassblade_med> grass_group : GameBase.getMap().getTallGrass()) {
				for(Detail_grassblade_med grass_blade : grass_group)
					grass_blade.draw();
			}
		}
		finalRender(g);
	}
	
	public static void renderAllInQueue(Graphics g) {
		Collections.sort(renderQueue, new AffinityComparator());
		for(RenderCandidate rc : renderQueue) {
			if(rc.getShear().length() != 0) {
				rc.getImage().drawSheared(rc.getX(), rc.getY(), rc.getShear().x, rc.getShear().y);
			} else {
				g.drawImage(rc.getImage(), rc.getX(), rc.getY(), rc.getColor());
			}
		//	drawString(""+rc.getPriority(), rc.getX(), rc.getY());
		}
	}
	
	public static void submitToQueue(RenderCandidate rc) {
		renderQueue.add(rc);
	}
	
	public static void submitToQueue(Image i, float x, float y, float priority) {
		renderQueue.add(new RenderCandidate(i, x, y, priority));
	}
	
	public static void submitToQueue(Image img, float x, float y, Color col, float priority) {
		renderQueue.add(new RenderCandidate(img, x, y, col, priority));
	}
	
	public static void drawString(String s, float x, float y) {
		drawString(s, new Vector2f(x, y));
	}
	
	public static void drawString(String s, Vector2f pos) {
		if(GameBase.debug_text)
			stringsToDraw.put(pos, s);
	}
	
	public static ParticleSystem getParticleSystemFire() {
		return particle_system_fire;
	}
	
	public static void renderEntities(Graphics g) {
		for(int i = 0; i < EntityManager.getTableLength(); i++) {
<<<<<<< HEAD
			Color oldCol = g.getColor();
			if(EntityManager.getByIndex(i) instanceof Entity_mobile) {
				Entity_mobile curEnt = (Entity_mobile)EntityManager.getByIndex(i);
				if(curEnt.getImg() == null) continue;
				
				if(curEnt instanceof Entity_player) {
					curEnt = curEnt;
				} else if(curEnt instanceof Entity_mobile) {
					curEnt = curEnt;
				}
				
				if(debug) 
					g.draw(curEnt.getBounds());
				
				float x = curEnt.getX() - curEnt.getImg().getWidth()/2, y = curEnt.getY() - curEnt.getImg().getHeight()/2;
				
				g.setColor(new Color(0, 0, 0, 0.3f * curEnt.getImg().getAlpha()));
			//	g.setDrawMode(g.MODE_ALPHA_BLEND);
				if(curEnt.getName().equals("player")) {
					g.fillOval(curEnt.getBounds().getCenterX() -15 + curEnt.shadow_offset.x , curEnt.getBounds().getCenterY() - curEnt.shadow_offset.y, 30, 20);
				}
				
				curEnt.drawFgEffects();
				if(curEnt.getName().equals("umbrin_test") && !curEnt.isDead()) {
					float cycle = (float)Math.sin(GameBase.runningTime() * 0.01f) * 0.2f + 0.8f;
					Color darkAmt = new Color(cycle, cycle, cycle, 0.8f);
					submitToQueue(curEnt.getImg(), (int)x + curEnt.getImgOffsetX(), (int)y + curEnt.getImgOffsetY(), darkAmt, curEnt.getY());
				}
				else if(curEnt.getName().equals("player")) {
					submitToQueue(curEnt.getImg(), (int)x + curEnt.getImgOffsetX(), (int)y + curEnt.getImgOffsetY(), curEnt.getY());
				}
				curEnt.drawBgEffects();
			} else if(EntityManager.getByIndex(i) instanceof Item) {
				Item curEnt = (Item)EntityManager.getByIndex(i);

				if(curEnt.getImg() == null) continue;

				if(debug) 
					g.draw(curEnt.getBounds());
				
				if(!curEnt.stored) { 
					g.setColor(new Color(0, 0, 0, 0.3f * curEnt.getImg().getAlpha()));
					float shadowWidth = curEnt.getImg().getWidth() * 0.6f;
					g.fillOval(curEnt.getBounds().getCenterX()-shadowWidth/2, curEnt.getBounds().getCenterY(), shadowWidth, shadowWidth/3);
					curEnt.draw();
				}
			}
			g.setColor(oldCol);
=======
			Entity curEnt = EntityManager.getByIndex(i);
			if(curEnt.getImg() == null) continue;
			
			if(curEnt instanceof Entity_player) {
				curEnt = (Entity_player)curEnt;
			} else if(curEnt instanceof Entity_mobile) {
				curEnt = (Entity_mobile)curEnt;
			}
			
			float z;
			
			if(curEnt instanceof Entity_mobile) {
				z = ((Entity_mobile) curEnt).getZ();
			} else z = 0;
			
			if(debug) 
				g.draw(curEnt.getBounds());
			
			float x = curEnt.getX() - curEnt.getImg().getWidth()/2, y = curEnt.getY() - curEnt.getImg().getHeight()/2;
			
			g.setColor(new Color(0, 0, 0, 0.4f));
			g.fillOval(curEnt.getBounds().getCenterX() -15 , curEnt.getBounds().getCenterY() + curEnt.getImgOffsetY() + z + 10, 30, 8);
			
			curEnt.drawFgEffects();
			g.drawImage(curEnt.getImg(), (int)x + curEnt.getImgOffsetX(), (int)y + curEnt.getImgOffsetY());
			curEnt.drawBgEffects();
			
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		}
	}
	

	public static void renderMap(Graphics g) {
<<<<<<< HEAD
<<<<<<< HEAD
		GameBase.getMap().render(0,0);//-Math.round(Camera.getAnchorX()), -Math.round(Camera.getAnchorY()));
	}
	
	public static void renderDebugMap(Graphics g) {
		GameBase.getMap().renderDebug(g);
=======
		GameBase.getMap().render(-(int)Camera.getAnchorX(), -(int)Camera.getAnchorY());
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		GameBase.getMap().render(-(int)Camera.getAnchorX(), -(int)Camera.getAnchorY());
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	
	/**
	 * Simple method to display strings.
	 * @param g2 - Graphics2D context
	 * @param text - String in question
	 * @param x - Pos in pixels
	 * @param y - Pos in pizels
	 * @param local - True means it's local to the camera, false means it's static on the map surface.
	 */
	public static void print(Graphics g, String text, float x, float y, boolean local) {
		if(local) 
			g.drawString(text, (int)Util.toScreenX(x), (int)Util.toScreenY(y));
		else
			g.drawString(text, (int)x, (int)y);
	}
	
	/**Tally up all the particles
	 * 
	 */
	public static int getParticleCount() {
		return particle_system_smoke.getParticleCount() + particle_system_fire.getParticleCount();
	}
	
	/**
	 * Simple grid drawing algorithm
	 * @param g - Graphics context
	 */
	public static void renderGrid(Graphics g) {
		
		int k=0;
		Color oldColor = g.getColor();
		g.setColor(new Color(200, 200, 200));
		int htOfRow = GameBase.getHeight() / 15;
		for (k = 0; k <= 15; k++)
			g.drawLine(0, k * htOfRow , GameBase.getWidth(), k * htOfRow );
		
		int wdOfRow = GameBase.getWidth() / 20;
		for (k = 0; k <= 20; k++) 
			g.drawLine(k*wdOfRow , 0, k*wdOfRow , GameBase.getHeight());
		
		g.setColor(oldColor);
	}
	
	public static void setDebugMode(boolean mode) {
		debug = mode;
	}
	
	/**
	 * Mutator to update boolean fade variable.
	 * @param mode - true/false to toggle if its fading in or out respectively
	 */
	public static void setFade(boolean mode) {
		fadeMode = mode;
	}
	
	/**
	 * Update overlay alpha to obscure screen
	 */
	public static void fade() {
		float fadeIncrement = ControlManager.getDelta() * 0.1f;
		if(fadeMode) {
			if(overlayCol.getAlpha() > 0 - fadeIncrement)
				overlayCol = new Color(overlayCol.getRed(), overlayCol.getGreen(), overlayCol.getBlue(), overlayCol.getAlpha() - fadeIncrement);
			else
				overlayCol = new Color(overlayCol.getRed(), overlayCol.getGreen(), overlayCol.getBlue(), 0);
				
		}
		else {
			if(overlayCol.getAlpha() < 255 + fadeIncrement)
				overlayCol = new Color(overlayCol.getRed(), overlayCol.getGreen(), overlayCol.getBlue(), overlayCol.getAlpha() + fadeIncrement);
			else
				overlayCol = new Color(overlayCol.getRed(), overlayCol.getGreen(), overlayCol.getBlue(), 255);
		}
	}
	
	public static void fadeToggle() {
		fadeMode = !fadeMode;
		fade();
		System.out.println("Fade: "+fadeMode);
	}
	
	/**
	 * Updated every tick to show helper text (will reset after each tick)
	 * @param mode - true/false to show/hide helper text respectively
	 */
	public static void showHelperText(boolean mode) {
		helperText = mode;
	}
	
	public static void createLight(Vector2f pos, float size, float brightness, Color tint) {
		
	}
	
	
	/**
	 * Set camera shaking to true (is reset to false after each tick)
	 */
	public static void shake() {
		shake = true;
	}

	public static void renderMenuOverlay(Graphics g) {
		Color oldCol = g.getColor();
		g.setColor(new Color(0.2f, 0.2f, 0.2f, 0.4f));
		g.fillRect(0, 0, GameBase.getWidth(), GameBase.getHeight());
		g.setColor(oldCol);
	}
}
