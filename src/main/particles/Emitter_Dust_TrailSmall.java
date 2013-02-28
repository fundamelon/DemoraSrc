package main.particles;
<<<<<<< HEAD
<<<<<<< HEAD
import org.newdawn.slick.Image;

public class Emitter_Dust_TrailSmall extends ParticleEmitter {
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
import main.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.particles.*;

public class Emitter_Dust_TrailSmall implements ParticleEmitter {
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	/** The x coordinate of the center of the effect */
	private int x;
	/** The y coordinate of the center of the effect */
	private int y;
	
<<<<<<< HEAD
<<<<<<< HEAD
	private int z;
	
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	/** The particle emission rate */
	private int interval = 80;
	/** Time til the next particle */
	private int timer;
	/** The size of the initial particles */
	private float size = 10f;
	
	/** Timer controlling particle variation */
	private int variation_timer = 0;
	
	private boolean enabled = false;
	
	/**
	 * Create a dust effect at 0,0
	 */
	public Emitter_Dust_TrailSmall() {
	}

	/**
	 * Create a dust effect at x,y
	 * 
	 * @param x2 The x coordinate of the effect
	 * @param y2 The y coordinate of the effect
	 */
	public Emitter_Dust_TrailSmall(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}
	
	public Emitter_Dust_TrailSmall(float x, float y) {
		this.x = (int)x;
		this.y = (int)y;
	}

	/**
	 * Create a dust effect at x,y
	 * 
	 * @param x The x coordinate of the effect
	 * @param y The y coordinate of the effect
	 * @param size The size of the particle being pumped out
	 */
	public Emitter_Dust_TrailSmall(int x, int y, float size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#update(org.newdawn.slick.particles.ParticleSystem, int)
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public void update(ParticleSystem system, int delta) {
		timer -= delta;
		variation_timer += delta;
		if (timer <= 0 && isEnabled()) {
			timer = interval;
			Particle p = system.getNewParticle(this, 4000);
			p.setColor(0.8f, 0.6f, 0.6f, 0.4f);
<<<<<<< HEAD
<<<<<<< HEAD
			p.setPosition(x + (float)(Math.random() * 2f), y, 15);
=======
			p.setPosition(x + (float)(Math.random() * 2f), y);
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
			p.setPosition(x + (float)(Math.random() * 2f), y);
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			p.setSize(size*3f);
			float vx = (float) (-0.005f + (Math.random() * 0.01f)) + (float)Math.sin(variation_timer * 0.0003 + Math.sin(variation_timer*0.001))*0.007f;
			float vy = (float) (-(Math.random() * 0.05f)-0.03f) * 0.15f;
			
			
			p.setVelocity(vx/2, vy/2, 2f);
		}
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#updateParticle(org.newdawn.slick.particles.Particle, int)
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public void updateParticle(Particle particle, int delta) {
		if (particle.getLife() > 1000) {
			particle.adjustSize(0.06f * delta);
			particle.adjustColor(0, 0, 0, delta*-0.0002f);
			
		} else {
			particle.adjustSize(0.02f * delta * (size / 40.0f));
			particle.adjustColor(0,  0, 0, delta*-0.08f);
		}
		float c = 0.002f * delta;
		float vx = (float)Math.sin(variation_timer * 0.0003 + Math.sin(variation_timer*0.001))*0.000001f;
		float vy = 0;
		
		particle.adjustVelocity(vx, vy);
		
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public void setPos(float nx, float ny) {
		x = (int)nx;
		y = (int)ny;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#isEnabled()
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#setEnabled(boolean)
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#completed()
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public boolean completed() {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#useAdditive()
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public boolean useAdditive() {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#getImage()
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public Image getImage() {
		return null;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#usePoints(org.newdawn.slick.particles.ParticleSystem)
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public boolean usePoints(ParticleSystem system) {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#isOriented()
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public boolean isOriented() {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#wrapUp()
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public void wrapUp() {
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#resetState()
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
	public void resetState() {
	}

	@Override
=======
	public void resetState() {
	}

>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
	public void resetState() {
	}

>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public float getBrightness() {
		return 0.4f + (float)((Math.sin(variation_timer * 0.003f) + Math.cos(variation_timer * 0.01f)) * 0.01f);
	}
}
