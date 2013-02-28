package main.ai;

public abstract class NodeBase {
	private int x, y;
	private boolean blocked;
	
	public int getX() {return x;}
	public int getY() {return y;}
	public boolean isBlocked() {return blocked;}
	
	public void setX(int nx) {x = nx;}
	public void setY(int ny) {y = ny;}
	public void setBlocked(boolean nblocked) {blocked = nblocked;}
	
<<<<<<< HEAD
	@Override
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public String toString() {
		return "Node ["+x+", "+y+"] "+(blocked?"[obstruct]":"[free]");
	}
}
