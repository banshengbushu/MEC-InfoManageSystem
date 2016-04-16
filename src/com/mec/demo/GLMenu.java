package com.mec.demo;


import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;



public class GLMenu 
{
	private JFrame jframMain;
	private JMenuBar jmnbTop;
	
	private JMenu jmnuFile;
	private JMenuItem jmniOpen;
	private JMenuItem jmniSave;
	private JMenuItem jmniExit;
	
	private JMenu jmnuEdit;
	private JMenuItem jmniCopy,jmniCopy1;
	private JMenuItem jmniCut,jmniCut1;
	private JMenuItem jmniPaste,jmniPaste1;
	
	private JMenu jmnuFormat;
	private JMenuItem jmniFont;
	private JMenuItem jmniMote;
	
	private JPopupMenu jpopMenu;
	
	private DefaultListModel<myData> dlmList;
	private JList<myData> jlstList;
	private JScrollPane jscpList;
	
	public GLMenu()
	{
		this.initFrame();
		Open();
		dealAction();
		
	}
	public void dealAction()
	{
		this.jframMain.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == 3)
					jpopMenu.show(jframMain, e.getX(), e.getY());										
			}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}});
		
		jlstList.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) 
			{
				myData value = jlstList.getSelectedValue();
				value.invertSelected();
				jlstList.repaint();
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}});
	}
	
	private void initFrame()
	{
		jframMain = new JFrame("记事本");
		jmnbTop = new JMenuBar();
		Container con = jframMain.getContentPane();
		con.setLayout(null);
		
		//this.jframMain.setBounds(10, 10, 30, 40);
		this.jframMain.setSize(600, 400);
		this.jframMain.setLayout(null);
		
		
		jmnuFile = new JMenu("文件");
		jmniOpen = new JMenuItem("打开");
		jmniSave = new JMenuItem("保存");
		jmniExit = new JMenuItem("退出");
		jmnuFile.add(jmniOpen);
		jmnuFile.addSeparator();
		jmnuFile.add(jmniSave);
		jmnuFile.addSeparator();
		jmnuFile.add(jmniExit);
		jmnbTop.add(jmnuFile);
		
		jmnuEdit = new JMenu("编辑");
		jmniCopy = new JMenuItem("复制");
		jmniCut = new JMenuItem("剪切");
		jmniPaste =new JMenuItem("粘贴");
		jmniCopy.setEnabled(false);//实现灰色字体
		jmnuEdit.add(jmniCopy);
		jmnuEdit.addSeparator();		
		jmnuEdit.add(jmniCut);
		jmnuEdit.addSeparator();
		jmnuEdit.add(jmniPaste);
		jmnbTop.add(jmnuEdit);	
		
		jmnuFormat = new JMenu("格式");
		jmniFont = new JMenuItem("字体");
		jmniMote = new JMenuItem("自动换行");
		jmnuFormat.add(jmniFont);
		jmnuFormat.addSeparator();
		jmnuFormat.add(jmniMote);
		jmnbTop.add(jmnuFormat);
		
		jpopMenu = new JPopupMenu();
		jmniCopy1 = new JMenuItem("撤销");
		jmniCopy1.setVisible(false);
		//jmniCopy1.setEnabled(false);
		jmniCut1 = new JMenuItem("剪切");
		jmniPaste1 = new JMenuItem("粘贴");
		jpopMenu.add(jmniCopy1);
		jpopMenu.addSeparator();
		jpopMenu.add(jmniCut1);
		jpopMenu.addSeparator();
		jpopMenu.add(jmniPaste1);
		this.jframMain.add(jpopMenu);
		
		
		this.jframMain.setJMenuBar(jmnbTop);
		
		dlmList = new DefaultListModel<myData>();
		jlstList = new JList<myData>(dlmList);
		jscpList = new JScrollPane(jlstList);
		jscpList.setBounds(16,44,491,148);
		con.add(jscpList);
		
		
		this.jframMain.setVisible(true);
		this.jframMain.setDefaultCloseOperation(jframMain.EXIT_ON_CLOSE);

	}
	
	public void Open()
	{
		CheckListCellRenderer jclcCheck = new CheckListCellRenderer();
		jlstList.setCellRenderer(jclcCheck);
		
		dlmList.addElement(new myData("000","第一个"));
		dlmList.addElement(new myData("001","第二个"));
		dlmList.addElement(new myData("002","第三个"));
		System.out.println(jframMain.getBackground());
	}
	public static void main(String[] args) 
	{
		new GLMenu();
	
	}

}
