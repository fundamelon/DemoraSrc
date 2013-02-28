package main.entity;

import main.ControlManager;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;

import util.Util;

public class Entity_cursor extends Entity_mobile implements Entity {

	public Entity_cursor() {
		bounds = new Rectangle(0, 0, 16, 16);
<<<<<<< HEAD
<<<<<<< HEAD
		pos.x = Util.toWorldX(ControlManager.getMouseX() - 8);
		pos.y = Util.toWorldY(ControlManager.getMouseY() - 8);
		name = "cursor";
=======
		pos.x = Util.toWorldX(ControlManager.getMouseX());
		pos.y = Util.toWorldY(ControlManager.getMouseY());
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		pos.x = Util.toWorldX(ControlManager.getMouseX());
		pos.y = Util.toWorldY(ControlManager.getMouseY());
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	@Override
	public float getAng() {
		return 0;
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
	public void update() {
		this.pos.x = Util.toWorldX(ControlManager.getMouseX() - 8);
		this.pos.y = Util.toWorldY(ControlManager.getMouseY() - 8);
		updateBounds();
	}

	@Override
	public void init(float nx, float ny, boolean tilewise) {
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public void update() {
		this.vel.x = Util.toWorldX(ControlManager.getMouseX() - this.pos.x);
		this.vel.y = Util.toWorldY(ControlManager.getMouseY() - this.pos.y);
		super.update();
	}

	@Override
	public void init(int nx, int ny, boolean tilewise) {
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		update();
	}

	@Override
	public Shape getBounds() {
		return bounds;
	}
	
	@Override
	public Image getImg() {
		return null;
	}

	@Override
	public Image getShadowCasterImg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean castShadows() {
		// TODO Auto-generated method stub
		return false;
	}

}
