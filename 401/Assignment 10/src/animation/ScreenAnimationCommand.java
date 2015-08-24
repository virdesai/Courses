package animation;

import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"animating command"})
public class ScreenAnimationCommand implements Runnable{
	
	ScreenAnimator animator;
	
	public ScreenAnimationCommand(ScreenAnimator screenAnimator) {
		animator = screenAnimator;
	}

	public void run() {
		for (int i=1; i<50;) {
		animator.avatar.move(animator.xstep, animator.ystep);
		ThreadSupport.sleep(50);
		i++;
		}
	}

}
