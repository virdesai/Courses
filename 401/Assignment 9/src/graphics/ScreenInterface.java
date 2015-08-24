package graphics;

public interface ScreenInterface {
	
	public void Failed();
	public void Passed();
	public void Say(String dialog);
	public void Approach(Avatar character);
	
	public Avatar getArthur();
	public Avatar getGalahad();
	public Avatar getGuard();
	public Avatar getLancelot();
	public Avatar getRobin ();

	public Circle getGuardSpot();
	public Circle getAvatarSpot();

	public Line getLeftGorge();
	public Line getRightGorge();

	public Rectangle getRectangle();
	
}
