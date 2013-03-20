package main.particles;

import org.newdawn.slick.Color;

import org.lwjgl.util.vector.*;

public class SparkEmitter_FireMed extends SparkEmitter{
	
	@Override
	public void createSparksAt(Vector2f newpos, int amt, int trailLength) {
		eamt = amt;
		pos = newpos;
		col = Color.yellow;
		for(int i = 0; i < amt; i++) {
			float randVelFactor = (float)Math.random()/2 + 0.5f;
			float ang = (float)((Math.random() * angWidth) - angWidth/2) + startAng - 180;
			ang *= Math.PI/180;
			Vector2f velVec = new Vector2f((float)Math.cos(ang), (float)Math.sin(ang));
			velVec.scale(vel * randVelFactor);
			sparks.add(new FireSpark(pos, velVec, life, type));
			sparks.get(i).setColor(col);
			sparks.get(i).setTrails(trailLength);
		}
	}
	
	class FireSpark extends SparkEmitter.SparkBase{
		Color col = Color.white;
		
		public FireSpark(Vector2f pos, Vector2f vel, float life, String type) {
			super.pos = new Vector2f(pos.x, pos.y);
			super.vel = new Vector2f(vel.x, vel.y);
			super.life = life;
		//	super.vel.x *= (float)(Math.random()-0.5) * 0.01f;
		//	super.vel.y *= (float)Math.random();
			super.type = type;
		}
		
		@Override
		public void update() {
			super.setColor(new Color(
					this.col.r * (life/0.7f),
					this.col.g * (life/0.4f),
					this.col.b * (life/0.9f),
					this.col.a * (life/0.35f)*(float)(1-Math.random()*0.01)));
			super.update();
		}
		
		@Override
		public void move() {
		//	super.randAng += ControlManager.getDelta() * 0.1f * (Math.random()/2+0.5f);
		//	super.vel.x += ControlManager.getDelta() * Math.cos(randAng) * 0.001f;
		//	super.vel.y += ControlManager.getDelta() * ((Math.sin(randAng) < 0)? Math.sin(randAng)*0.0001f : 0);

			super.move();
		}
	}
}
