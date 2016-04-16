

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class MECPubTool 
{
	public static final int showMess(JFrame jfrmMain, String mess, int ackType)
	{
		int userChoose;
		
		userChoose = JOptionPane.showConfirmDialog(jfrmMain, mess, "系统温馨提示", ackType);
		
		return userChoose;
	}
	
	public static final void showMess(JFrame jfrmMain, String mess)
	{
		JOptionPane.showConfirmDialog(jfrmMain, mess, "系统温馨提示", JOptionPane.OK_OPTION);
	}

	public static int showMess(JInternalFrame jitfMain, String mess,
			int yesNoCancelOption)
	{
		int userChoose;
		
		userChoose = JOptionPane.showConfirmDialog(jitfMain, mess, "系统温馨提示", yesNoCancelOption);
		
		return userChoose;
		
	}//自己加进去的

	public static void showMess(JInternalFrame jitfMain, String string)
	{
		JOptionPane.showConfirmDialog(jitfMain, string, "系统温馨提示", JOptionPane.OK_CANCEL_OPTION);
		
	}// 自己加进去的
}
