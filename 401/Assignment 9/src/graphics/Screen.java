package graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Bridge Sceen"})
public class Screen implements ScreenInterface{

	Line leftGorge, rightGorge;
	Rectangle rectangle;
	Circle guardSpot, avatarSpot;

	public Avatar arthur = new Avatar("arthur.jpg",50,100);
	public Avatar galahad = new Avatar("galahad.jpg",150,100);
	public Avatar guard = new Avatar("guard.jpg",860,370);
	public Avatar lancelot = new Avatar("lancelot.jpg",150,250);
	public Avatar robin = new Avatar("robin.jpg",50,250);
	Avatar hotseat;

	Boolean knightSpot;
	Boolean knightTurn = false;
	
	int yFall = 100, xFall = 950;

	public Screen (){
		
		avatarSpot = new Circle(725, 400, 75, 75);
		guardSpot = new Circle(820, 400, 75, 75);
		leftGorge = new Line(900,0, Math.PI/2, 1000);
		rightGorge = new Line(1050,0, Math.PI/2, 1000);
		rectangle = new Rectangle(900,400,150,75);
		knightSpot = false;
	}

	@Tags({"Approach"})
	public void Approach(Avatar character){
		if (knightSpot == false){
			character.move(760-character.getX(), 370-character.getY());
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

	public Circle getAvatarSpot(){return avatarSpot;}
	public Circle getGuardSpot(){return guardSpot;}

	public Line getLeftGorge(){return leftGorge;}
	public Line getRightGorge(){return rightGorge;}

	public Rectangle getRectangle(){return rectangle;}
}