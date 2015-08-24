package graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
public class ImageShape implements ImageShapeInterface{
	String imageFileName;
    int x, y;
    public ImageShape (String initImageFileName, int initX, int initY) {	
   	imageFileName = initImageFileName;
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
   public String getImageFileName() {
	   return imageFileName;
	   }  
   public void setImageFileName(String newVal) {
	   imageFileName = newVal ;
	   }

}
