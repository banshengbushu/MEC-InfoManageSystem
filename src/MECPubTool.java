

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class MECPubTool 
{
	public static final int showMess(JFrame jfrmMain, String mess, int ackType)
	{
		int userChoose;
		
		userChoose = JOptionPane.showConfirmDialog(jfrmMain, mess, "ϵͳ��ܰ��ʾ", ackType);
		
		return userChoose;
	}
	
	public static final void showMess(JFrame jfrmMain, String mess)
	{
		JOptionPane.showConfirmDialog(jfrmMain, mess, "ϵͳ��ܰ��ʾ", JOptionPane.OK_OPTION);
	}

	public static int showMess(JInternalFrame jitfMain, String mess,
			int yesNoCancelOption)
	{
		int userChoose;
		
		userChoose = JOptionPane.showConfirmDialog(jitfMain, mess, "ϵͳ��ܰ��ʾ", yesNoCancelOption);
		
		return userChoose;
		
	}//�Լ��ӽ�ȥ��

	public static void showMess(JInternalFrame jitfMain, String string)
	{
		JOptionPane.showConfirmDialog(jitfMain, string, "ϵͳ��ܰ��ʾ", JOptionPane.OK_CANCEL_OPTION);
		
	}// �Լ��ӽ�ȥ��
}
