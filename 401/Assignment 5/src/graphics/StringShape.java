package graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
public class StringShape implements StringShapeInterface{
		String text;
		int x, y;

		public StringShape(String initText, int initX, int initY) {
			text = initText;
			x = initX;
			y = initY;
		}

		public int getX() {
			return x;
			}
		public void setX(int newX) {
			x = newX;
			}
		public int getY() {
			return y;
			}
		public void setY(int newY) {
			y = newY;
			}
		public String getText() {
			return text;
			}
		public void setText(String newVal) {
			text = newVal;
			}

	}
