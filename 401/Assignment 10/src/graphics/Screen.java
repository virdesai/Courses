package graphics;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Bridge Sceen"})
public class Screen implements ScreenInterface, PropertyListenerRegisterer{
	
	PropertyListenerInterface propertyListener = new PropertyListenerClass();

	Line leftGorge, rightGorge;
	Rectangle rectangle;
	Circle guardSpot, avatarSpot;

	public Avatar arthur = new Avatar("arthur.jpg",50,100);
	public Avatar galahad = new Avatar("galahad.jpg",150,100);
	public Avatar guard = new Avatar("guard.jpg",860,370);
	public Avatar lancelot = new Avatar("lancelot.jpg",150,250);
	public Avatar robin = new Avatar("robin.jpg",50,250);
	Avatar hotseat;

	Boolean knightSpot = false;
	Boolean knightTurn = false;
	
	int yFall = -250, xFall = 150;

	public Screen (){
		
		avatarSpot = new Circle(725, 400, 75, 75);
		guardSpot = new Circle(820, 400, 75, 75);
		leftGorge = new Line(900,0, Math.PI/2, 1000);
		rightGorge = new Line(1050,0, Math.PI/2, 1000);
		rectangle = new Rectangle(900,400,150,75);
	}

	@Tags({"Approach"})
	public void Approach(Avatar character){
		assert getKnightSpot() == false;
		character.move(760-character.getX(), 370-character.getY());
		hotseat = character;
		knightSpot = true;
		propertyListener.notifyAllListeners(new PropertyChangeEvent(this, "this", "Approach", knightSpot));
	}

	@Tags({"Say"})
	public void Say(String dialog){
		assert getKnightSpot();
			if (getKnightTurn()){
		hotseat.setString(dialog);
		knightTurn = false;
		propertyListener.notifyAllListeners(new PropertyChangeEvent(this, "this", "Say", knightTurn));
			}else {
		guard.setString(dialog);
		knightTurn = true;
		propertyListener.notifyAllListeners(new PropertyChangeEvent(this, "this", "Say", knightTurn));
			}
		//}
	}

	@Tags({"Passed"})
	public void Passed(){
		assert getKnightSpot();
		assert getKnightTurn();
		hotseat.move(375, 30);
		knightSpot = false;
		propertyListener.notifyAllListeners(new PropertyChangeEvent(this, "this", "Passed", knightSpot));
	}
	
	@Tags({"Failed"})
	public void Failed(){
		assert getKnightSpot();
		if (getGuardTurn()){
		guard.move(xFall, yFall);
		xFall += 25;
		yFall += 75;
			}else {
		hotseat.move(xFall, yFall);
		xFall += 25;
		yFall += 75;
		knightSpot = false;
		propertyListener.notifyAllListeners(new PropertyChangeEvent(this, "this", "Failed", knightSpot));
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
	
	public boolean getKnightSpot() {
		return knightSpot;
	}
	public boolean getKnightTurn() {
		return knightTurn;
	}
	public boolean getGuardTurn() {
		return knightTurn == false;
	}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListener.addElement(listener);
	}
}