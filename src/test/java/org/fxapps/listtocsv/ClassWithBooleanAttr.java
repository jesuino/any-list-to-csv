package org.fxapps.listtocsv;

public class ClassWithBooleanAttr {

	private boolean authorized;
	
	public ClassWithBooleanAttr(boolean authorized) {
		super();
		this.authorized = authorized;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}	
	
}
