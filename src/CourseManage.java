

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
* Class <code>{CourseManage}</code>{微易码科技信息管理-课程管理}
*
* @author   {高乐}
* @version {2015/8/14}
* @see      java.lang.Class
* @since    JDK{jdk1.7}
*/ 


public class CourseManage
{
	private JInternalFrame jitfMain;
	private Container con;
	
	private JLabel jlbTop;
	
	private ButtonGroup bgShow;
	private JRadioButton[] jbIsAll;
	
	private JLabel jlbCourse;
	private JList<myData> jlstCourseList;
	private DefaultListModel<myData> dlmCourseList;
	private JScrollPane jslpCourseList;
	private JLabel jlbCourseSum;
	
	private JLabel jlbStartTime;
	private JComboBox<String> jcbYear;
	private JLabel jlbYear;
	private JComboBox<String> jcbMonth;
	private JLabel jlbMonth;
	
	private JLabel jlbCourseId;
	private JLabel jlbCourseIdtxt;
	
	private JLabel jlbCourseName;
	private JLabel jlbCourseNametxt;
	
	private JLabel jlbMainName;
	private JComboBox<String> jcbMainNametxt;
	
	private JLabel jlbCourseTime;
	private JLabel jlbCourseTimetxt;
	
	private JLabel jlbCoursePrice;
	private JTextField jtfCoursePricetxt;
	private JLabel jlbYuan;
	
	private JLabel jlbClassTime;
	private JLabel jlbClassTimetxt;
	
	private JLabel jlbTeacher;
	private JComboBox<String> jcbTeacher;
	
	private JLabel jlbMaxNumber;
	private JTextField jtfMaxNumbertxt;
	private JLabel jlbPeople;
	
	private JLabel jlbBookState;
	private ButtonGroup bgBook;
	private JRadioButton[] jbIsBook;


	private JLabel jlbCourseState;
	private ButtonGroup bgBegin;
	private JRadioButton[] jbIsBegin;

	
	private JLabel jlbSubject;
	private JList<myData> jlstSubjectList;
	private DefaultListModel<myData> dlmSubjectList;
	private JScrollPane jslpSubjectList;
	private JLabel jlbSubjectSum;
	
	private JButton jbtAdd;
	private JButton jbtModify;
	private JButton jbtExit;
	
	private JLabel jlbOperator;
	
	private JTextField jtf;
	
	private final static int BROWS = 0;	
	private final static int ADD = 1;
	private final static int MODIFY = 2;
	
	private final static int YES = 0;
	private final static int NO = 1;
	
	private int whataction;
	private static final int addtion = 0;
	private static final int modify = 1;

	private int condition;
	
	public JInternalFrame getJFrame()
	{
		return jitfMain;
	}
	
	public CourseManage()
	{
		this(null, null);
	}
	
	public CourseManage(String userId, String userName)
	{
		initFrame(userName);
		reinitFrame();
		dealAction();
	} 
	
	/**
	 * {界面初始化}
	 * @param userName {操作员姓名}
	 */
	public void initFrame(String userName)
	{
		Font labFont = new Font("宋体", Font.PLAIN,15);
		
		jitfMain = new JInternalFrame("微易码科技信息管理系统-课程管理", false, true, false, true);
		jitfMain.setBounds(300/15, 300/15, 13695/15, 7800/15);
		con = new Container();
		con = jitfMain.getContentPane();
		con.setLayout(null);
		
		jlbTop = new JLabel("课程管理");
		jlbTop.setFont(new Font("隶书", Font.PLAIN,32));
		jlbTop.setBounds(5760/15, 0/15, 2055/15, 360/15);
		jlbTop.setForeground(Color.BLUE);
		con.add(jlbTop);
		
		jbIsAll = new JRadioButton[2]; 
		jbIsAll[0] = new JRadioButton("显示所有课程");
		jbIsAll[0].setFont(labFont);
		jbIsAll[0].setBounds(240/15, 480/15, 1755/15, 480/15);
		con.add(jbIsAll[0]);
		
		jbIsAll[1] = new JRadioButton("显示未结课课程");
		jbIsAll[1].setFont(labFont);
		jbIsAll[1].setBounds(3000/15, 480/15, 2115/15, 480/15);
		con.add(jbIsAll[1]);
		
		bgShow = new ButtonGroup();
		bgShow.add(jbIsAll[0]);
		bgShow.add(jbIsAll[1]);
		
		jlbCourse = new JLabel("课程列表");
		jlbCourse.setFont(labFont);
		jlbCourse.setBounds(240/15, 1080/15, 1095/15, 240/15);
		con.add(jlbCourse);
		
		dlmCourseList = new DefaultListModel<myData>();
		jlstCourseList = new JList<myData>(dlmCourseList);
		jslpCourseList = new JScrollPane(jlstCourseList);
		jslpCourseList.setBounds(240/15, 1440/15, 4695/15, 5100/15);
		con.add(jslpCourseList);
		
		jlbCourseSum = new JLabel();
		jlbCourseSum.setFont(labFont);
		jlbCourseSum.setBounds(240/15, 6720/15, 4935/15, 240/15);
		con.add(jlbCourseSum);
		
		jlbStartTime = new JLabel("开课时间:");
		jlbStartTime.setBounds(5400/15, 600/15, 1095/15, 240/15);
		jlbStartTime.setFont(labFont);
		con.add(jlbStartTime);
		
		jcbYear = new JComboBox<String>();
		jcbYear.setBounds(6600/15, 540/15, 960/15, 360/15);
		con.add(jcbYear);
		
		jlbYear = new JLabel("年");
		jlbYear.setBounds(7725/15, 600/15, 360/15, 240/15);
		jlbYear.setFont(labFont);
		con.add(jlbYear);
		
		jcbMonth = new JComboBox<String>();
		jcbMonth.setBounds(8265/15, 540/15, 825/15, 360/15);
		con.add(jcbMonth);
		
		jlbMonth = new JLabel("月");
		jlbMonth.setBounds(9225/15, 600/15, 240/15, 240/15);
		jlbMonth.setFont(labFont);
		con.add(jlbMonth);
		
		jlbCourseId = new JLabel("课程编号:");
		jlbCourseId.setBounds(5400/15, 1080/15, 1095/15, 240/15);
		jlbCourseId.setFont(labFont);
		con.add(jlbCourseId);
		
		jlbCourseIdtxt = new JLabel();
		jlbCourseIdtxt.setBounds(6600/15, 1080/15, 2895/15, 240/15);
		jlbCourseIdtxt.setFont(labFont);
		con.add(jlbCourseIdtxt);
		
		jlbCourseName = new JLabel("课程名称:");
		jlbCourseName.setBounds(5400/15, 1560/15, 1095/15, 240/15);
		jlbCourseName.setFont(labFont);
		con.add(jlbCourseName);
		
		jlbCourseNametxt = new JLabel();
		jlbCourseNametxt.setBounds(6600/15, 1560/15, 3375/15, 240/15);
		jlbCourseNametxt.setFont(labFont);
		con.add(jlbCourseNametxt);
		
		jlbMainName = new JLabel("主 名 称:");
		jlbMainName.setBounds(5400/15, 2040/15, 1095/15, 240/15);
		jlbMainName.setFont(labFont);
		con.add(jlbMainName);
		
		jcbMainNametxt = new JComboBox<String>();
		jcbMainNametxt.setBounds(6600/15, 1980/15, 2880/15, 360/15);
		jcbMainNametxt.setFont(labFont);
		jcbMainNametxt.setEditable(true);
		con.add(jcbMainNametxt);
		
		jlbCourseTime = new JLabel("总 课 时:");
		jlbCourseTime.setBounds(5400/15, 2520/15, 1095/15, 240/15);
		jlbCourseTime.setFont(labFont);
		con.add(jlbCourseTime);
		
		jlbCourseTimetxt = new JLabel();
		jlbCourseTimetxt.setBounds(6600/15, 2520/15, 2895/15, 240/15);
		jlbCourseTimetxt.setFont(labFont);
		con.add(jlbCourseTimetxt);
		
		jlbCoursePrice = new JLabel("课程价格:");
		jlbCoursePrice.setBounds(5400/15, 3000/15, 1095/15, 240/15);
		jlbCoursePrice.setFont(labFont);
		con.add(jlbCoursePrice);
		
		jtfCoursePricetxt = new JTextField();
		jtfCoursePricetxt.setBounds(6600/15, 2940/15, 840/15, 360/15);
		jtfCoursePricetxt.setFont(labFont);
		con.add(jtfCoursePricetxt);
		
		jlbYuan = new JLabel("元");
		jlbYuan.setBounds(7440/15, 3000/15, 705/15, 240/15);
		jlbYuan.setFont(labFont);
		con.add(jlbYuan);
		
		jlbClassTime = new JLabel("上课时间:");
		jlbClassTime.setBounds(5400/15, 3480/15, 1095/15, 240/15);
		jlbClassTime.setFont(labFont);
		con.add(jlbClassTime);
		
		jlbClassTimetxt = new JLabel("已定");
		jlbClassTimetxt.setBounds(6600/15, 3480/15, 705/15, 240/15);
		jlbClassTimetxt.setFont(labFont);
		con.add(jlbClassTimetxt);
		
		jlbTeacher = new JLabel("授课教师:");
		jlbTeacher.setBounds(5400/15, 3960/15, 1095/15, 240/15);
		jlbTeacher.setFont(labFont);
		con.add(jlbTeacher);
		
		jcbTeacher = new JComboBox<String>();
		jcbTeacher.setBounds(6600/15, 3900/15, 2880/15, 360/15);
		jcbTeacher.setFont(labFont);
		con.add(jcbTeacher);
		
		jlbMaxNumber = new JLabel("最大容量:");
		jlbMaxNumber.setBounds(5400/15, 4440/15, 1095/15, 240/15);
		jlbMaxNumber.setFont(labFont);
		con.add(jlbMaxNumber);
		
		jtfMaxNumbertxt = new JTextField();
		jtfMaxNumbertxt.setBounds(6600/15, 4380/15, 840/15, 360/15);
		jtfMaxNumbertxt.setFont(labFont);
		con.add(jtfMaxNumbertxt);
		
		jlbPeople = new JLabel("人/班");
		jlbPeople.setBounds(7440/15, 4440/15, 705/15, 240/15);
		jlbPeople.setFont(labFont);
		con.add(jlbPeople);
		
		jlbBookState = new JLabel("签到状态:");
		jlbBookState.setBounds(5400/15, 4920/15, 1095/15, 240/15);
		jlbBookState.setFont(labFont);
		con.add(jlbBookState);
		
		jbIsBook = new JRadioButton[2]; 
		jbIsBook[0] = new JRadioButton("开始上课");
		jbIsBook[0].setFont(labFont);
		jbIsBook[0].setBounds(6600/15, 4800/15, 1275/15, 480/15);
		con.add(jbIsBook[0]);
		
		jbIsBook[1] = new JRadioButton("暂停上课");
		jbIsBook[1].setFont(labFont);
		jbIsBook[1].setBounds(8205/15, 4800/15, 1275/15, 480/15);
		con.add(jbIsBook[1]);
		
		bgBook = new ButtonGroup();
		bgBook.add(jbIsBook[0]);
		bgBook.add(jbIsBook[1]);
		
		jlbCourseState = new JLabel("课程状态:");
		jlbCourseState.setBounds(5400/15, 5400/15, 1095/15, 240/15);
		jlbCourseState.setFont(labFont);
		con.add(jlbCourseState);
		
		jbIsBegin = new JRadioButton[2]; 
		jbIsBegin[0] = new JRadioButton("开课");
		jbIsBegin[0].setFont(labFont);
		jbIsBegin[0].setBounds(6600/15, 5400/15, 915/15, 240/15);
		con.add(jbIsBegin[0]);
		
		jbIsBegin[1] = new JRadioButton("结课");
		jbIsBegin[1].setFont(labFont);
		jbIsBegin[1].setBounds(8205/15, 5400/15, 915/15, 240/15);
		con.add(jbIsBegin[1]);
		
		bgBegin = new ButtonGroup();
		bgBegin.add(jbIsBegin[0]);
		bgBegin.add(jbIsBegin[1]);
		
		jlbSubject = new JLabel("课程组成");
		jlbSubject.setFont(labFont);
		jlbSubject.setBounds(9720/15, 720/15, 1095/15, 240/15);
		con.add(jlbSubject);
		
		dlmSubjectList = new DefaultListModel<myData>();
		jlstSubjectList = new JList<myData>(dlmSubjectList);
		jslpSubjectList = new JScrollPane(jlstSubjectList);
		jslpSubjectList.setBounds(9720/15, 1080/15, 3495/15, 4860/15);
		con.add(jslpSubjectList);
		
		jlbSubjectSum = new JLabel();
		jlbSubjectSum.setFont(labFont);
		jlbSubjectSum.setBounds(9720/15, 6000/15, 3495/15, 315/15);
		con.add(jlbSubjectSum);
		
		jbtAdd = new JButton("添加");
		jbtAdd.setFont(labFont);
		jbtAdd.setBounds(5400/15, 6120/15, 1215/15, 495/15); 
		con.add(jbtAdd);
		
		jbtModify = new JButton("修改");
		jbtModify.setFont(labFont);
		jbtModify.setBounds(7200/15, 6120/15, 1215/15, 495/15); 
		con.add(jbtModify);
		
		jbtExit = new JButton("退出");
		jbtExit.setFont(labFont);
		
		
		jbtExit.setBounds(12240/15, 6480/15, 1215/15, 495/15); 
		con.add(jbtExit);
		
		jlbOperator = new JLabel("操作员:"+userName);
		jlbOperator.setBounds(11520/15, 120/15, 1695/15, 255/15);
		jlbOperator.setForeground(Color.RED);
		jlbOperator.setFont(labFont);
		con.add(jlbOperator);
		
		jitfMain.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jitfMain.setVisible(true);
	}
	
	/**
	 * {再次初始化}
	 */
	private void reinitFrame()
	{
			initSubject();
			initStartTime();
			initMainName();
			initTeacher();
			initCourseList();
			setStatus(BROWS);
			ClickOnCourseList();
			jbIsAll[YES].setSelected(true);
			
	}
	
	/**
	 * 设置状态
	 * @param whatStatus 哪种状态
	 */
	private void setStatus(int whatStatus)
	{
		boolean status = true;
		
		jbIsAll[YES].setEnabled(status);
		jbIsAll[NO].setEnabled(status);
		
		if(status = whatStatus == BROWS)
		{
			
			if(dlmCourseList.getSize()<1)
				jbtModify.setEnabled(!status);	

			jlstCourseList.setEnabled(status);
					
			jcbYear.setEnabled(!status);
			jcbMonth.setEnabled(!status);
			jcbMainNametxt.setEnabled(!status);
			jcbTeacher.setEnabled(!status);
			jtfCoursePricetxt.setEnabled(!status);
			jtfMaxNumbertxt.setEnabled(!status);
			jbIsBook[YES].setEnabled(!status);
			jbIsBook[NO].setEnabled(!status);
			jbIsBegin[YES].setEnabled(!status);
			jbIsBegin[NO].setEnabled(!status);
			jlstSubjectList.setEnabled(!status);
			
		}
		else if(status = whatStatus == ADD)
		{
			jlstCourseList.setEnabled(!status);
			jbtModify.setEnabled(status);			
			jcbYear.setEnabled(status);
			jcbMonth.setEnabled(status);
			jcbMainNametxt.setEnabled(status);
			jcbTeacher.setEnabled(status);
			jtfCoursePricetxt.setEnabled(status);
			jtfMaxNumbertxt.setEnabled(status);
			jbIsBook[YES].setEnabled(!status);
			jbIsBook[NO].setEnabled(!status);
			jbIsBegin[YES].setEnabled(!status);
			jbIsBegin[NO].setEnabled(!status);
			jlstSubjectList.setEnabled(status);
		}
		else if(status = whatStatus == MODIFY)
		{
			jlstCourseList.setEnabled(!status);
			
			jcbYear.setEnabled(status);
			jcbMonth.setEnabled(status);
			jcbMainNametxt.setEnabled(status);
			jcbTeacher.setEnabled(status);
			jtfCoursePricetxt.setEnabled(status);
			jtfMaxNumbertxt.setEnabled(status);
			jbIsBook[YES].setEnabled(status);
			jbIsBook[NO].setEnabled(status);
			jbIsBegin[YES].setEnabled(status);
			jbIsBegin[NO].setEnabled(status);
			jlstSubjectList.setEnabled(status);
		}
	}
	
	/**
	 * 点击课程列表
	 */
	private void ClickOnCourseList()
	{
		
		String id = null;
		if(jlstCourseList.getSelectedValue()!=null)
			 id = jlstCourseList.getSelectedValue().subString(0, 10);
		
		showCourseInfo(id);
	}
	
	/**
	 * 显示课程信息
	 * @param id 课程编号
	 */
	private void showCourseInfo(String id)
	{
		if(id!=null)
		{
			jlbCourseIdtxt.setText(id);
			jlbCourseIdtxt.setForeground(Color.RED);
			jcbYear.setSelectedItem(id.substring(0, 0+4));
			jcbYear.updateUI();
			jcbMonth.setSelectedItem(id.substring(4, 4+2));			
			jcbMonth.updateUI();
		}
		
		ResultSet rs = null;
		String SQLString = "SELECT CourseName, MainName, HaveCourseTime, MaxNumber, SYS_INF_TEACHER.TeacherId AS df, BookInStatus, CourseStatus, TeacherName, CoursePrice "
				+"FROM SYS_INF_COURSE, SYS_INF_TEACHER "+"WHERE CourseId = '"+ id +"' AND SYS_INF_COURSE.TeacherId = SYS_INF_TEACHER.TeacherId";
		
		MECData dat = new MECData("MEC_SYS_COURSE");
		
		try 
		{
			dat.connectionDatabase();
			rs = dat.select(SQLString);
			
			while(rs.next())
			{
				jlbCourseNametxt.setText(rs.getString("CourseName"));
				jcbMainNametxt.setSelectedItem(rs.getString("MainName"));
				jtfMaxNumbertxt.setText(rs.getString("MaxNumber"));
				jtfCoursePricetxt.setText(rs.getString("CoursePrice"));
			//	MECPubTool.showMess(jitfMain, rs.getString("CoursePrice"));
				jlbClassTimetxt.setText(rs.getString("HaveCourseTime"));
				jcbTeacher.setSelectedItem(rs.getString("df")+rs.getString("TeacherName"));
				jbIsBook[rs.getInt("BookInStatus")].setSelected(true);
				jbIsBegin[rs.getInt("CourseStatus")].setSelected(true);				
				
			}			
		dat.disconnection();
		jlbCourseSum.setText("共"+dlmCourseList.getSize()+"个课程");
		
		showSubJectInfo(id); 

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示科目信息
	 * @param id 课程编号
	 */
	private void showSubJectInfo(String id)
	{
		cleanSelectedItem();
		
		ResultSet rs = null;
	
		String SQLString = "SELECT SYS_XTGL_SUBJECT.SubjectId, SubjectTime, MainName, SubjectName, SubjectFee "
				+"FROM SYS_SUBJECT_COURSE, SYS_XTGL_SUBJECT, SYS_INF_COURSE "+"WHERE SYS_SUBJECT_COURSE.CourseId = '"+id
				+"'AND SYS_SUBJECT_COURSE.SubjectId = SYS_XTGL_SUBJECT.SubjectId"			
				+" AND SYS_INF_COURSE.CourseId = SYS_SUBJECT_COURSE.CourseId";
		
		int sumFee = 0;
		double sumTime = 0;
		String subjectId = null;
		String name = null;
		MECData dat = new MECData("MEC_SYS_COURSE");
		int i = 0;
		int j = 0;
		try 
		{
			dat.connectionDatabase();
			
			rs = dat.select(SQLString);
			while(rs.next())
			{
				sumFee += rs.getInt("SubjectFee");
				sumTime += rs.getInt("SubjectTime");
				name = rs.getString("MainName");
				subjectId = rs.getString("SubjectId");
				i++;
				
				for(; j < dlmSubjectList.getSize(); j++)
					if(dlmSubjectList.getElementAt(j).toString().substring(0, 0+2).equals(subjectId))
					{
						myData mydata = dlmSubjectList.getElementAt(j);
						mydata.setSelected(true);
					}
				j = 0;
			
			}
			jlstSubjectList.repaint();
			jlbCourseTimetxt.setText(sumTime+"");
			if(condition == MODIFY)
				jtfCoursePricetxt.setText(jtfCoursePricetxt.getText());
			else
				jtfCoursePricetxt.setText(sumFee+"");
			
			jlbSubjectSum.setText(name+"包括"+ i+"个科目");
			
			dat.disconnection();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}					
	}

	/**
	 *清除Checkbox所选中的项
	 */
	private void cleanSelectedItem()
	{
		for(int j=0; j < dlmSubjectList.getSize(); j++)
		{
			myData mydata = dlmSubjectList.getElementAt(j);
			mydata.setSelected(false);
		}
		jlstSubjectList.clearSelection();
	}
	
	/**
	 * 选中科目时显示科目信息
	 */
	private void selectOnSubjectInfo()
	{	
		ArrayList<String> subjectId = new ArrayList<String>();
		int i = 0;
		ResultSet rs = null;
		double sumTime = 0;
		int sumFee = 0; 
		for(;i<dlmSubjectList.getSize();i++)
			if(dlmSubjectList.getElementAt(i).isSelected())
				subjectId.add(dlmSubjectList.getElementAt(i).toString().substring(0, 0+2));
		MECData dat = new MECData("MEC_SYS_COURSE");
		try
		{
			dat.connectionDatabase();
			for(i = 0; i<subjectId.size(); i++)
			{
				String id = subjectId.get(i);
				String SQLString = "SELECT SYS_XTGL_SUBJECT.SubjectId, SubjectTime, SubjectFee "
									+"FROM SYS_XTGL_SUBJECT "+"WHERE SYS_XTGL_SUBJECT.SubjectId = '"+id+"'";
				rs = dat.select(SQLString);
				while(rs.next())
				{
					sumTime += rs.getInt("SubjectTime");
					sumFee += rs.getInt("SubjectFee");
				}
				rs = null;
			}
			dat.disconnection();
			jlbCourseTimetxt.setText(sumTime+"");
			if(condition == MODIFY)
				jtfCoursePricetxt.setText(jtfCoursePricetxt.getText());
			else 
				jtfCoursePricetxt.setText(sumFee+"");
			} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * 根据选中的主名称进行添加课程信息
	 */
	private void SelectMainName()
	{
		
		String newId = getNewId();   
		String name = jcbMainNametxt.getSelectedItem().toString(); 
		String max = jtfMaxNumbertxt.getText();
		String num = newId.substring(6, 6+2);
		String courseName =  jcbYear.getSelectedItem().toString() + "年" +  jcbMonth.getSelectedItem().toString() + "月" +
					 jcbMainNametxt.getSelectedItem().toString() + newId.substring(8,8+2);
		String time = "未定";
		String teacherId = jcbTeacher.getSelectedItem().toString().substring(0,0+4);
		
		String SQLString = "INSERT INTO SYS_INF_COURSE (CourseId, CourseNumber, ClassNumber, CourseName, MainName, HaveCourseTime, TeacherId, MaxNumber, BookInStatus, CourseStatus, CoursePrice) VALUES ('" +
				newId + "', '" + num  + "', '" + newId.substring(8,8+2)  + "', '" + courseName  + "', '" + name + "', '" + time  + "', '" + teacherId  + "', '" + max + "', '" + 1 + "', '" + 0 +"', '"+jtfCoursePricetxt.getText()+"')";
		
		int i = 0;
		String SQLstr = null;		
	
		MECData dat = new MECData("MEC_SYS_COURSE");
		try 
		{
			dat.connectionDatabase();
			dat.update(SQLString);
			
			if(jlstSubjectList.getSelectedValue()!=null)
			{
				ArrayList<String> subjectId = new ArrayList<String>();
				for(; i<dlmSubjectList.getSize(); i++)
				{
					myData md = dlmSubjectList.get(i);
					if(md.isSelected())
					{
						subjectId.add(md.subString(0, 0+2));

					}
				}
				for(i=0; i<subjectId.size(); i++)
				{
					SQLstr = "INSERT INTO SYS_SUBJECT_COURSE (CourseId, SubjectId) VALUES ('"+newId.substring(0, 8)+ newId.substring(8,8+2)+"', '"+subjectId.get(i)+"')";
					dat.update(SQLstr);
				}
				dat.disconnection();
				
				myData insertValue =new myData(newId, courseName);
				selectInsertValue(insertValue);
				showSubJectInfo(newId.substring(0, 8)+ newId.substring(8,8+2));
				
			}else
			{
				MECPubTool.showMess(jitfMain, "请您选择科目!");
			}
						
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 得到新的课程序号
	 * @return number新的课程序号
	 */
	private String getNewCoursenumber()
	{
		
		ResultSet rs = null;
		String mainName = jcbMainNametxt.getSelectedItem().toString();
		String SQLString = "SELECT DISTINCT CourseNumber FROM SYS_INF_COURSE WHERE MainName = '" + mainName + "'";
		
		MECData dat = new MECData("MEC_SYS_COURSE");
		String number = null;
		try 
		{
			dat.connectionDatabase();
			rs = dat.select(SQLString);
			while(rs.next())
			{
				number  = rs.getString("CourseNumber");
			}
			dat.disconnection();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return number;
	} 
	
	/**
	 * 得到新的课程编号
	 * @return newId新的课程编号
	 */
	private String getNewId()
	{
		String year = jcbYear.getSelectedItem().toString();
		String month = jcbMonth.getSelectedItem().toString();
		String courseNumber = getNewCoursenumber();
		String classNumber = null;
		String newId = null;

		int num = 0;
		int max = 0;
		ResultSet rs = null;
		
		String SQLString = "SELECT CourseId, ClassNumber "
				+"FROM SYS_INF_COURSE "+"WHERE LEFT(CourseId, 8) = '"+ year+month+courseNumber+"'";

		MECData dat = new MECData("MEC_SYS_COURSE");
		try 
		{
			dat.connectionDatabase();
			rs = dat.select(SQLString);
			if(rs == null)
				newId = year+month+courseNumber+"01";
			else
			{	while(rs.next())
				{
		
					classNumber = rs.getString("ClassNumber");
					num = Integer.valueOf(classNumber);
					if(num>max)
						max = num;					
				}
				max+=1;
				String.valueOf(max);
				classNumber = String.format("%02d", max);
				newId = year+month+courseNumber+classNumber;
			}
		dat.disconnection();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return newId;
	}
		

	
	/**
	 * 在ComboBox中直接写入主名称进行添加
	 */
	private void OnMainName()
	{
		
		jtf = (JTextField)jcbMainNametxt.getEditor().getEditorComponent();
		String inputName = jtf.getText();
			
		String year = jcbYear.getSelectedItem().toString();
		String month = jcbMonth.getSelectedItem().toString();
		String number = getNewNumber();
				
		String SQLString = "INSERT INTO SYS_INF_COURSE (CourseId, CourseNumber, ClassNumber, CourseName, MainName, HaveCourseTime, TeacherId, MaxNumber, BookInStatus, CourseStatus, CoursePrice) "
				+ "	VALUES ('"+year+month+number+"01"+"', '"+number+"', '"+"01"+"', '"+year+"年"+month+"月"+inputName+"01"+"', '"+inputName+"', '未定', '"+new myData(jcbTeacher.getSelectedItem().toString(),"").subString(0, 0+4)+"', '"+jtfMaxNumbertxt.getText()+"', '1', '0','"+jtfCoursePricetxt.getText()+"')";	
			
		String SQLstr = null;			
		int i = 0;
		
		MECData dat = new MECData("MEC_SYS_COURSE");
			
		try
		{
			dat.connectionDatabase();
				
			dat.update(SQLString);
			if(jlstSubjectList.getSelectedValue() != null)
			{
				ArrayList<String> subjectId = new ArrayList<String>();
				for(; i<dlmSubjectList.getSize(); i++)
				{
					myData md = dlmSubjectList.get(i);
					if(md.isSelected())
					{
						subjectId.add(md.subString(0, 0+2));

					}
				}
				for(i=0; i<subjectId.size(); i++)
				{
					SQLstr = "INSERT INTO SYS_SUBJECT_COURSE (CourseId, SubjectId) VALUES ('"+year+month+number+"01"+"', '"+subjectId.get(i)+"')";
					dat.update(SQLstr);
				}
				
				dat.disconnection();
				
				myData insertValue = new myData(year+month+number+"01", year+"年"+month+"月"+inputName+"01");
				selectInsertValue(insertValue);				
				
				initMainName();
				showCourseInfo(year+month+number+"01");
			//	selectSubject();
			}
			else
			{
				MECPubTool.showMess(jitfMain, "请您选择科目!");
			}
					
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}		
			
	}
	
	/**
	 * 得到最大的课程编号，并进行加1
	 * @return newNumber所添加课程的编号
	 */
	private String getNewNumber()
	{
		ResultSet rs = null;
		String SQLString = "SELECT DISTINCT CourseNumber FROM SYS_INF_COURSE";
		String newNumber = "0";
		int num = 0;
		int max = 0;
		
		MECData dat = new MECData("MEC_SYS_COURSE");
		try 
		{
			dat.connectionDatabase();
			rs = dat.select(SQLString);
			while(rs.next())
			{
				newNumber = rs.getString("CourseNumber");
				num = Integer.valueOf(newNumber);
				if(num>max)
					max = num;
			}
			dat.disconnection();
			max+=1;

			String.valueOf(max);
			newNumber = String.format("%02d", max);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return newNumber;
		
	}
	
	/**
	 * 列表中添加内容，并且直接显示在课程列表中
	 * @param insertValue 需要添加的内容
	 * @throws Exception 当添加的内容为空时，就有异常出现
	 */
	private void selectInsertValue(myData insertValue) throws Exception
	{			
		dlmCourseList.add(dlmCourseList.getSize(), insertValue);
		jlstCourseList.setSelectedValue(insertValue, true);
	}	
	

	/**
	 * 判断是否存在主名称
	 * @return Ok 当Ok为真时，主名称存在，当Ok为假时，主名称不存在
	 */
	private void addCourseInfo()
	{
		boolean Ok = false;
		jtf = (JTextField)jcbMainNametxt.getEditor().getEditorComponent();
		
		String inputName = jtf.getText();
		ResultSet rs = null;
		String Name = null;
		String SQLString = "SELECT DISTINCT MainName "
							+"FROM SYS_INF_COURSE ";
		MECData dat = new MECData("MEC_SYS_COURSE");
		try
		{
			dat.connectionDatabase();
			rs = dat.select(SQLString);
			while(rs.next() && Ok == false)
			{
				Name = rs.getString("MainName");
				if(Name.equals(inputName))
					Ok = true;					
			}
			
			if(Ok == true)
				SelectMainName();
			else
			{
				initMainName();
				if(jcbMainNametxt.getSelectedItem()== null)
					MECPubTool.showMess(jitfMain, "请您输入主名称");
				else
					OnMainName();
			}				
		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改信息
	 */
	private void modifyAction()
		{
			condition = MODIFY;
			String CoursePrice = null;
	
			String courseId = jlstCourseList.getSelectedValue().subString(0, 0+10);
			CoursePrice = jtfCoursePricetxt.getText();
			String teacherId = (String) jcbTeacher.getSelectedItem().toString().substring(0, 0+4);
			String maxNumber = jtfMaxNumbertxt.getText();
			String BookInStatus = jbIsBook[YES].isSelected() ? "0"  : "1";
			String CourseStatus = jbIsBegin[YES].isSelected() ? "0" : "1";
			
			String SQLString = "UPDATE SYS_INF_COURSE SET CoursePrice = '" + CoursePrice +
							   "', TeacherId = '" + teacherId + "', MaxNumber = '" +
							   maxNumber + "', BookInStatus = '" + BookInStatus + "', " +
							   "CourseStatus = '" + CourseStatus + "' " +
							   "WHERE CourseId = '" + courseId + "'";
			
			
			addToAccess(SQLString, courseId);
			
			for(int i = 0; i < dlmCourseList.getSize(); i++)
				if(dlmCourseList.get(i).subString(0, 0+10).equals(courseId))
					jlstCourseList.setSelectedIndex(i);
			showCourseInfo(courseId);
			selectSubject();
			
		}
	/**
	 * 添加或修改数据库中的数据，并显示其信息
	 * @param SQLString SQL语句
	 * @param courseId	课程编号
	 */
	private void addToAccess(String SQLString, String courseId)
		{
			String SQL = "DELETE * FROM SYS_SUBJECT_COURSE WHERE CourseId = '"
					+ courseId + "'";
			int i = 0;
			MECData data = new MECData("MEC_SYS_COURSE");
			ArrayList<String> selectedSubject = new ArrayList<String>();
			for(; i < dlmSubjectList.getSize(); i++)
			{
				myData ck = dlmSubjectList.getElementAt(i);
				if(ck.isSelected())
				{
					selectedSubject.add(ck.toString().substring(0, 0+2));
				}
					
			}
			
			try 
			{
				data.connectionDatabase();
				
				data.update(SQL);
				
				data.update(SQLString);
				System.out.println(SQLString);
				
				for(i = 0; i < selectedSubject.size(); i++)
					data.update("INSERT INTO SYS_SUBJECT_COURSE (SubjectId, CourseId) " + 
								"VALUES ('" + selectedSubject.get(i) + "', '" + courseId + "')");
				
				data.disconnection();
			
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	
	
	private void selectSubject()
	{	
		ArrayList<String> subjectId = new ArrayList<String>();
		int i = 0;
		ResultSet rs = null;
		int sumFee = 0; 
		for(;i<dlmSubjectList.getSize();i++)
			if(dlmSubjectList.getElementAt(i).isSelected())
				subjectId.add(dlmSubjectList.getElementAt(i).toString().substring(0, 0+2));
		MECData dat = new MECData("MEC_SYS_COURSE");
		try
		{
			dat.connectionDatabase();
			for(i = 0; i<subjectId.size(); i++)
			{
				String id = subjectId.get(i);
				String SQLString = "SELECT SYS_XTGL_SUBJECT.SubjectId, SubjectFee "
									+"FROM SYS_XTGL_SUBJECT "+"WHERE SYS_XTGL_SUBJECT.SubjectId = '"+id+"'";
				rs = dat.select(SQLString);
				while(rs.next())
				{
					sumFee += rs.getInt("SubjectFee");
				}
				rs = null;
			}
			dat.disconnection();
			jtfCoursePricetxt.setText(sumFee+"");
			} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

		
	/**
	 * 初始化课程列表
	 */
	private void initCourseList() 
	{
		dlmCourseList.removeAllElements();
		ResultSet rs = null;
		String SQLString = "SELECT CourseId, CourseName "+
				"FROM SYS_INF_COURSE ";
		String id = null;
		String name = null;
		
		MECData dat = new MECData("MEC_SYS_COURSE");
		
		if(jbIsAll[NO].isSelected())
			SQLString += " WHERE CourseStatus = '"+0+"'";	
		
		try
		{
			dat.connectionDatabase();
			rs = dat.select(SQLString);
			
			while(rs.next())
			{
				id = rs.getString("CourseId");
				name = rs.getString("CourseName");
				dlmCourseList.addElement(new myData(id,name));
			}
			if(dlmCourseList.getSize()>0)
				jlstCourseList.setSelectedIndex(0);
		
			dat.disconnection();
		} catch (Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	/**
	 * 初始化老师
	 */
	private void initTeacher() 
	{
		ResultSet rs = null;
		String SQLString = "SELECT TeacherId, TeacherName "+
				"FROM SYS_INF_TEACHER " + "ORDER BY TeacherId";
		String id = null;
		String name = null;
		
		MECData dat = new MECData("MEC_SYS_COURSE");
		
		try 
		{
			dat.connectionDatabase();
			rs = dat.select(SQLString);
			while(rs.next())
			{
				id = rs.getString("TeacherId");
				name = rs.getString("TeacherName");
				jcbTeacher.addItem(id+name);				
			}
			dat.disconnection();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 初始化主名称
	 */
	private void initMainName()
	{
		ResultSet rs = null;
		String SQLString = "SELECT DISTINCT MainName "+
				"FROM SYS_INF_COURSE ";
		String name = null;
		
		MECData dat = new MECData("MEC_SYS_COURSE");
		
		try 
		{
			dat.connectionDatabase();
			rs = dat.select(SQLString);
			while(rs.next())
			{
				name = rs.getString("MainName");
				jcbMainNametxt.addItem(name);
			//	model.addElement(name);
			}

			dat.disconnection();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	
	}
	
	/**
	 * 初始化开课时间
	 */
	private void initStartTime() 
	{
		Calendar rightNow = Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR);
		int month = rightNow.get(Calendar.MONTH)+1;
		
		for(int i=year-1; i<year+2; i++)
			jcbYear.addItem(i+"");
		for(int i=1; i<13; i++)
			jcbMonth.addItem(String.format("%02d", i));
		if(dlmCourseList.getSize()<1)
		{
			jcbYear.setSelectedItem(String.valueOf(year));
			jcbMonth.setSelectedItem(String.format("%02d", month));
		}		
	}

	/**
	 * 初始化科目信息
	 */
	private void initSubject()
	{
		CheckListCellRenderer jclcCheck = new CheckListCellRenderer();
		jlstSubjectList.setCellRenderer(jclcCheck);
	
		ResultSet rs = null;
		String SQLString = "SELECT SubjectId, SubjectName "+
				"FROM SYS_XTGL_SUBJECT "+"ORDER BY SubjectId";
		String id = null;
		String name = null;
		
		MECData dat = new MECData("MEC_SYS_COURSE");
		
		try 
		{
			dat.connectionDatabase();
			rs = dat.select(SQLString);
			
			while(rs.next())
			{
				id = rs.getString("SubjectId");
				name = rs.getString("SubjectName");
				dlmSubjectList.addElement(new myData(id,name));	
				
			}

		dat.disconnection();		
		
		jclcCheck.setSelected(true);
		
	
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 处理事件
	 */
	private void dealAction()
	{		
		jlstCourseList.addMouseListener(new MouseListener()
		{
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseClicked(MouseEvent e)
			{
				ClickOnCourseList();
				jlstSubjectList.repaint();
			}
		});
		
		jlstCourseList.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				
				ClickOnCourseList();
				jlstSubjectList.repaint();
			}});
	
		jbIsAll[YES].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				initCourseList();
			}});
		
		jbIsAll[NO].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				initCourseList();
			}});
		jbtAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(jbtAdd.getText().equals("添加"))
				{
					
					jbtAdd.setText("确定");
					jbtModify.setText("取消");	
					jlbCourseIdtxt.setText(null);
					jlbCourseNametxt.setText(null);
					jlbCourseTimetxt.setText(null);
					jtfCoursePricetxt.setText(null);
					jlbClassTimetxt.setText(null);
					jtfMaxNumbertxt.setText(null);
					cleanSelectedItem();
					setStatus(ADD);
					jbIsBook[NO].setSelected(true);
					jbIsBegin[YES].setSelected(true);
					whataction = addtion;
				}
				else 
				{
					jlstSubjectList.repaint();

					if(whataction == addtion)
					{
						addCourseInfo();							
					}
						
					else if(whataction == modify)
					{
						modifyAction();
					}
					jbtAdd.setText("添加");
					jbtModify.setText("修改");
					setStatus(BROWS);
				}
			}			
		});
		
		jbtModify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(jbtModify.getText().equals("修改"))
				{ 
					
					jbtAdd.setText("确定");
					jbtModify.setText("取消");
					
					setStatus(MODIFY);
					
					whataction = modify;
					int isUsed = jbIsBegin[YES].isSelected() ? 0 : 1;
					if(isUsed == 1)
					{
						jbIsBegin[YES].setEnabled(false);
					}			

				}
				else
				{
					if((jlstCourseList.getSelectedValue() != null))
					{
						showCourseInfo(jlstCourseList.getSelectedValue().toString().substring(0,0+10));
					}
					jbtAdd.setText("添加");
					jbtModify.setText("修改");
					setStatus(BROWS);
				}
			}});
		
			jcbMainNametxt.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					jcbMainNametxt.requestFocus();
					
				}});
			jlstSubjectList.addMouseListener(new MouseListener()
			{
				
				public void mouseClicked(MouseEvent e) 
				{
					
					myData value = (myData)jlstSubjectList.getSelectedValue();
					if(value != null)
						value.invertSelected();
					jlstSubjectList.repaint();
						selectOnSubjectInfo();
				}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}});
	}	
}