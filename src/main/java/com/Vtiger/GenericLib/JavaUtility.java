package com.Vtiger.GenericLib;

import java.util.Random;

public class JavaUtility
{

	public int generateRandomNo() 
	{
		Random random = new Random();
		return random.nextInt(1000);
	}
	
	
	
}
