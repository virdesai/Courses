package graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
public class StringShape extends Location implements StringShapeInterface{
		String text;

		public StringShape(String initText, int initX, int initY) {
			text = initText;
			x = initX;
			y = initY;
		}
		public String getText() {
			return text;
			}
		public void setText(String newVal) {
			text = newVal;
			}

	}
