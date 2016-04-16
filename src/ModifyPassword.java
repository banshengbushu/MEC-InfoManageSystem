

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
* Class <code>{ModifyPassword}</code>{密码更改}
*
* @author   {高乐}
* @version {2015-8-1}
* @see      java.lang.Class
* @since    JDK{jdk1.7}
*/  

public class ModifyPassword
{
	private JInternalFrame jitfMain;   
	private Container con;
	
	private JLabel jlbTop;
	private JLabel jlbStaff;
	
	private JLabel jlbCurrentPw;
	private JPasswordField jpwfCurrentPw;
	
	private JLabel jlbNewPw;
	private JPasswordField jpwfNewPw;
	
	private JLabel jlbSurePw;
	private JPasswordField jpwfSurePw;
	
	private JButton jbtSure;
	private JButton jbtExit;
	
	/**
     * {界面初始化}
     * @param       {userName}    {操作员的姓名}
     * @return       {无返回值}    {无}
     * @exception    {无异常}
     */ 
	
	public void init(String userName)
	{
		Font labelFont = new Font("宋体", Font.PLAIN, 16);
		jitfMain = new JInternalFrame("微易码科技信息管理系统-密码修改", false, true, false, true);
		con = new Container();
		con = jitfMain.getContentPane();
		con.setLayout(null);
		
		jitfMain.setBounds(100, 100, 5280/15, 3915/15);
		
		jlbTop = new JLabel("密码修改");
		jlbTop.setBounds(1680/15, 0, 1800/15, 435/15);
		jlbTop.setFont(new Font("隶书", Font.PLAIN, 30));
		jlbTop.setForeground(Color.BLUE);
		con.add(jlbTop);
		
		jlbStaff = new JLabel("操作员："+userName);
		jlbStaff.setBounds(3240/15, 480/15, 1560/15, 240/15);
		jlbStaff.setForeground(Color.RED);
		jlbStaff.setFont(new Font("宋体", Font.PLAIN, 16));
		con.add(jlbStaff);
		
		jlbCurrentPw = new JLabel("当前密码");
		jlbCurrentPw.setBounds(240/15, 1020/15, 1080/15, 240/15);
		jlbCurrentPw.setFont(labelFont);
		con.add(jlbCurrentPw);
		
		jpwfCurrentPw = new JPasswordField()
		{
			public void paste()
			{
	     		 UIManager.getLookAndFeel().provideErrorFeedback(this);
	     	}
		};
		jpwfCurrentPw.setBounds(1680/15, 960/15, 3135/15, 360/15);
		jpwfCurrentPw.setFont(labelFont);
		con.add(jpwfCurrentPw);
		
		jlbNewPw = new JLabel("  新密码");
		jlbNewPw.setBounds(240/15, 1620/15, 1080/15, 240/15);
		jlbNewPw.setFont(labelFont);
		con.add(jlbNewPw);
		
		jpwfNewPw = new JPasswordField()
		{
			public void paste()
			{
	     		 UIManager.getLookAndFeel().provideErrorFeedback(this);
	     	}
		};
		jpwfNewPw.setBounds(1680/15, 1560/15, 3135/15, 360/15);
		jpwfNewPw.setFont(labelFont);
		con.add(jpwfNewPw);
		
		jlbSurePw = new JLabel("确认密码");
		jlbSurePw.setBounds(240/15, 2220/15, 1080/15, 240/15);
		jlbSurePw.setFont(labelFont);
		con.add(jlbSurePw);
		
		jpwfSurePw = new JPasswordField()
		{
			public void paste()
			{
	     		 UIManager.getLookAndFeel().provideErrorFeedback(this);
	     	}
		};
		jpwfSurePw.setBounds(1680/15, 2160/15, 3135/15, 360/15);
		jpwfSurePw.setFont(labelFont);
		con.add(jpwfSurePw);
		
		jbtSure = new JButton("确定");
		jbtSure.setBounds(360/15, 2760/15, 1215/15, 465/15);
		jbtSure.setFont(new Font("宋体", Font.PLAIN, 22));
		con.add(jbtSure);
		//jitfMain.getRootPane().setDefaultButton(jbtSure);
		
		jbtExit = new JButton("退出");
		jbtExit.setBounds(3600/15, 2760/15, 1215/15, 465/15);
		jbtExit.setFont(new Font("宋体", Font.PLAIN, 22));
		con.add(jbtExit);
		
		jitfMain.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		jitfMain.setVisible(true);
		
	}
	
	
	
	public ModifyPassword()
	{
		this(null,null);
	} 
	
	public ModifyPassword(String userId, String userName)
	{
		init(userName);
		jpwfCurrentPw.requestFocus();
		dealAction();	
		setStatus();
	} 

	/**
     * {设置状态}
     * @param       {无参数名}   
     * @return       {无返回值}   
     * @exception    {无异常}
     */
	
	public void setStatus()
	{
		jbtSure.setEnabled(false);
		if(jpwfCurrentPw.getPassword().length >0 && jpwfNewPw.getPassword().length >0 && jpwfSurePw.getPassword().length >0)
			jbtSure.setEnabled(true);
		else
			jbtSure.setEnabled(false);
	}
	
	/**
     * {判断输入密码的有效性}
     * @param      
     *  {cs, whatPassword} cs：作为数组名称存储密码框中的内容,whatPassword：显示密码的种类; 
     *  cs
     *  whatPassword
     * @return      
     *  {Ok}  {当长度满足6-16个字符时，Ok = true为正确} 
     * @exception
     *  {无异常}
     */
	
	public boolean IsLengthInvalid(char[] cs, String whatPassword)
	{
		boolean Ok = false;

		for(int i = 0; i<cs.length && cs.length >= 6 && cs.length <= 16 ;i++)
		{
			if(cs[i] >= '0' && cs[i] <='9' || (cs[i] >= 'a' && cs[i] <='z') || (cs[i] >= 'A' && cs[i] <='Z'))
				Ok = true;		
		}				
		if(Ok != true)
			MECPubTool.showMess(jitfMain, whatPassword +"格式输入错误，请重新输入(6-16)个数字字符、字母字符！");
		return Ok;
	}
	/**
     * {判断当前密码的正确性}
     * @param      
     *  {cs，Id}    {cs为一个字符串用来存储密码框的内容的字符串形式，Id是员工编号}
     *  cs
     *  Id
     * @return       {Ok}    {当字符串内容进行比较相同则为true}
     * @exception    {在没有建立数据库时或者数据库名错误时，将会产生异常}
	*/
	public boolean isRight(String cs, String Id) throws Exception
	{
		boolean Ok = false;
		
		ResultSet rs = null;
		String hashStr = null;
			
		String currentPassword = String.valueOf(cs.hashCode());
		String SQLString = "SELECT MEC_USER_password FROM SYS_MEC_USER_PASSWORD WHERE MEC_USER_id = '"+ Id +"'";
		
		MECData dat  = new MECData("SYS_MEC_USER_PASSWORD");
		
		try
		{			
			dat.connectionDatabase();
			rs = dat.select(SQLString);
			while(rs.next())
			{
				 hashStr = rs.getString("MEC_USER_Password");
				
			}
			if(currentPassword.equals(hashStr))
				Ok = true;
			else
				MECPubTool.showMess(jitfMain, "当前密码输入错误");
					
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return Ok;
	}
	
	/**
     * {判断密码是否相同}
     * @param      
     *  {newPassword，surePassword}    {newPassword、surePassword为一个字符串用来存储密码框的内容的字符串形式}
     *  newPassword
     *  surePassword
     * @return       {Ok}    {当字符串内容进行比较相同则为true}
     * @exception    {无异常}
	*/
	public boolean isEqual(String newPassword, String surePassword)
	{
		boolean Ok = false;
		if(newPassword.equals(surePassword))
			Ok = true;
		
		return Ok;
	}	
	/**
     * {修改密码}
     * @param      
     *  {str，Id}    {str为一个字符串用来存储密码框的内容的字符串形式，Id是String类型的为编号}
     *  str
     *  Id
     * @return       {无返回值}    
     * @exception    {在没有建立数据库时或者数据库名错误时，将会产生异常}
	*/
	public void modify(String str, String Id)
	{
		String newPasswordHash = String.valueOf(str.hashCode());
		String SQLString = "UPDATE SYS_MEC_USER_PASSWORD SET MEC_USER_password = '" +
					newPasswordHash + "' WHERE MEC_USER_id = '" + Id + "'";
		
		try
		{
			MECData dat = new MECData("SYS_MEC_USER_PASSWORD");
			dat.connectionDatabase();
			
			dat.update(SQLString);
			dat.disconnection();
			System.out.println(SQLString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * {删除新密码框、确认密码框的内容，并将焦点确定到新密码框中}
     * @param      
     *  {无参数}    
     * @return       {无返回值}    
     * @exception    {无异常}
	*/
	public void deleteNS()
	{
		jpwfNewPw.requestFocus();
		jpwfSurePw.setText("");
		jpwfNewPw.setText("");
	}
	
	/**
     * {删除当前密码框的内容，并将焦点确定到当前密码框中}
     * @param      
     *  {无参数}    
     * @return     
     *  {无返回值}    
     * @exception    
     * {无异常}
	*/
	public void deleteC()
	{
		jpwfCurrentPw.setText("");
		jpwfCurrentPw.requestFocus();
	}
	
	/**
     * {窗口关闭}
     * @param      
     *  {无参数}    
     * @return     
     *  {无返回值}    
     * @exception    
     * {无异常}
	*/
	public void quitWindow()
	{
		int choose;
		choose =MECPubTool.showMess(jitfMain, "您真的要退出吗？", JOptionPane.YES_NO_CANCEL_OPTION);
		if(choose == JOptionPane.YES_OPTION )
		{
			jitfMain.dispose();
		}
	}
	
	/**
     * {处理事件响应}
     * @param      
     *  {无参数}    
     * @return     
     *  {无返回值}    
     * @exception    
     * {无异常}
	*/
	//事件的响应
	public void dealAction()
	{
		jbtExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				quitWindow();
			}
			
		});
		
	
		jbtSure.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(IsLengthInvalid( jpwfCurrentPw.getPassword(),  "当前密码"))
					{
						try 
						{
							if(isRight(String.valueOf(jpwfCurrentPw.getPassword()), "001"))
							{
								if(IsLengthInvalid( jpwfNewPw.getPassword(),  "新密码"))
								{
									if(isEqual(String.valueOf(jpwfNewPw.getPassword()), String.valueOf(jpwfCurrentPw.getPassword())))
										{
											deleteNS();
											MECPubTool.showMess(jitfMain, "新密码与当前密码重复，请重新选择新密码");
										}
									else
									{
										if(isEqual(String.valueOf(jpwfNewPw.getPassword()), String.valueOf(jpwfSurePw.getPassword())))
										{
											modify(String.valueOf(jpwfSurePw.getPassword()), "001");
											MECPubTool.showMess(jitfMain, "密码修改成功，请牢记！");
										}
										else
										{
											deleteNS();
											MECPubTool.showMess(jitfMain, "确认密码与新密码不一致，请重新输入！");
										}		
									}
									
								}
								else
									deleteNS();
							}
							else
								deleteC();						
								
						} catch (Exception e1)
						{
							e1.printStackTrace();
						}
					}
				else
					deleteC();				
			}
			
		});
				
		jpwfCurrentPw.addCaretListener(new CaretListener()
		{
			public void caretUpdate(CaretEvent e) 
			{
				setStatus();
				
			}
		});		
		
		jpwfCurrentPw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == jpwfCurrentPw)
				{
					jpwfNewPw.requestFocus();
					jpwfNewPw.setSelectionStart(0);
					jpwfNewPw.setSelectionEnd(jpwfNewPw.getPassword().length);
				}					
			}});			
		
		jpwfNewPw.addCaretListener(new CaretListener()
		{
			public void caretUpdate(CaretEvent e) 
			{
				setStatus();
				
			}
		});		
		
		jpwfNewPw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == jpwfNewPw)
				{
					jpwfSurePw.requestFocus();
					jpwfSurePw.setSelectionStart(0);
					jpwfSurePw.setSelectionEnd(jpwfSurePw.getPassword().length);
				}
					
			}});
		
		jpwfSurePw.addCaretListener(new CaretListener()
		{
			public void caretUpdate(CaretEvent e) 
			{
				setStatus();
				
			}
		});
		
		jpwfSurePw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == jpwfSurePw)
					jbtSure.doClick();
			}});		
	}
	
	public JInternalFrame getJFrame()
	{
		return jitfMain;
	}
}
