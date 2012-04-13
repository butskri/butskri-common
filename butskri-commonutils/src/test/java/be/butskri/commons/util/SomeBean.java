package be.butskri.commons.util;

public class SomeBean {

	private String stringField;
	private int intField;
	private SomeBean nestedBean;

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public int getIntField() {
		return intField;
	}

	public void setIntField(int intField) {
		this.intField = intField;
	}

	public SomeBean getNestedBean() {
		return nestedBean;
	}

	public void setNestedBean(SomeBean nestedBean) {
		this.nestedBean = nestedBean;
	}

}
