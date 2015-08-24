package main;

import graphics.PropertyListenerClass;
import graphics.PropertyListenerInterface;
import graphics.Screen;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ConsoleSceneView implements PropertyChangeListener {

	PropertyListenerInterface propertyListener = new PropertyListenerClass();
	
	public ConsoleSceneView(Screen screen){
		screen.getArthur().getArms().getLeftLine().addPropertyChangeListener(this);
		screen.getArthur().getArms().getRightLine().addPropertyChangeListener(this);
		screen.getArthur().getLegs().getLeftLine().addPropertyChangeListener(this);
		screen.getArthur().getLegs().getRightLine().addPropertyChangeListener(this);
		screen.getArthur().getHead().addPropertyChangeListener(this);
		screen.getArthur().getTorso().addPropertyChangeListener(this);
		screen.getArthur().getString().addPropertyChangeListener(this);
		
		screen.getGalahad().getArms().getLeftLine().addPropertyChangeListener(this);
		screen.getGalahad().getArms().getRightLine().addPropertyChangeListener(this);
		screen.getGalahad().getLegs().getLeftLine().addPropertyChangeListener(this);
		screen.getGalahad().getLegs().getRightLine().addPropertyChangeListener(this);
		screen.getGalahad().getHead().addPropertyChangeListener(this);
		screen.getGalahad().getTorso().addPropertyChangeListener(this);
		screen.getGalahad().getString().addPropertyChangeListener(this);
		
		screen.getGuard().getArms().getLeftLine().addPropertyChangeListener(this);
		screen.getGuard().getArms().getRightLine().addPropertyChangeListener(this);
		screen.getGuard().getLegs().getLeftLine().addPropertyChangeListener(this);
		screen.getGuard().getLegs().getRightLine().addPropertyChangeListener(this);
		screen.getGuard().getHead().addPropertyChangeListener(this);
		screen.getGuard().getTorso().addPropertyChangeListener(this);
		screen.getGuard().getString().addPropertyChangeListener(this);
		
		screen.getLancelot().getArms().getLeftLine().addPropertyChangeListener(this);
		screen.getLancelot().getArms().getRightLine().addPropertyChangeListener(this);
		screen.getLancelot().getLegs().getLeftLine().addPropertyChangeListener(this);
		screen.getLancelot().getLegs().getRightLine().addPropertyChangeListener(this);
		screen.getLancelot().getHead().addPropertyChangeListener(this);
		screen.getLancelot().getTorso().addPropertyChangeListener(this);
		screen.getLancelot().getString().addPropertyChangeListener(this);
		
		screen.getRobin().getArms().getLeftLine().addPropertyChangeListener(this);
		screen.getRobin().getArms().getRightLine().addPropertyChangeListener(this);
		screen.getRobin().getLegs().getLeftLine().addPropertyChangeListener(this);
		screen.getRobin().getLegs().getRightLine().addPropertyChangeListener(this);
		screen.getRobin().getHead().addPropertyChangeListener(this);
		screen.getRobin().getTorso().addPropertyChangeListener(this);
		screen.getRobin().getString().addPropertyChangeListener(this);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt);
	}
}