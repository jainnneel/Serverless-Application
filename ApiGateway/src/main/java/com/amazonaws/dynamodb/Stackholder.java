package com.amazonaws.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "crudeOperation")
public class Stackholder {

	@DynamoDBHashKey
	@DynamoDBAutoGeneratedKey
	private String shId;
	@DynamoDBAttribute
	private String stackholderName;
	@DynamoDBAttribute
	private int noShare;
	public Stackholder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stackholder(String stackholderName, int noShare) {
		super();
		this.stackholderName = stackholderName;
		this.noShare = noShare;
	}
	public String getShId() {
		return shId;
	}
	public void setShId(String shId) {
		this.shId = shId;
	}
	public String getStackholderName() {
		return stackholderName;
	}
	public void setStackholderName(String stackholderName) {
		this.stackholderName = stackholderName;
	}
	public int getNoShare() {
		return noShare;
	}
	public void setNoShare(int noShare) {
		this.noShare = noShare;
	}

	
}
