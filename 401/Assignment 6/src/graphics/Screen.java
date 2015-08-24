package graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Bridge Sceen"})
public class Screen implements ScreenInterface{

	Line leftGorge, rightGorge;

	Point leftGorgePoint, rightGorgePoint;

	PolarPoint polarGorge;

	Rectangle rectangle;
	Circle guardSpot, avatarSpot;

	Avatar arthur, galahad, guard, lancelot, robin, hotseat;

	@Visible(true)
	Boolean knightSpot;
	@Visible(true)
	Boolean knightTurn = false;
	
	int yFall = 100, xFall = 950;

	public Screen (){

		avatarSpot = new Circle(725, 400, 75, 75);
		guardSpot = new Circle(825, 400, 75, 75);

		leftGorgePoint = new Point(900,0);
		rightGorgePoint = new Point (1050,0);
		polarGorge = new PolarPoint(Math.PI/2,1000);

		leftGorge = new Line(leftGorgePoint, polarGorge);
		rightGorge = new Line(rightGorgePoint, polarGorge);

		rectangle = new Rectangle(900,350,150,50);

		arthur = new Avatar("arthur.jpg",50,100);
		galahad = new Avatar("galahad.jpg",150,100);
		guard = new Avatar("guard.jpg",860,370);
		lancelot = new Avatar("lancelot.jpg",150,250);
		robin = new Avatar("robin.jpg",50,250);

		knightSpot = false;
	}

	@Tags({"Approach"})
	public void Approach(Avatar character){

		if (knightSpot == false){
			character.move(760, 370);
			hotseat = character;
			knightSpot = true;
		}

	}

	@Tags({"Say"})
	public void Say(String dialog){
		if (knightSpot){
			if (knightTurn){
				hotseat.setString(dialog);
				knightTurn = false;
			}else {
				guard.setString(dialog);
				knightTurn = true;
			}
		}
	}

	@Tags({"Passed"})
	public void Passed(){
		if (knightSpot){
			if (knightTurn != true){
				hotseat.move(1100, 370);
				knightSpot = false;
			}
		}
	}
	
	@Tags({"Failed"})
	public void Failed(){
		if (knightSpot){
			if (knightTurn){
				guard.move(xFall, yFall);
				xFall += 25;
				yFall += 75;
				knightTurn = false;
			}else {
				hotseat.move(xFall, yFall);
				xFall += 25;
				yFall += 75;
				knightSpot = false;
			}
		}
	}

	public Avatar getArthur(){return arthur;}
	public Avatar getGalahad(){return galahad;}
	public Avatar getGuard(){return guard;}
	public Avatar getLancelot(){return lancelot;}
	public Avatar getRobin (){return robin;}

	public Circle getGuardSpot(){return guardSpot;}
	public Circle getAvatarSpot(){return avatarSpot;}

	public Line getLeftGorge(){return leftGorge;}
	public Line getRightGorge(){return rightGorge;}

	@Visible(false)
	public PolarPoint getPolarGorge(){return polarGorge;}

	public Point getLeftPoint(){return leftGorgePoint;}
	public Point getRightPoint(){return rightGorgePoint;}

	public Rectangle getRectangle(){return rectangle;}

}