package com.amazonaws.dynamodb;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class StackholderRepository implements RepoInterface {
	
	DynamoDBMapper mapper = DynamoConfig.mapper();
	
	@Override
	public List<Stackholder> getAllStackholders() {
		try {
			return mapper.scan(Stackholder.class, new DynamoDBScanExpression());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Stackholder getStackholder(String id) {
		try {
			return mapper.load(Stackholder.class,id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Stackholder addStackholder(Stackholder stackholder) {
		   try {
			mapper.save(stackholder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stackholder;
	}

	@Override
	public String deleteStackholder(String id) {
		try {
			Stackholder stackholder = getStackholder(id);
			if(stackholder!=null) {
				mapper.delete(stackholder);
				return "delete done!!!"+stackholder.toString(); 
			}else {
				return "not done";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
}
