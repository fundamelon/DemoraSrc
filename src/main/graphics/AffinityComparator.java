package main.graphics;

import java.util.Comparator;

public class AffinityComparator implements Comparator<RenderCandidate>  {
	@Override
	public int compare(RenderCandidate rc_a, RenderCandidate rc_b) {
		float a = rc_a.getPriority(), b = rc_b.getPriority();
		return (a>b ? 1 : (a==b ? 0 : -1));
	}

}
