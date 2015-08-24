package main;

import tokens.Common;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class ScannerBean {

	static String beanInput;
	Common[] common;
	
	ScanString scanString = new ScanString();

	public void setInput(String newInput) {
		Common[] bigger = new Common[100];
		beanInput = newInput;
		scanString.analyzeString(beanInput, bigger);
	}

	public static String getInput() {
		return beanInput;
	}

	public Common[] getCommon() {
		common = ScanString.common;
		return common;
	}
}
