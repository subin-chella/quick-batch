package com.quick.batch.types.read;

public class IntegerToDollarRead {

    private int id;
    private String dollarInString;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getDollarInString() {
		return dollarInString;
	}

	public void setDollarInString(String dollarInString) {
		this.dollarInString = dollarInString;
	}

	@Override
	public String toString() {
		return "IntegerToDollarRead [id=" + id + ", dollarInString=" + dollarInString + "]";
	}

    

}