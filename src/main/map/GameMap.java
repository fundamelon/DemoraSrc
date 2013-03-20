package main.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import main.GameBase;
import main.ai.NodeMap;
import main.entity.EntityManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import util.Util;

@SuppressWarnings("all")
public class GameMap {
	private TiledMap mapData;
	private HashMap<Vector2f, Rectangle> collisionMap = new HashMap<Vector2f, Rectangle>(), obstacleMap = new HashMap<Vector2f, Rectangle>();
	private ArrayList<ArrayList<Detail_grassblade_med>> grasses = new ArrayList<ArrayList<Detail_grassblade_med>>();
	private ArrayList<Detail_grassblade_med> indies = new ArrayList<Detail_grassblade_med>();
	private String path;

	public static Image grassblade_tex0, grassblade_tex1;
	
	public GameMap(String newPath) {
		readFromFile(newPath);
		init();
	//	if(mapData.getLayerIndex("structures_roof") != -1)
	//		mapData.setLayerOpacity(mapData.getLayerIndex("structures_roof"), 0.5f);
	}
	
	public void readFromFile(String newPath) {
		path = newPath;
		try {
			mapData = new TiledMap(path);
		} catch (Exception e) {	e.printStackTrace(); }
		
	}
	
	public void readFromFile() {
		readFromFile("lib/map/test.tmx");
	}
	
	public void init() {
		collisionMap.clear();
		obstacleMap.clear();
		loadEnvObjects();
		loadTriggers();
		buildCollisionMap();

		try {
			grassblade_tex0 = new Image("lib/img/tilesets/individual/grass_blade0a_tint2.png");
			grassblade_tex1 = new Image("lib/img/tilesets/individual/grass_blade1.png");
		} catch(Exception e) {
			e.printStackTrace();
		}
		grasses.clear();
		int groupID = mapData.getObjectGroupID("grass");
		for(int i = 0; i < mapData.getObjectCount(groupID); i++) {
			createTallGrass(0, mapData.getObjectX(groupID, i), mapData.getObjectY(groupID, i));
		}
		grasses.add(indies);
		
	}
	
	public void update() {
		updateObstacles();
		if(EntityManager.getPlayer()!=null) {
			int indoors = getPropertyValue(
					(int)EntityManager.getPlayer().tilepos.x,
					(int)EntityManager.getPlayer().tilepos.y, 
					"structures_util",
					"indoors");
			
			if(indoors == 1)  {
				((Layer)mapData.layers.get(mapData.getLayerIndex("structures_roof"))).fadeOut();
			} else {
				((Layer)mapData.layers.get(mapData.getLayerIndex("structures_roof"))).fadeIn();
			}
		}
	}
	
	public TiledMap getData() {
		return mapData;
	}
	
	public int getWidth() {
		return mapData.getWidth();
	}
	
	public int getHeight() {
		return mapData.getHeight();
	}
	
	public int getWidthPixels() {
		return mapData.getWidth() * mapData.getTileWidth();
	}
	
	public int getHeightPixels() {
		return mapData.getHeight() * mapData.getTileHeight();
	}
	
	public void render(int x, int y) {
		for(int i = 0; i < mapData.getLayerCount(); i++) {
			if(		i != mapData.getLayerIndex("spawn") && 
					i != mapData.getLayerIndex("env") && 
					i!=mapData.getLayerIndex("structures_roof") && 
					i!=mapData.getLayerIndex("structures_top") && 
					i!=mapData.getLayerIndex("structures_util") && 
					!(i == mapData.getLayerIndex("util") && !GameBase.debug_tileUtil)) {
				mapData.render(x, y, i);
			}
		}
	}
	
	public void renderLast(int x, int y) {
		mapData.render(x, y, mapData.getLayerIndex("structures_top"));
		Color oldCol = main.GameBase.g.getColor();
		main.GameBase.g.setColor(Color.black);
		mapData.render(x, y, mapData.getLayerIndex("structures_roof"));
		main.GameBase.g.setColor(oldCol);
	}
	
	public void renderDebug(Graphics g) {
		Iterator it = collisionMap.entrySet().iterator();
	//	System.out.println(it.toString());
		while(it.hasNext()) {
			if(collisionMap.get(it)!=null) {
				System.out.println(collisionMap.get(it));
				g.draw(collisionMap.get(it));
			}
			it.next();
		}
	}
	
	public Vector2f playerSpawnPos() {
		Vector2f out = null;
		int index = mapData.getObjectGroupID("spawn");
		if(index != -1 )
			if(mapData.getObjectCount(index) > 0)
				out = new Vector2f(mapData.getObjectX(index, 0) + 64, mapData.getObjectY(index, 0) - 18);
			else System.out.println("No spawn points found");
		else System.out.println("No spawn layer defined");
		return out;
	}
	
	public int getTileAtX(float ox) {
		return (int)Math.floor(ox / mapData.getTileWidth());
	}
	
	public int getTileAtY(float oy) {
		return (int)Math.floor(oy / mapData.getTileHeight());
	}
	
	public void buildCollisionMap() {
		if(mapData.getLayerIndex("util") == -1) return;
		for(int x = 0; x < getWidth(); x++) {
			for(int y = 0; y < getHeight(); y++) {
				if(blocked(x, y)) {
					collisionMap.put(
							new Vector2f(x*mapData.getTileWidth(), y*mapData.getTileHeight()),  
							new Rectangle(x*mapData.getTileWidth(), y*mapData.getTileWidth(), mapData.getTileWidth(), mapData.getTileWidth()));
				}
			}
		}
	}
	
	public void loadEnvObjects() {
		EnvObject.envTable.clear();
		int gid = mapData.getObjectGroupID("env");
		for(int i = 0; i < mapData.getObjectCount(gid); i++) {
			EnvObject env = null;
			String type = mapData.getObjectType(gid, i);
			if(type.contains("door")) {
				Image trans = null;
				env = new Door(mapData.getObjectX(gid, i), mapData.getObjectY(gid, i), 64, 160, type);
				System.out.println("Made a door: "+gid+", "+i);
			}
			
			if(env != null) {
				obstacleMap.put(env.pos, env.bounds);
			}
		}
	}
	
	public void loadTriggers() {
		int i = mapData.getLayerIndex("util");
		if(i != -1) {
			for(int x = 0; x < getWidth(); x++) {
				for(int y = 0; y < getHeight(); y++) {
					//TODO
					mapData.getTileProperty(getData().getTileId(x, y, mapData.getLayerIndex("util")), "collision", "0");
				}
			}
		}
	}
	
	public HashMap<Vector2f, Shape> getNearbyTiles(Shape s, int dist) {
		HashMap<Vector2f, Shape> total = new HashMap<Vector2f, Shape>();
		HashMap<Vector2f, Shape> tempArray = new HashMap<Vector2f, Shape>();
		for(int r = (int)Math.floor(s.getX()/mapData.getTileWidth()); r <= Math.floor((s.getWidth()+s.getX())/mapData.getTileWidth()); r++) {
			for(int c = (int)Math.floor(s.getY()/mapData.getTileWidth()); c <= Math.floor((s.getHeight()+s.getY())/mapData.getTileWidth()); c++) {
				total.put(new Vector2f(r, c), new Rectangle(r * mapData.getTileWidth(), c * mapData.getTileWidth(), mapData.getTileWidth(), mapData.getTileWidth()));
			}
		}
		while(dist > 0) {
			for(int i = 0; i < total.size(); i++) {
				Shape a = (Shape)total.values().toArray()[i];
				int x = (int)Math.floor(a.getX()/mapData.getTileWidth());
				int y = (int)Math.floor(a.getY()/mapData.getTileWidth());
				for(int r = 1; r >= -1; r--) {
					for(int c = 1; c >= -1; c--) {
						if(true) {
							if(x+c >= 0 && y+r >= 0 && x+c < getWidth() && y+r < getHeight()) {
								tempArray.put(new Vector2f(x+c, y+r), new Rectangle((x+c)*mapData.getTileWidth(), (y+r)*mapData.getTileWidth(), mapData.getTileWidth(), mapData.getTileWidth()));
							}
						}
					}
				}
			}
			total.putAll(tempArray);
			tempArray.clear();
			dist--;
		}
	
		return total;
	}
	
	public void updateObstacles() {
		obstacleMap.clear();
		ArrayList<EnvObject> envTable = EnvObject.getAll();
		for(int i = 0; i < envTable.size(); i++) {
			if(envTable.get(i) != null) {
				EnvObject env = envTable.get(i);
				obstacleMap.put(new Vector2f(env.getBounds().getX(), env.getBounds().getY()), env.getBounds());
			}
		}
	}
	
	public HashMap<Vector2f, Shape> getNearbyObstacles(float x, float y) {
		HashMap<Vector2f, Shape> out = new HashMap<Vector2f, Shape>();
	/*	Iterator it = collisionMap.entrySet().iterator();
		while(it.hasNext()) {
			Shape current = collisionMap.get(it);
			if(current.getCenterX() - x < current.getWidth()) {
				out.put(collisionMap., current);
			}
			it.remove();
		}
		*/
		out.putAll(obstacleMap);
		return out;
	}
	
	public ArrayList<Shape> getObstacleArray(Shape bounds) {
		ArrayList<Shape> out = new ArrayList<Shape>();
		HashMap<Vector2f, Shape> nearbyTiles = getNearbyTiles(bounds, 1);
	//	System.out.println(nearbyTiles.size());
		for(Object _s : nearbyTiles.values().toArray()) {
			Shape s = (Shape)_s;
			if(s != null && blockedAtPixel(s.getCenterX(), s.getCenterY()))
				out.add(s);
		}
		for(Object _s : obstacleMap.values().toArray()) {
			out.add((Shape)_s);
		}
		return out;
	}
	
	public void getCollisionNeighbors(int x, int y) {
		int i = y*getWidth() + x;
		System.out.println(collisionMap.keySet().toString());
	}
	
	//TODO: Broken, add support for map polygon regions
	public boolean isWithinRegion(String name) {
		int gid = mapData.getObjectGroupID(name);
		for(int i = 0; i < mapData.getObjectCount(gid); i++) {
			System.out.println(mapData.getObjectType(gid, i));
		}
		return false;
	}
	
	public void createTallGrass(int type, Rectangle bounds, int count) {
		ArrayList<Detail_grassblade_med> group = new ArrayList<Detail_grassblade_med>();
		grasses.add(group);
		
		Random gen = new Random();
		for(int i = 0; i < count; i++) {
			group.add(new Detail_grassblade_med(type, bounds.getX() + gen.nextInt((int)bounds.getWidth()), bounds.getY() + gen.nextInt((int)bounds.getHeight())));
		}
		
		group = sortTallGrass(group);
	}
	
	public ArrayList<Detail_grassblade_med> sortTallGrass(ArrayList<Detail_grassblade_med> group) {

		for(int i = 0; i < group.size(); i++) {
			float min = group.get(i).y;
			int minIndex = i;
			for(int c = i+1; c < group.size(); c++) {
				if(group.get(c).y < min) {
					min = group.get(c).y;
					minIndex = c;
				}
			}
			Detail_grassblade_med trans = group.get(i);
			group.set(i, group.get(minIndex));
			group.set(minIndex, trans);
		}
		
		return group;
	}
	
	public ArrayList<ArrayList<Detail_grassblade_med>> getTallGrass() {
		return grasses;
	}
	
	public ArrayList<Detail_grassblade_med> getTallGrassGroup(int id) {
		return grasses.get(id);
	}
	
	public Detail_grassblade_med getOneTallGrass(int id, int i) {
		return grasses.get(id).get(i);
	}
	
	public int idToTileX(int id) {
		return id == 0 ? 0 : id%getWidth();
	}
	
	public int idToTileY(int id) {
		return id == 0 ? 0 : (int)(id/getHeight());
	}
	
	public HashMap<Vector2f, Rectangle> getCollisionArray() {
		return collisionMap;
	}
	
	public String getFilepath() {
		return path;
	}
	
	public Map<Vector2f, Rectangle> getCollisionMap() {
		return collisionMap;
	}
	
	public boolean blockedAtPixel(float x, float y) {
		return 1 == collisionType(getTileAtX(x), getTileAtY(y));
	}
	
	public boolean blocked(int x, int y) {
		if(x >= 0 && y >= 0 && x < mapData.width && y < mapData.height)
			return 1 == collisionType(x, y);
		else return true;
	}
	
	public boolean blockedAtTile(Vector pos) {
		Vector2f tpos = (Vector2f)pos;
		return blocked((int)tpos.x, (int)tpos.y);
	}
	
	public boolean blockedAtTile(float x, float y) {
		return blockedAtTile(new Vector2f(x, y));
	}
	
	public boolean blocked(Vector pos) {
		Vector2f tpos = (Vector2f)pos;
		return blockedAtPixel(tpos.x, tpos.y);
	}
	
	public boolean blocked(int i) {
		return blocked(idToTileX(i), idToTileY(i));
	}
	
	public int collisionType(int x, int y) {
		if(mapData.getLayerIndex("util") == -1) return -1;
		if(x >= 0 && y >= 0 && x < mapData.getWidth() && y < mapData.getHeight())
			return Integer.parseInt(mapData.getTileProperty(getData().getTileId(x, y, mapData.getLayerIndex("util")), "collision", "0"));
		else return 0;
	}

	public int getPropertyValue(int x, int y, String layer, String name) {
		String pre = getPropertyString(x, y, layer, name);
		return pre == null ? -1 : Integer.parseInt(pre);
	}
	
	public String getPropertyString(int x, int y, String layer, String name) {
		if(mapData.getLayerIndex(layer) == -1) return null;
		return mapData.getTileProperty(getData().getTileId(x, y, mapData.getLayerIndex(layer)), name, "0");
	}
	
	public int collisionType(int i) {
		return collisionType(idToTileX(i), idToTileY(i));
	}

	public void createTallGrass(int type, int x, int y) {
		indies.add(new Detail_grassblade_med(type, x, y));
		indies = sortTallGrass(indies);
	}
	
	public Shape getTileAt(Vector2f pos) {
		return new Rectangle(getTileAtX(pos.x), getTileAtY(pos.y), mapData.getTileWidth(), mapData.getTileWidth());
	}

	public Vector2f getTilePosAt(Vector pos) {
		Vector2f tpos = (Vector2f)pos;
		return new Vector2f(getTileAtX(tpos.x), getTileAtY(tpos.y));
	}

	public Vector2f getTilePosAt(float x, float y) {
		return getTilePosAt(new Vector2f(x, y));
	}

}