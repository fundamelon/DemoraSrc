package main.map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import util.Transition;

public class Overlay_color {
	public static final Color NORMAL = new Color(1f, 1f, 1f);
	public static final Color FOREST01 = new Color(1.20f, 1.21f, 1.23f);
	
	

	public static Color col = FOREST01;
	public static Transition colAnim;
	
	public static void update() {
		if(colAnim != null && colAnim.finished()) colAnim = null;
	}
	
	public static void render(Graphics g) {
		Color oldCol = g.getColor();
		g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);
		if(colAnim != null)
			g.setColor(colAnim.getCurCol());
		else
			g.setColor(col);
		
		g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);
		g.fillRect(0, 0, main.GameBase.getWidth()+1, main.GameBase.getHeight()+1);
		g.setColor(oldCol);
		g.setDrawMode(Graphics.MODE_NORMAL);
	}
	
	public static void setColor(Color newCol) {
		col = newCol;
	}
	
	public static void setColor(Color newCol, float t) {
		colAnim = new Transition(col, newCol, t);
	}
}
