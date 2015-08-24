package animation;

import graphics.Avatar;
import util.annotations.Tags;

@Tags({"animator"})
public class ScreenAnimator{
	
	Avatar avatar;
	int xstep, ystep;
	BroadcastingClearanceManager sequencer;

	public ScreenAnimator(Avatar knight, int newX, int newY){
		avatar = knight;
		xstep = (newX/50);
		ystep = (newY/50);
	}
	
	public ScreenAnimator(Avatar knight, int newX, int newY, BroadcastingClearanceManager newSequencer){
		avatar = knight;
		xstep = (newX/50);
		ystep = (newY/50);
		sequencer = newSequencer;
	}
}
