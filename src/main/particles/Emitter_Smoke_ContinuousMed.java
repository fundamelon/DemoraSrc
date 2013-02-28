package main.particles;
import org.newdawn.slick.Image;

public class Emitter_Smoke_ContinuousMed extends ParticleEmitter {
	/** The x coordinate of the center of the fire effect */
	private int x;
	/** The y coordinate of the center of the fire effect */
	private int y;
	
	/** The particle emission rate */
	private int interval = 30;
	/** Time til the next particle */
	private int timer;
	/** The size of the initial particles */
	private float size = 1f;
	
	/** Timer controlling particle variation */
	private int variation_timer = 0;
	/**
	 * Create a default fire effect at 0,0
	 */
	public Emitter_Smoke_ContinuousMed() {
	}

	/**
	 * Create a default fire effect at x,y
	 * 
	 * @param x The x coordinate of the fire effect
	 * @param y The y coordinate of the fire effect
	 */
	public Emitter_Smoke_ContinuousMed(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Create a default fire effect at x,y
	 * 
	 * @param x The x coordinate of the fire effect
	 * @param y The y coordinate of the fire effect
	 * @param size The size of the particle being pumped out
	 */
	public Emitter_Smoke_ContinuousMed(int x, int y, float size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#update(org.newdawn.slick.particles.ParticleSystem, int)
	 */
	@Override
	public void update(ParticleSystem system, int delta) {
		timer -= delta;
		variation_timer += delta;
		if (timer <= 0) {
			timer = interval;
			Particle p = system.getNewParticle(this, 3000);
			p.setColor(0.8f, 0.8f, 0.8f, 0.07f + (float)(Math.random()*0.01f));
			p.setPosition(x + (float)(Math.random() * 10f), y);
			p.setSize(size*2f);
			float vx = (float) (-0.005f + (Math.random() * 0.01f)) + (float)Math.sin(variation_timer * 0.0003 + Math.sin(variation_timer*0.001))*0.007f;
			float vy = (float) (-(Math.random() * 0.05f)-0.03f);
			
			
			p.setVelocity(vx/2, vy/2, 2f);
		}
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#updateParticle(org.newdawn.slick.particles.Particle, int)
	 */
	@Override
	public void updateParticle(Particle particle, int delta) {
		if (particle.getLife() > 600) {
			particle.adjustSize(0.02f * delta);
			particle.adjustColor(0, 0, 0, delta*-0.00003f);
			
		} else {
			particle.adjustSize(0.02f * delta * (size / 40.0f));
			particle.adjustColor(0,  0, 0, delta*-0.02f);
		}
		float c = 0.002f * delta;
		float vx = (float)Math.sin(variation_timer * 0.0003 + Math.sin(variation_timer*0.001))*0.000001f;
		float vy = 0;
		
		particle.adjustVelocity(vx, vy);
		
	}
	
	@Override
	public void setPos(float nx, float ny) {
		x = (int)nx;
		y = (int)ny;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#completed()
	 */
	@Override
	public boolean completed() {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#useAdditive()
	 */
	@Override
	public boolean useAdditive() {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#getImage()
	 */
	@Override
	public Image getImage() {
		return null;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#usePoints(org.newdawn.slick.particles.ParticleSystem)
	 */
	@Override
	public boolean usePoints(ParticleSystem system) {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#isOriented()
	 */
	@Override
	public boolean isOriented() {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#wrapUp()
	 */
	@Override
	public void wrapUp() {
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#resetState()
	 */
	@Override
	public void resetState() {
	}

	@Override
	public float getBrightness() {
		return 0.4f + (float)((Math.sin(variation_timer * 0.003f) + Math.cos(variation_timer * 0.01f)) * 0.01f);
	}
}
