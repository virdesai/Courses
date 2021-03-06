package graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
public class ImageShape extends Location implements ImageShapeInterface{
	String imageFileName;
	
    public ImageShape(String initImageFileName, int initX, int initY) {	
   	super(initX, initY);
    imageFileName = initImageFileName;
    }
   public String getImageFileName() {
	   return imageFileName;
	   }  
   public void setImageFileName(String newVal) {
	   imageFileName = newVal ;
	   }

}
