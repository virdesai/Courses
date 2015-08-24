package main;

import tokens.Common;
import util.annotations.Tags;

@Tags({"Scanner Bean"})
public class ScannerBean {

	static String beanInput;
	static Common[] common;

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
