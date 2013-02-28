package main.particles;

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
import java.util.ArrayList;

import main.ControlManager;
import main.particles.SparkEmitter.Spark;

<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
import org.newdawn.slick.Color;

import org.lwjgl.util.vector.*;

public class SparkEmitter_FireMed extends SparkEmitter{
<<<<<<< HEAD
<<<<<<< HEAD
	
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
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	class FireSpark extends SparkEmitter.SparkBase{

		public FireSpark(Vector2f pos, Vector2f vel, float life, String type) {
			super.pos = pos;
			super.vel = vel;
			super.life = 3000;
			super.vel.x *= (float)(Math.random()-0.5) * 0.01f;
			super.vel.y *= (float)Math.random();
			super.type = type;
			col = Color.darkGray;
		}
		
		public void update() {
			super.setColor(new Color(
					col.r * (life/30),
					col.g * (life/40),
					col.b * (life/70),
					col.a * (life/35)*(float)(1-Math.random()*0.01)));
			super.update();
		}
		
		public void move() {
			super.randAng += ControlManager.getDelta() * 0.5f;
			super.vel.x += ControlManager.getDelta() * Math.cos(randAng) * 0.001f;
			super.vel.y += ControlManager.getDelta() * ((Math.sin(randAng) < 0)? Math.sin(randAng)*0.0001f : 0);
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

			super.move();
		}
	}
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

	public void createSparksAt(Vector2f newpos, int amt) {
		eamt = amt;
		this.pos = newpos;
		for(int i = 0; i < amt; i++) {
			float ang = (float)((startAng + (Math.random()-0.5)*2*angWidth)*Math.PI/180);
			float randVelFactor = (float)Math.random();
			Vector2f velVec = new Vector2f((float)Math.cos(ang), (float)Math.sin(ang));
			velVec.scale(vel * randVelFactor);
			sparks.add(new Spark(pos, velVec, -Math.abs(0.03f * (float)Math.sin(ang)) - 0.01f, type));
			sparks.get(i).setColor(col);
		}
	}
	
	public void update() {
		super.update();
	}
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
}
