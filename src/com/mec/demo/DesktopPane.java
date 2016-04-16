package com.mec.demo;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class DesktopPane extends JDesktopPane
{
	private static final long serialVersionUID = 1L;
	private ImageIcon ico = new ImageIcon("F:\\myEclipse\\15 Ó∆⁄-3\\src\\Image\\¿÷ÀÆ¥Û.jpg");
	
	public void paintComponent(Graphics g)
	{
	     g.drawImage(ico.getImage(),0,0,this);
	}

}
