package main;

import graphics.Avatar;
import graphics.ImageShape;
import graphics.PropertyListenerClass;
import graphics.PropertyListenerInterface;
import graphics.Screen;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.annotations.Tags;

@SuppressWarnings("serial")
@Tags({"Inheriting Bridge Scene Painter"})
public class View extends Component implements PropertyChangeListener {

	PropertyListenerInterface propertyListener = new PropertyListenerClass();

	Screen screens;
	
	static ImageShape arthur, galahad, lancelot, guard, robin;

	public View(Screen screen){
		screens = screen;

		screens.getArthur().getArms().getLeftLine().addPropertyChangeListener(this);
		screens.getArthur().getArms().getRightLine().addPropertyChangeListener(this);
		screens.getArthur().getLegs().getLeftLine().addPropertyChangeListener(this);
		screens.getArthur().getLegs().getRightLine().addPropertyChangeListener(this);
		screens.getArthur().getHead().addPropertyChangeListener(this);
		screens.getArthur().getTorso().addPropertyChangeListener(this);
		screens.getArthur().getString().addPropertyChangeListener(this);
		
		screens.getGalahad().getArms().getLeftLine().addPropertyChangeListener(this);
		screens.getGalahad().getArms().getRightLine().addPropertyChangeListener(this);
		screens.getGalahad().getLegs().getLeftLine().addPropertyChangeListener(this);
		screens.getGalahad().getLegs().getRightLine().addPropertyChangeListener(this);
		screens.getGalahad().getHead().addPropertyChangeListener(this);
		screens.getGalahad().getTorso().addPropertyChangeListener(this);
		screens.getGalahad().getString().addPropertyChangeListener(this);
		
		screens.getGuard().getArms().getLeftLine().addPropertyChangeListener(this);
		screens.getGuard().getArms().getRightLine().addPropertyChangeListener(this);
		screens.getGuard().getLegs().getLeftLine().addPropertyChangeListener(this);
		screens.getGuard().getLegs().getRightLine().addPropertyChangeListener(this);
		screens.getGuard().getHead().addPropertyChangeListener(this);
		screens.getGuard().getTorso().addPropertyChangeListener(this);
		screens.getGuard().getString().addPropertyChangeListener(this);
		
		screens.getLancelot().getArms().getLeftLine().addPropertyChangeListener(this);
		screens.getLancelot().getArms().getRightLine().addPropertyChangeListener(this);
		screens.getLancelot().getLegs().getLeftLine().addPropertyChangeListener(this);
		screens.getLancelot().getLegs().getRightLine().addPropertyChangeListener(this);
		screens.getLancelot().getHead().addPropertyChangeListener(this);
		screens.getLancelot().getTorso().addPropertyChangeListener(this);
		screens.getLancelot().getString().addPropertyChangeListener(this);
		
		screens.getRobin().getArms().getLeftLine().addPropertyChangeListener(this);
		screens.getRobin().getArms().getRightLine().addPropertyChangeListener(this);
		screens.getRobin().getLegs().getLeftLine().addPropertyChangeListener(this);
		screens.getRobin().getLegs().getRightLine().addPropertyChangeListener(this);
		screens.getRobin().getHead().addPropertyChangeListener(this);
		screens.getRobin().getTorso().addPropertyChangeListener(this);
		screens.getRobin().getString().addPropertyChangeListener(this);
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		repaint();
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		draw(g, screens);
	}
	public static void draw(Graphics g, Screen aScreen) {
		g.drawRect(aScreen.getRectangle().getX(),aScreen.getRectangle().getY(),aScreen.getRectangle().getWidth(),aScreen.getRectangle().getHeight());
		g.drawLine(aScreen.getLeftGorge().getX(),aScreen.getLeftGorge().getY(),aScreen.getLeftGorge().getX()+aScreen.getLeftGorge().getWidth(),aScreen.getLeftGorge().getY()+aScreen.getLeftGorge().getHeight());
		g.drawLine(aScreen.getRightGorge().getX(),aScreen.getRightGorge().getY(),aScreen.getRightGorge().getX()+aScreen.getRightGorge().getWidth(),aScreen.getRightGorge().getY()+aScreen.getRightGorge().getHeight());
		g.drawOval(aScreen.getGuardSpot().getX(),aScreen.getGuardSpot().getY(),aScreen.getGuardSpot().getWidth(),aScreen.getGuardSpot().getHeight());
		g.drawOval(aScreen.getAvatarSpot().getX(),aScreen.getAvatarSpot().getY(),aScreen.getAvatarSpot().getWidth(),aScreen.getAvatarSpot().getHeight());

		File arthurFile = new File("arthur.jpg"), 
				galahadFile=new File("galahad.jpg"), 
				lancelotFile=new File("lancelot.jpg"), 
				robinFile=new File("robin.jpg"), 
				guardFile=new File("guard.jpg");
		Image arthur = null, galahad = null, lancelot=null, robin=null, guard=null;
		try {
			arthur = ImageIO.read(arthurFile);
			galahad = ImageIO.read(galahadFile);
			lancelot = ImageIO.read(lancelotFile);
			robin = ImageIO.read(robinFile);
			guard = ImageIO.read(guardFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g.drawImage(arthur, aScreen.getArthur().getHead().getX(),aScreen.getArthur().getHead().getY(), null);
		g.drawImage(galahad, aScreen.getGalahad().getHead().getX(),aScreen.getGalahad().getHead().getY(), null);
		g.drawImage(lancelot, aScreen.getLancelot().getHead().getX(),aScreen.getLancelot().getHead().getY(), null);
		g.drawImage(robin, aScreen.getRobin().getHead().getX(),aScreen.getRobin().getHead().getY(), null);
		g.drawImage(guard, aScreen.getGuard().getHead().getX(),aScreen.getGuard().getHead().getY(), null);

		draw(g,aScreen.getArthur());
		draw(g,aScreen.getLancelot());
		draw(g,aScreen.getGalahad());
		draw(g,aScreen.getGuard());
		draw(g,aScreen.getRobin());
	}
	public static void draw(Graphics g, Avatar a){
		g.drawLine(a.getArms().getLeftLine().getX(),a.getArms().getLeftLine().getY(),a.getArms().getLeftLine().getX()+a.getArms().getLeftLine().getWidth(),a.getArms().getLeftLine().getY()+a.getArms().getLeftLine().getHeight()); //Left Arm
		g.drawLine(a.getArms().getRightLine().getX(),a.getArms().getRightLine().getY(),a.getArms().getRightLine().getX()+a.getArms().getRightLine().getWidth(),a.getArms().getRightLine().getY()+a.getArms().getRightLine().getHeight()); //Right Arm
		g.drawLine(a.getLegs().getLeftLine().getX(),a.getLegs().getLeftLine().getY(),a.getLegs().getLeftLine().getX()+a.getLegs().getLeftLine().getWidth(),a.getLegs().getLeftLine().getY()+a.getLegs().getLeftLine().getHeight()); //Left Leg
		g.drawLine(a.getLegs().getRightLine().getX(),a.getLegs().getRightLine().getY(),a.getLegs().getRightLine().getX()+a.getLegs().getRightLine().getWidth(),a.getLegs().getRightLine().getY()+a.getLegs().getRightLine().getHeight()); //Right Leg
		g.drawLine(a.getTorso().getX(),a.getTorso().getY(),a.getTorso().getX()+a.getTorso().getWidth(),a.getTorso().getY()+a.getTorso().getHeight());
		g.drawString(a.getString().getText(), a.getString().getX(), a.getString().getY());
	}
}