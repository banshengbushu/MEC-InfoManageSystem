package com.mec.demo;

import java.awt.Container;




import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.mec.demo.DesktopPane;

public class parentWindow
{
	private JFrame jfrmMain;
	private Container con;
	private DesktopPane jdtpBoder;
	private JLabel jlblBackground;
	private int whatAction = 1001;
	
	private void init()
	{
		jfrmMain = new JFrame("微易码管理信息系统");
		con = jfrmMain.getContentPane();
	//	con.setLayout(null);
		jfrmMain.setExtendedState(jfrmMain.MAXIMIZED_BOTH);
		jfrmMain.setVisible(true);
		
		jdtpBoder = new DesktopPane();
		
		ImageIcon imgiBackground = new ImageIcon("F:\\myEclipse\\乐水大.jpg");
		imgiBackground.setImage(imgiBackground.getImage().getScaledInstance(jfrmMain.getWidth(), jfrmMain.getHeight(), Image.SCALE_DEFAULT));
		jlblBackground = new JLabel(imgiBackground);
		jlblBackground.setIcon(imgiBackground);
		jlblBackground.setBounds(0, 0, jfrmMain.getWidth(), jfrmMain.getHeight());
		con.add(jdtpBoder);
		
		JMenuBar jmubMain;
		JMenu jmnuMenu;
		JMenuItem jmniItemEmploy;
		JMenuItem jmniItemPost;
		JMenuItem jmniItemModifymm;
		jmubMain = new JMenuBar();
		jmnuMenu = new JMenu("教务管理");
		jmniItemEmploy = new JMenuItem("11");
		jmniItemPost = new JMenuItem("22");
		jmniItemModifymm = new JMenuItem("课程管理");
		
		jmnuMenu.add(jmniItemEmploy);
		jmnuMenu.addSeparator();
		jmnuMenu.add(jmniItemPost);
		jmnuMenu.addSeparator();
		jmnuMenu.add(jmniItemModifymm);
		jmubMain.add(jmnuMenu);
		jfrmMain.setJMenuBar(jmubMain);
		
		jmniItemModifymm.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				
				//	new ModifyPassword(jdtpBoder, "201507001", "高乐");
					jdtpBoder.add(new CourseManage2("201507001", "高乐").getJFrame());
					//new CourseManage2();
					whatAction++;
				
				//new ModifyPassword("", "201507001", "张三疯");
			}});
		
		
		jfrmMain.setDefaultCloseOperation(jfrmMain.EXIT_ON_CLOSE);
		jfrmMain.setVisible(true);
		jfrmMain.setResizable(false);
		
	}
	
	public parentWindow()
	{
		init();
	}
	public static void main(String []args)
	{
		new parentWindow();
	} 
}
