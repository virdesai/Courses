package main;

import tokens.Common;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class ScannerBean {

	String beanInput;
	Common[] common;

	public void setInput(String newInput) {
		
		Common[] bigger = new Common[100];

		beanInput = newInput;

		ScanString.analyzeString(beanInput, bigger);

	}

	public String getInput() {

		return beanInput;

	}

	public Common[] getCommon() {

		common = ScanString.common;

		return common;

	}

}
