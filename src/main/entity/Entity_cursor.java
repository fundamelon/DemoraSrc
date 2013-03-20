package main.entity;

import main.ControlManager;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;

import util.Util;

public class Entity_cursor extends Entity_mobile implements Entity {

	public Entity_cursor() {
		bounds = new Rectangle(0, 0, 16, 16);
		pos.x = Util.toWorldX(ControlManager.getMouseX() - 8);
		pos.y = Util.toWorldY(ControlManager.getMouseY() - 8);
		name = "cursor";
	}
	@Override
	public float getAng() {
		return 0;
	}
	
	@Override
	public void update() {
		this.pos.x = Util.toWorldX(ControlManager.getMouseX() - 8);
		this.pos.y = Util.toWorldY(ControlManager.getMouseY() - 8);
		updateBounds();
	}

	@Override
	public void init(float nx, float ny, boolean tilewise) {
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
