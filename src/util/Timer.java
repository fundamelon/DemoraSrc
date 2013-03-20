package util;

public class Timer {
	private String name = null;
	private long startTime = 0;
	private long curTime = 0;
	private long limit = 0;
	private boolean running = false;
	private boolean repeat = false;
	private boolean override = false;
	public Timer(String name) {
		this.name = name;
		startTime = System.currentTimeMillis();
		curTime = startTime;
		TimerSet.addTimer(this);
	}
	
	public Timer(String name, int time) {
		this(name, (long)time);
	}
	
	public Timer(String name, long time) {
		this(name);
		this.limit = time;
	}
	
	public Timer(String name, long time, boolean repeat) {
		this(name, time);
		this.repeat = repeat;
	}
	
	public Timer(String s, float t) {
		this(s, (long)t);
	}

	public void update() {
		if(running) curTime = System.currentTimeMillis();
		if(completed()) {
			if(!repeat) {
				stop();
			} else {
				reset();
			}
		}
		
	}	
	
	public void start() {
		running = true;
		override = false;
	}
	
	public void restart() {
		reset();
		start();
	}
	
	public void stop() {
		running = false;
	}
	
	public void reset() {
		startTime = System.currentTimeMillis();
		curTime = startTime;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setRepeat(boolean a) {
		repeat = a;
	}
	
	public long timeElapsed() {
		return curTime - startTime;
	}
	
	public long totalTime() {
		return limit;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean completed() {
		return timeElapsed() >= limit || override;
	}

	public void finish() {
		stop();
		override = true;
	}

	public void setLength(int newLength) {
		limit = newLength;
	}
}