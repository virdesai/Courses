package graphics;

import util.annotations.IsAtomicShape;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@IsAtomicShape(false)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class Avatar implements AvatarInterface{
	
	Angle arms, legs;
	Line torso;
	Point pointTop, pointBottom;
	StringShape text;
	String test = "test";
	String avatar;
	ImageShape head;
	PolarPoint polarPointTorso = new PolarPoint(Math.PI/2,45);
	PolarPoint polarPointArms = new PolarPoint(Math.PI/4, 45);
	PolarPoint polarPointLegs = new PolarPoint(Math.PI/4, 45);

	public Avatar(String character, int newX, int newY){
		pointTop = new Point(newX, newY);
		pointBottom = new Point(newX, newY+45);
		avatar = String.valueOf(character);
		arms = new Angle(pointTop, polarPointArms);
		legs = new Angle(pointBottom, polarPointLegs);
		torso = new Line(pointTop, polarPointTorso);
		head = new ImageShape(character, newX-20, newY-65);
		text = new StringShape(test, newX-20, newY-85);

	}

	public Angle getArms(){return arms;}
	public Angle getLegs(){return legs;}
	public Line getTorso(){return torso;}
	public void setString(String newString){
		text.setText(newString);
	}
	public StringShape getString(){return text;}
	public ImageShape getHead(){return head;}

	public void moveTorso(int newX, int newY){
		pointTop = new Point(newX, newY);
		torso = new Line(pointTop, polarPointTorso);
	}

	public void moveArms(int newX, int newY){
		pointTop = new Point(newX, newY);
		arms = new Angle(pointTop, polarPointArms);
	}

	public void moveLegs(int newX, int newY){
		pointBottom = new Point(newX, newY+45);
		legs = new Angle(pointBottom, polarPointLegs);
	}

	public void moveHead(int newX, int newY){
		head = new ImageShape(avatar, newX-20, newY-65);
	}

	public void moveText(int newX, int newY){
		text = new StringShape(test, newX-20, newY-85);
	}

	public void move(int newX, int newY){
		moveTorso(newX, newY);
		moveArms(newX, newY);
		moveLegs(newX, newY);
		moveHead(newX, newY);
		moveText(newX, newY);
	}

}
