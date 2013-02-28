package main.map;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class EnvObject {
	public static ArrayList<EnvObject> envTable = new ArrayList<EnvObject>();
	private int ID;
	protected String action = "";
	protected Rectangle bounds = new Rectangle(0,0,0,0);
	public Vector2f pos = new Vector2f(0, 0);
	public Vector2f imgOffset = new Vector2f(0, 0);
	public static void addToTable(EnvObject env) {
		envTable.add(env);
		env.setID(envTable.indexOf(env));
	}
	
	public static void updateAll() {
		for(EnvObject env : envTable) {
			env.update();
		}
	}
	
	public static ArrayList<EnvObject> getAll() {
		return envTable;
	}
	
	public static EnvObject getByID(int tID) {
		for(EnvObject env : envTable)
			if(env.getID() == tID)
				return env;
		return null;
	}
	
	public void setAction(String newAction) {
		this.action = newAction;
	}
	
	public Rectangle getBounds() { return bounds;}
	public abstract Image getImg();
	public float getX() { return pos.x; }
	public float getY() { return pos.y; }
	public Vector2f getPos() {return pos;}
	
	public abstract void update();

	public int getID() {
		return ID;
	}
	public void setID(int nID) {
		ID = nID;
	}
}
