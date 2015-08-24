package graphics;

public interface AvatarInterface{
	
	public void moveTorso(int newX, int newY);
	public void moveArms(int newX, int newY);
	public void moveLegs(int newX, int newY);
	public void move(int newX, int newY);
	public void moveHead(int newX, int newY);
	public void moveText(int newX, int newY);
	public Angle getArms();
	public Angle getLegs();
	public Line getTorso();
	public StringShape getString();
	public ImageShape getHead();

}
