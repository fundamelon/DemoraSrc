package main.map;

import main.AudioManager;
import main.ControlManager;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Door extends EnvObject {
	private Animation anim;
	private Rectangle activeBounds, inactiveBounds;
	private boolean open = false;
	public Door(int x, int y, int w, int h, String type) {
		pos.x = x;
		pos.y = y;
		activeBounds = new Rectangle(pos.x, pos.y-8, w, 10);
		inactiveBounds = new Rectangle(0,0,0,0);
		imgOffset.y = -64;
		
		try {
			this.anim = new Animation(new SpriteSheet("lib/img/env/"+type+".png", w, h), 100);
			anim.stop();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		addToTable(this);
	}
	
	public void update() {
		
		this.bounds = (anim.getFrame()>1 ? inactiveBounds : activeBounds);
		if(action.equals("open")) {
			//open animation
			if(anim.getFrame()!=anim.getFrameCount()-1) {
				if(anim.isStopped() || anim.getFrame() == 0) {
					anim.start();
					anim.setPingPong(true);
					anim.stopAt(anim.getFrameCount()-1);
				//	if(!open)
				//		AudioManager.playSound("door01_open", 1f, 0.3f);
				}
			}
			open = true;
		} else {
			//close animation
			if(anim.getFrame()!=0) {
				if(anim.isStopped() || anim.getFrame() == anim.getFrameCount()-1) {
					anim.setPingPong(true);
					anim.start();
					anim.setCurrentFrame(anim.getFrameCount()-1);
					anim.stopAt(0);
				}
			}
		}
		if(anim.getFrame() == 0) open = false;
		anim.update((long)ControlManager.getDelta());
	//	System.out.println(anim.toString());
	}
	
	public float getY() {
		return pos.y-32;
	}
	
	public void open() {
		this.action = "open";
		this.open = true;
	}
	
	public void close() {
		this.action = "close";
		this.open = false;
	}
	
	public Image getImg() {
		return anim.getCurrentFrame();
	}
}
