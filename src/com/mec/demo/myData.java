package com.mec.demo;

import java.util.ArrayList;

public class myData extends BaseData
{
	protected String id;
	protected String name;
	
	public myData(String id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}
	
	public myData(ArrayList<String> select, String name2) 
	{
		// TODO Auto-generated constructor stub
	}

	public String getId()
	{
		return id;
	} 
	
	public String getName()
	{
		return name;
	} 
	
	public String toString()
	{
		return id + "   " + name;
	} 
	public String subString(int beginIndex,int  endIndex)
	{
		return id.substring(beginIndex, endIndex);
	}
}
