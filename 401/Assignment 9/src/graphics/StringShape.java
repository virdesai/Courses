package graphics;

import java.beans.PropertyChangeEvent;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
public class StringShape extends Location implements StringShapeInterface{
		String text;

		public StringShape(String initText, int initX, int initY) {
			super(initX, initY);
			text = initText;
		}
		public String getText() {
			return text;
			}
		public void setText(String newVal) {
			String oldText = text;
			text = newVal;
			propertyListener.notifyAllListeners(new PropertyChangeEvent(this, "text", oldText, newVal));
			}

	}
