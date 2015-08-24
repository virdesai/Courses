package animation;

import util.annotations.ComponentWidth;
import util.annotations.Row;
import util.annotations.StructurePattern;

@StructurePattern("No Pattern")
public class BroadcastingClearanceManager {
	@Row(0)
    @ComponentWidth(100)
    public synchronized void proceed() {
        notify();
    }
    @Row(1)
    @ComponentWidth(100)
    public synchronized void proceedAll() {
        notifyAll();
    }
    public synchronized void waitForProceed() {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
