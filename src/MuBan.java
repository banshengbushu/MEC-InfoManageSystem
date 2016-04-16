

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class MuBan {
	
	private JFrame jfrmMain;
	private Container con;
	
	private JDesktopPane desktopPane;
	private JToolBar jtbTool;
	
	private JMenuBar jmnuTop;
	
	private ResultSet result;
	private ResultSet rs;
	private JInternalFrame newFrame;
	private String className;
	private ArrayList<String> arrayList = new ArrayList<String>();
	/**
	 * 请使用发行版MECDatabase
	 * 不需要看
	 * 注意右边蓝色提示
	 */
	public static  final void showMess(JInternalFrame jfrmMain, String mes)
	{
		JOptionPane.showConfirmDialog(jfrmMain, mes, "系统提示", 
				JOptionPane.YES_NO_OPTION);
	}
	/**
	 * 不需要看
	 * 注意右边蓝色提示
	 */
	public static final void showMess(JFrame jfrm, String mes){
		JOptionPane.showConfirmDialog(jfrm, mes, "系统提示", 
				JOptionPane.YES_NO_OPTION);
	}
	/**
	 * 不需要看
	 * 注意右边蓝色提示
	 */
	public static final int getSelect(JInternalFrame jfrm, String mes)
	{
		int s;
		s = JOptionPane.showConfirmDialog(jfrm, mes, "系统提示", JOptionPane.YES_NO_OPTION);
		return s;
	}
	/**
	 * 不需要看
	 * 注意右边蓝色提示
	 */
	public MuBan(){
		initFrame();
		newJMenu();
		dealAction();
		jfrmMain.setVisible(true);
	}
	/**
	 * 不需要看
	 * 注意右边蓝色提示
	 */
	private void newJMenu() {
		setJMenuResult();
	}
	/**
	 * 不需要看
	 * 注意右边蓝色提示
	 */
	private void setJMenu(){
		int i = 1;
		try {
			while(result.next()){
				String id = result.getString("Menu_Id");
				String name = result.getString("Menu_Name");
				int type = result.getInt("Menu_Type");
				String fId = result.getString("Father_Id");
				className = result.getString("Class_Name");
				if(type == 0 && fId.equals("0000"))
				{
					JMenu jm = new JMenu(name);
					setJMenuItem(id, name, fId, jm);
				}else if(type ==2 && fId.equals("9999")){
					final JButton jbtn = new JButton(name);
					jbtn.setName(className);
					jbtn.setFont(new Font("隶书", Font.PLAIN, 16));
					jtbTool.add(jbtn);
					jbtn.addActionListener
					(
						new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								try{
									
									if(!arrayList.contains(jbtn.getName())){
										creatWindow(jbtn.getName(), "cheneyao", "123");	
										arrayList.add(jbtn.getName());
										newFrame.addInternalFrameListener
										(
											new InternalFrameListener(){
												public void internalFrameActivated(InternalFrameEvent arg0) {}
												public void internalFrameClosed(InternalFrameEvent arg0) {
													arrayList.remove(jbtn.getName());}
												public void internalFrameClosing(InternalFrameEvent arg0) {}
												public void internalFrameDeactivated(InternalFrameEvent arg0) {}
												public void internalFrameDeiconified(InternalFrameEvent arg0) {}
												public void internalFrameIconified(InternalFrameEvent arg0) {}
												public void internalFrameOpened(InternalFrameEvent arg0) {}
											}
										);
									}
									else{
										showMess(jfrmMain, "窗口已打开");
									}
								}catch(NullPointerException s){
									showMess(jfrmMain, "该功能还未开发");
								} catch (ClassNotFoundException e1) {
									showMess(jfrmMain, "ClassNotFoundException");
								}
							}
						}
					);
				}
				
				i++;
				result.absolute(i);
			}
		} catch (SQLException e) {
			showMess(jfrmMain, "未找到5");
		}
	}
	/**
	 * 不需要看
	 * 注意右边蓝色提示
	 */
	private int setJMenuItem(String idf, String namef, String fIdf, JMenu jmf) {
		int c = 0;
		if(fIdf.equals("0000"))
			jmnuTop.add(jmf);
		try{
			while(result.next())
			{
				c++;
				String id = result.getString("Menu_Id");
				final String name = result.getString("Menu_Name");
				String fId = result.getString("Father_Id");
				className = result.getString("Class_Name");
				int type = result.getInt("Menu_Type");
				if(fId.equals(idf))
				{
					if(type == 0)
					{
						JMenu jmtwo = new JMenu(name);
						int count = setJMenuItem(id, name, fId, jmtwo);
						jmf.add(jmtwo);
						result.absolute(-count);
					}
					else if(name.equals("-")){
						jmf.addSeparator();
					}
					else
					{
						final JMenuItem jmitwo = new JMenuItem(name);
						jmitwo.setName(className);
						jmitwo.addActionListener
						(
							new ActionListener(){
								public void actionPerformed(ActionEvent arg0) {
									try{
										
										if(!arrayList.contains(jmitwo.getName())){
											creatWindow(jmitwo.getName(), "cheneyao", "123");	
											arrayList.add(jmitwo.getName());
											newFrame.addInternalFrameListener
											(
												new InternalFrameListener(){
													public void internalFrameActivated(InternalFrameEvent arg0) {}
													public void internalFrameClosed(InternalFrameEvent arg0) {
														arrayList.remove(jmitwo.getName());}
													public void internalFrameClosing(InternalFrameEvent arg0) {}
													public void internalFrameDeactivated(InternalFrameEvent arg0) {}
													public void internalFrameDeiconified(InternalFrameEvent arg0) {}
													public void internalFrameIconified(InternalFrameEvent arg0) {}
													public void internalFrameOpened(InternalFrameEvent arg0) {}
												}
											);
										}
										else{
											showMess(jfrmMain, "窗口已打开");
										}
									}catch(NullPointerException e){
										showMess(jfrmMain, "该功能还未开发");
									}catch (ClassNotFoundException e1) {
										showMess(jfrmMain, "该功能未添加\n类未找到\n或者请确定程序在缺省包下运行");
									}
								}
							}
						);
						
						jmf.add(jmitwo);
					}
				}
			}
		} catch (SQLException e) {
			showMess(jfrmMain, "SQLException 6");
		}
		return c + 1;
	}
	/**
	 * 不需要看
	 * 注意右边蓝色提示
	 */
	private void setJMenuResult()
	{
		String SQLString = "SELECT Menu_Id, Menu_Name, Menu_Type, Class_Name, Father_Id FROM SYS_INF_MAINMENU";
		MECDatabase dat = new MECDatabase(1);
		try
		{
			dat.connection("SYS_INF_MAINMENU");
			result = dat.doSql(SQLString);
			setJMenu();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 不需要看
	 * 注意右边蓝色提示
	 */
	private void dealAction(){
		jfrmMain.addComponentListener
		(
			new ComponentListener(){
				public void componentHidden(ComponentEvent arg0) {}
				public void componentMoved(ComponentEvent arg0) {
					jfrmMain.setLocation(0, 0);
				}
				public void componentResized(ComponentEvent arg0) {}
				public void componentShown(ComponentEvent arg0) {}
			}
		);
	}
	/**
	 * TODO 注意这里最好将所有要使用的类放在一个缺省包下，
	 *		否则请修改：creatWindow中的Class.forName(className)，将其修改为：Class.forName("你的包名." + className)
	 *		注意你的包名后面记得要加上一个点：.
	 */
	private void creatWindow(final String className, String peopleName, String peopleId) throws ClassNotFoundException {
			try {
				Class class1  = Class.forName(className);
				//或许上面需要修改。
				Class[] classShu = {String.class, String.class};
				Object[] objectShu = {peopleName, peopleId};
				
				Constructor cons = class1.getConstructor(classShu);
				Object p = cons.newInstance(objectShu);
				
				Method m = class1.getMethod("getJFrame", new Class[]{});
				Object soil = m.invoke(p, new Object[]{});
				
				newFrame = (JInternalFrame) soil;
				newFrame.addInternalFrameListener
				(
					new InternalFrameListener(){
						public void internalFrameActivated(InternalFrameEvent arg0) {}
						public void internalFrameClosed(InternalFrameEvent arg0) {
							arrayList.remove(className);
						}
						public void internalFrameClosing(InternalFrameEvent arg0) {}
						public void internalFrameDeactivated(InternalFrameEvent arg0) {}
						public void internalFrameDeiconified(InternalFrameEvent arg0) {}
						public void internalFrameIconified(InternalFrameEvent arg0) {}
						public void internalFrameOpened(InternalFrameEvent arg0) {}
					}
				);
				desktopPane.add(newFrame);
				
			} catch (NoSuchMethodException e) {
				showMess(jfrmMain, "没有这样的构造方法或者没有getJFrame方法\n请确认你的构造方法是传递两个参数（ID, Name）并且有getJFrame方法");
			} catch (SecurityException e) {
				showMess(jfrmMain, "SecurityException异常报错\n有问题请咨询有关人员");
			} catch (InstantiationException e1) {
				showMess(jfrmMain, "InstantiationException异常报错\n有问题请咨询有关人员");
			} catch (IllegalAccessException e1) {
				showMess(jfrmMain, "IllegalAccessException异常报错\n有问题请咨询有关人员");
			} catch (IllegalArgumentException e) {
				showMess(jfrmMain, "IllegalArgumentException异常报错\n有问题请咨询有关人员");
			} catch (InvocationTargetException e) {
				showMess(jfrmMain, "InvocationTargetException异常报错\n有问题请咨询有关人员");
			}
	}
	/**
	 * TODO 如果你需要设置背景，请在这里看
	 */
	private void initFrame() {
		jfrmMain = new JFrame("西安微易码科技有限公司信息管理系统");
		
		con = jfrmMain.getContentPane();
		jfrmMain.setVisible(true);
		
		Dimension pos = Toolkit.getDefaultToolkit().getScreenSize();

		jfrmMain.setSize(pos.width, pos.height);
		//jfrmMain.setSize(11200/15, 9600/15);
		jfrmMain.setLocation((pos.width - jfrmMain.getWidth())/2,
				 			 (pos.height - jfrmMain.getHeight())/2);
		

		jfrmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		jtbTool = new JToolBar();		
		con.add("North", jtbTool);
		
		jmnuTop = new JMenuBar();
		jfrmMain.setJMenuBar(jmnuTop);
		
		desktopPane = new JDesktopPane();
	
		ImageIcon imageIcon = new ImageIcon("F:\\myEclipse\\乐水大.jpg");
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(
				jfrmMain.getWidth(), jfrmMain.getHeight(), Image.SCALE_DEFAULT));
		
		JLabel backLabel = new JLabel(imageIcon);
		backLabel.setIcon(imageIcon);
		//backLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		backLabel.setBounds(0, 0, jfrmMain.getWidth(), jfrmMain.getHeight());
		desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));

		con.add(desktopPane);
		
		jfrmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrmMain.setResizable(false);
		jfrmMain.setVisible(true);
	}
	/**
	 * 不需要看
	 * 注意右边蓝色提示
	 */
	public static void main(String[] args)
	{
		new MuBan();
	}
}
