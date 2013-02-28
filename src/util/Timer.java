package util;

public class Timer {
	private String name = null;
	private long startTime = 0;
	private long curTime = 0;
	private long limit = 0;
<<<<<<< HEAD
<<<<<<< HEAD
	private boolean running = false;
	private boolean repeat = false;
	private boolean override = false;
	public Timer(String name) {
		this.name = name;
		startTime = System.currentTimeMillis();
		curTime = startTime;
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	private boolean running = true;
	private boolean repeat = false;
	public Timer(String name) {
		this.name = name;
		startTime = System.nanoTime();
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	public Timer(String name, long time) {
		this(name);
		this.limit = time;
	}
	
	public Timer(String name, long time, boolean repeat) {
		this(name, time);
		this.repeat = repeat;
	}
	
	public void update() {
<<<<<<< HEAD
<<<<<<< HEAD
		if(running) curTime = System.currentTimeMillis();
		if(completed()) {
			if(!repeat) {
				stop();
			} else {
				reset();
			}
		}
		
=======
		if(running) curTime = System.nanoTime();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		if(running) curTime = System.nanoTime();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}	
	
	public void start() {
		running = true;
<<<<<<< HEAD
<<<<<<< HEAD
		override = false;
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	public void restart() {
		reset();
		start();
	}
	
	public void stop() {
		running = false;
	}
	
	public void reset() {
<<<<<<< HEAD
<<<<<<< HEAD
		startTime = System.currentTimeMillis();
		curTime = startTime;
=======
		startTime = curTime;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		startTime = curTime;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
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
	
	public String getName() {
		return name;
	}
	
	public boolean completed() {
<<<<<<< HEAD
<<<<<<< HEAD
		return timeElapsed() >= limit || override;
	}

	public void finish() {
		stop();
		override = true;
	}

	public void setLength(int newLength) {
		limit = newLength;
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		if(!repeat) {
			running = false;
			return curTime >= startTime + limit;
		} else {
			reset();
			return true;
		}
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
}