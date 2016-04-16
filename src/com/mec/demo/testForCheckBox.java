package com.mec.demo;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class testForCheckBox
{
	private JFrame jfrmMain;
	private Container cont;
	private DefaultListModel<myData> dlmList;
	private JList<myData> jlstList;
	private JScrollPane jscpList;
	private JComboBox<myData> jcmbList;
	
	
	public testForCheckBox()
	{
		initFrame();
		reinitFrame();
		dealAction();
	}
	
	public void showMess(JFrame jfrmFrame, String mess)
	{
		JOptionPane.showConfirmDialog(jfrmFrame, mess, "系统温馨提示", JOptionPane.OK_OPTION);
	}
	
	public void dealAction()
	{
		jcmbList.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) 
			{
				myData value = (myData)jcmbList.getSelectedItem();
				value.invertSelected();
				jcmbList.repaint();
			}
			public void mousePressed(MouseEvent e) {}
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
	
	private void reinitFrame()
	{
		CheckListCellRenderer jclcCheck = new CheckListCellRenderer();
		jlstList.setCellRenderer(jclcCheck);
		jcmbList.setRenderer(jclcCheck);
		
		dlmList.addElement(new myData("000","第一个"));
		dlmList.addElement(new myData("001","第二个"));
		dlmList.addElement(new myData("002","第三个"));
		jcmbList.addItem(new myData("001","第一个"));
		jcmbList.addItem(new myData("002","第二个"));
		jcmbList.addItem(new myData("003","第三个"));
	}


	private void initFrame()
	{
		jfrmMain = new JFrame("练习");
		jfrmMain.setResizable(false);
		cont = jfrmMain.getContentPane();
		cont.setLayout(null);
		jfrmMain.setSize(681, 590);
		jfrmMain.setVisible(true);
		
		dlmList = new DefaultListModel<myData>();
		jlstList = new JList<myData>(dlmList);
		jscpList = new JScrollPane(jlstList);
		jscpList.setBounds(16,144,641,148);
		cont.add(jscpList);
		
		jcmbList = new JComboBox<myData>();
		jcmbList.setBounds(16, 144+148+10, 641, 24);
		cont.add(jcmbList);
		
		jfrmMain.setDefaultCloseOperation(jfrmMain.EXIT_ON_CLOSE);
		jfrmMain.setVisible(true);
		
	}
	public static void main(String[] arg)
	{
		new testForCheckBox();
	}
}
