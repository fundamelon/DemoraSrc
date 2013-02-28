package main.particles;

import org.newdawn.slick.Image;

/**
 * An emitter is responsible for producing the particles and controlling them during
 * their life. An implementation of this interface can be considered a particle
 * effect.
 *
 * @author kevin
 */
public abstract class ParticleEmitter {
	public float x, y, z;
	public float vel_x, vel_y, vel_z;
	/**
	 * Update the emitter, produce any particles required by requesting
	 * them from the particle system provided.
	 * 
	 * @param system The particle system used to create particles
	 * @param delta The amount of time in milliseconds since last emitter update
	 */
	public abstract void update(ParticleSystem system, int delta);
	
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public void setPos(float x, float y, float z) {
		setPos(x, y);
		this.z = z;
	}
	
	public abstract float getBrightness();

	/**
	 * Check if this emitter has completed it's cycle
	 * 
	 * @return True if the emitter has completed it's cycle
	 */
	public abstract boolean completed();
	
	/**
	 * Wrap up the particle emitter. This means the emitter will no longer produce
	 * particles and will be marked as completed once the particles have expired
	 */
	public abstract void wrapUp();
	
	/**
	 * Update a single particle that this emitter produced
	 * 
	 * @param particle The particle to be updated
	 * @param delta The amount of time in millisecond since last particle update
	 */
	public abstract void updateParticle(Particle particle, int delta);
	
	/**
	 * Check if the emitter is enabled 
	 * 
	 * @return True if the emitter is enabled
	 */
	public abstract boolean isEnabled();
	
	/**
	 * Indicate whether the emitter should be enabled
	 * 
	 * @param enabled True if the emitter should be enabled
	 */
	public abstract void setEnabled(boolean enabled);
	
	/**
	 * Check if this emitter should use additive blending
	 * 
	 * @return True if the emitter should use the right blending
	 */
	public abstract boolean useAdditive();
	
	/**
	 * Get the image to draw for each particle
	 * 
	 * @return The image to draw for each particle
	 */
	public abstract Image getImage();

	/**
	 * Check if the particles produced should maintain orientation
	 * 
	 * @return True if the particles produced should maintain orientation
	 */
	public abstract boolean isOriented();
	
	/**
	 * Check if this emitter should use points based on it's own settings 
	 * and those of the particle system
	 * 
	 * @param system The particle system to cross check agianst
	 * @return True if we should use points
	 */
	public abstract boolean usePoints(ParticleSystem system);
	
	/**
	 * Clear the state of emitter back to default
	 */
	public abstract void resetState();

}
