package graphics;

import util.annotations.Tags;
import util.annotations.Visible;

@Tags({"Bridge Sceen"})
public class Screen implements ScreenInterface{

	Line torsoArthur, torsoGalahad, torsoGuard, torsoLancelot, torsoRobin;
	Angle arthurArms, arthurLegs, galahadArms, galahadLegs, guardArms, guardLegs, lancelotArms, lancelotLegs, robinArms, robinLegs;
	StringShape arthurText, galahadText, guardText, lancelotText, robinText;
	Point pointArthur, pointGalahad, pointGuard, pointLancelot, pointRobin;
	Point pointArthurBottom, pointGalahadBottom, pointGuardBottom, pointLancelotBottom, pointRobinBottom;
	PolarPoint polarPointArms, polarPointLegs, polarPointTorso;
	ImageShape arthur, galahad, guard, lancelot, robin;

	public Screen (){
		pointArthur = new Point(45,115);
		pointArthurBottom = new Point(45, 160);
		pointGalahad = new Point(145, 115);
		pointGalahadBottom = new Point(145, 160);
		pointGuard = new Point(245, 115);
		pointGuardBottom = new Point (245, 160);
		pointLancelot = new Point(345, 115);
		pointLancelotBottom = new Point(345, 160);
		pointRobin = new Point(445, 115);
		pointRobinBottom = new Point(445, 160);
		
		polarPointTorso = new PolarPoint(Math.PI/2,45);
		polarPointArms = new PolarPoint(Math.PI/4, 45);
		polarPointLegs = new PolarPoint(Math.PI/4, 45);
		
		arthurArms = new Angle(pointArthur, polarPointArms);
		arthurLegs = new Angle(pointArthurBottom, polarPointLegs);
		galahadArms = new Angle(pointGalahad, polarPointArms);
		galahadLegs = new Angle(pointGalahadBottom, polarPointLegs);
		guardArms = new Angle(pointGuard, polarPointArms);
		guardLegs = new Angle(pointGuardBottom, polarPointLegs);
		lancelotArms = new Angle(pointLancelot, polarPointArms);
		lancelotLegs = new Angle(pointLancelotBottom, polarPointLegs);
		robinArms = new Angle(pointRobin, polarPointArms);
		robinLegs = new Angle(pointRobinBottom, polarPointLegs);
		
		arthurText = new StringShape("Arthur",45,20);
		galahadText = new StringShape("Galahad",145,20);
		guardText = new StringShape("Guard",245,20);
		lancelotText = new StringShape("Lancelot",345,20);
		robinText = new StringShape("Robin",445,20);

		torsoArthur = new Line(pointArthur, polarPointTorso);
		torsoGalahad = new Line(pointGalahad, polarPointTorso);
		torsoGuard = new Line(pointGuard, polarPointTorso);
		torsoLancelot = new Line(pointLancelot, polarPointTorso);
		torsoRobin = new Line(pointRobin, polarPointTorso);
		
		arthur = new ImageShape("arthur.jpg", 30, 50);
		galahad = new ImageShape("galahad.jpg", 130, 50);
		guard = new ImageShape("guard.jpg", 230, 50);
		lancelot = new ImageShape("lancelot.jpg", 330, 50);
		robin = new ImageShape("robin.jpg", 430, 50);

	}

	public Line getTorsoArthur(){return torsoArthur;}
	public Line getTorsoGalahad(){return torsoGalahad;}
	public Line getTorsoGuard(){return torsoGuard;}
	public Line getTorsoLancelot(){return torsoLancelot;}
	public Line getTorsoRobin(){return torsoRobin;}
	
	public StringShape getArthurText(){return arthurText;}
	public StringShape getGalahadText(){return galahadText;}
	public StringShape getGuardText(){return guardText;}
	public StringShape getLancelotText(){return lancelotText;}
	public StringShape getRobinText(){return robinText;}
	
	public ImageShape getArthur(){return arthur;}
	public ImageShape getGalahad(){return galahad;}
	public ImageShape getGuard(){return guard;}
	public ImageShape getLancelot(){return lancelot;}
	public ImageShape getRobin(){return robin;}
	
	public Angle getArthurArmAngle(){return arthurArms;}
	public Angle getArthurLegAngle(){return arthurLegs;}
	public Angle getGalahadArmAngle(){return galahadArms;}
	public Angle getGalahadLegAngle(){return galahadLegs;}
	public Angle getGuardArmAngle(){return guardArms;}
	public Angle getGuardLegAngle(){return guardLegs;}
	public Angle getLancelotArmAngle(){return lancelotArms;}
	public Angle getLancelotLegAngle(){return lancelotLegs;}
	public Angle getRobinArmAngle(){return robinArms;}
	public Angle getRobinLegAngle(){return robinLegs;}

	@Visible(false)
	public Point getArthurTorsoLocation(){return pointArthur;}
	
	@Visible(false)
	public Point getGalahadTorsoLocation(){return pointGalahad;}
	
	@Visible(false)
	public Point getGuardTorsoLocation(){return pointGuard;}
	
	@Visible(false)
	public Point getLancelotTorsoLocation(){return pointLancelot;}
	
	@Visible(false)
	public Point getRobinTorsoLocation(){return pointRobin;}
	
	@Visible(false)
	public Point getArthurTorsoBottomLocation(){return pointArthurBottom;}
	
	@Visible(false)
	public Point getGalahadTorsoBottomLocation(){return pointGalahadBottom;}
	
	@Visible(false)
	public Point getGuardTorsoBottomLocation(){return pointGuardBottom;}
	
	@Visible(false)
	public Point getLancelotTorsoBottomLocation(){return pointLancelotBottom;}
	
	@Visible(false)
	public Point getRobinTorsoBottomLocation(){return pointRobinBottom;}
	
	public void moveLine(Point person, int newX, int newY){
		person.setX(person.getX() + newX);
		person.setY(person.getY() + newY);
	}

	public void moveText(StringShape string, int newX, int newY){
		string.setX(string.getX()+newX);
		string.setY(string.getY()+newY);
	}

	public void moveImage(ImageShape image, int newX, int newY){
		image.setX(image.getX()+newX);
		image.setY(image.getY()+newY);
	}

	public void moveAll(int newX, int newY){
		pointArthur = new Point(newX, newY);
		pointGalahad = new Point(newX, newY);
		pointGuard = new Point(newX, newY);
		pointLancelot = new Point(newX, newY);
		pointRobin = new Point(newX, newY);
		pointArthurBottom = new Point(newX, newY);
		pointGalahadBottom = new Point(newX, newY);
		pointGuardBottom = new Point(newX, newY);
		pointLancelotBottom = new Point(newX, newY);
		pointRobinBottom = new Point(newX, newY);
		
		moveLine(pointArthur, newX, newY);
		moveLine(pointGalahad, newX, newY);
		moveLine(pointGuard, newX, newY);
		moveLine(pointLancelot, newX, newY);
		moveLine(pointRobin, newX, newY);
		moveLine(pointArthurBottom, newX, newY);
		moveLine(pointGalahadBottom, newX, newY);
		moveLine(pointGuardBottom, newX, newY);
		moveLine(pointLancelotBottom, newX, newY);
		moveLine(pointRobinBottom, newX, newY);
		
		moveText(arthurText, newX, newY);
		moveText(galahadText, newX, newY);
		moveText(guardText, newX, newY);
		moveText(lancelotText, newX, newY);
		moveText(robinText, newX, newY);
		
		moveImage(arthur, newX, newY);
		moveImage(galahad, newX, newY);
		moveImage(guard, newX, newY);
		moveImage(lancelot, newX, newY);
		moveImage(robin, newX, newY);

	}

	public void move(Point person, int newX, int newY){
		person.setX(person.getX() + newX);
		person.setY(person.getY() + newY);
	}
}
