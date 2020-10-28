package com.amazonaws.dynamodb;

import java.util.List;

interface RepoInterface {
	
	List<Stackholder> getAllStackholders();
	
	Stackholder getStackholder(String id);

	Stackholder addStackholder(Stackholder stackholder);
	
	String deleteStackholder(String id);
}
