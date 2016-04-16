package com.mec.demo;

import java.awt.Color;

public class BaseData
{
	protected String helpString;
	protected Color background;
	protected Color foreground;
	protected boolean selected;
	
	public BaseData(String helpString)
	{
		this.helpString = helpString;
		selected = false;
	}
	
	public BaseData()
	{
		this(null);
	}
	
	public Color getBackground()
	{
		return background;
	}
	
	public Color getForeground()
	{
		return foreground;
	}
	
	public void setBackground(Color background)
	{
		this.background = background;
	}
	
	public void setForeground(Color foreground)
	{
		this.foreground = foreground;
	}
	
	public String getHelpString()
	{
		return helpString;
	}
	
	public void setHelpString(String helpString)
	{
		if(helpString != null)
			this.helpString = helpString;
		else
			this.helpString = "";
	}
	
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
	
	public void invertSelected()
	{
		selected = !selected;
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
}
