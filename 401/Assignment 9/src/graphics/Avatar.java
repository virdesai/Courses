package graphics;

import util.annotations.IsAtomicShape;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@IsAtomicShape(false)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class Avatar extends Location implements AvatarInterface{

	Angle arms, legs;
	Line torso;
	StringShape text;
	String test = "";
	public String avatar;
	ImageShape head;

	public Avatar(String character, int newX, int newY){
		super(newX, newY);
		avatar = String.valueOf(character);
		arms = new Angle(x, y, Math.PI/4, 45);
		legs = new Angle(x, y+45, Math.PI/4, 45);
		torso = new Line(x, y, Math.PI/2, 45);
		head = new ImageShape(character.toLowerCase(), x-20, y-65);
		text = new StringShape(test, x-20, y-85);
	}

	public Angle getArms(){return arms;}
	public Angle getLegs(){return legs;}
	public Line getTorso(){return torso;}
	public void setString(String newString){ text.setText(newString);}
	public StringShape getString(){return text;}
	public ImageShape getHead(){return head;}

	public void moveTorso(int newX, int newY){
		torso.setX(newX);
		torso.setY(newY);
	}
	public void moveArms(int newX, int newY){
		arms.setLeftLine(newX, newY, Math.PI/4, 45);
		arms.setRightLine(newX, newY, Math.PI/4, 45);
	}
	public void moveLegs(int newX, int newY){
		legs.setLeftLine(newX, newY+45, Math.PI/4, 45);
		legs.setRightLine(newX, newY+45, Math.PI/4, 45);
	}
	public void moveHead(int newX, int newY){
		head.setX(newX-20);
		head.setY(newY-65);
	}
	public void moveText(int newX, int newY){
		text.setX(newX-20);
		text.setY(newY-85);
	}
	public void move(int newX, int newY){
		setX(getX()+newX);
		setY(getY()+newY);
		moveTorso(x, y);
		moveArms(x, y);
		moveLegs(x, y);
		moveHead(x, y);
		moveText(x, y);
	}
}