package graphics;

public interface ScreenInterface {
	public StringShape getArthurText();
	public StringShape getGalahadText();
	public StringShape getGuardText();
	public StringShape getLancelotText();
	public StringShape getRobinText();
	
	public ImageShape getArthur();
	public ImageShape getGalahad();
	public ImageShape getGuard();
	public ImageShape getLancelot();
	
	public Point getArthurTorsoLocation();
	public Point getGalahadTorsoLocation();
	public Point getGuardTorsoLocation();
	public Point getLancelotTorsoLocation();
	public Point getRobinTorsoLocation();
	public Point getArthurTorsoBottomLocation();
	public Point getGalahadTorsoBottomLocation();
	public Point getGuardTorsoBottomLocation();
	public Point getLancelotTorsoBottomLocation();
	public Point getRobinTorsoBottomLocation();
	
	public Angle getArthurArmAngle();
	public Angle getArthurLegAngle();
	public Angle getGalahadArmAngle();
	public Angle getGalahadLegAngle();
	public Angle getGuardArmAngle();
	public Angle getGuardLegAngle();
	public Angle getLancelotArmAngle();
	public Angle getLancelotLegAngle();
	public Angle getRobinArmAngle();
	public Angle getRobinLegAngle();
	
	public void move(Point person, int newX, int newY);
	public void moveLine(Point person, int newX, int newY);
	public void moveText(StringShape string, int newX, int newY);
	public void moveImage(ImageShape image, int newX, int newY);

}
