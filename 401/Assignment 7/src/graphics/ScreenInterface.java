package graphics;

public interface ScreenInterface {
	
	public void Failed();
	public void Passed();
	public void Say(String dialog);
	public void Approach(Avatar character);
	public void move(Avatar character, int newX, int newY);
	
	public Avatar getArthur();
	public Avatar getGalahad();
	public Avatar getGuard();
	public Avatar getLancelot();
	public Avatar getRobin ();

	public Circle getGuardSpot();
	public Circle getAvatarSpot();

	public Line getLeftGorge();
	public Line getRightGorge();

	public PolarPoint getPolarGorge();

	public Point getLeftPoint();
	public Point getRightPoint();

	public Rectangle getRectangle();
	
}
