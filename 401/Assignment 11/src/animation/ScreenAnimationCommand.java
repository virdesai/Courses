package animation;

import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"animating command"})
public class ScreenAnimationCommand implements Runnable{
	
	ScreenAnimator animator;
	
	public ScreenAnimationCommand(ScreenAnimator screenAnimator) {
		animator = screenAnimator;
	}

	public synchronized void run() {
		int i = 1;
		if (animator.sequencer != null){
			animator.sequencer.waitForProceed();
			while(true){
				while (i<50) {
					animator.avatar.move(animator.xstep, animator.ystep);
					ThreadSupport.sleep(50);
					i++;
		}
		}
		}else{
			while (i<50) {
				animator.avatar.move(animator.xstep, animator.ystep);
				ThreadSupport.sleep(50);
				i++;
			}
		}
	}
}