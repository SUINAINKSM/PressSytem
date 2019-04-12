package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument.Iterator;

import bll.ArgmentBiz;
import bll.UserBiz;
import util.PropertiesFileUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.KeyboardFocusManager;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Label;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.AbstractListModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.SpinnerListModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Login_UI extends JFrame {

	private JPanel contentPane;
	public static JTextField username_Field;
	public static  JPasswordField passwordField;
    static private UserBiz ubz=new UserBiz();
    public static  JComboBox comboBox = new JComboBox();
    static int days;
    static int start_time;
    static String ads;//保存的用户名
    public static Date date1=new Date();
    public static Date date2=new Date();
    public static PropertiesFileUtils pul=new PropertiesFileUtils();
	public static JCheckBox chckbxNewCheckBox=new JCheckBox("\u4FDD\u5B58\u5BC6\u7801");
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	
	/**
	 * 通过key获取value
	 * @param key
	 * @return
	 */
	
	public Login_UI() {
		setResizable(false);
		setTitle("[\u7528\u6237\u767B\u9646] ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 387);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.WHITE);
		contentPane.setLayout(null);	
		JLabel lblNewLabel = new JLabel("New label");
		ImageIcon img=new ImageIcon("C:\\Users\\62628\\Pictures\\Saved Pictures\\52.png");
		JLabel label = new JLabel("\u51FA\u7248\u793E\u7BA1\u7406\u4FE1\u606F\u7CFB\u7EDF");
		label.setFont(new Font("宋体", Font.BOLD, 24));
		label.setForeground(Color.WHITE);
		label.setBounds(138, 55, 293, 45);
		contentPane.add(label);
		lblNewLabel.setBounds(10, 0, 504, 122);
		lblNewLabel.setIcon(img);
		System.out.println(Login_UI.class.getResource("").getPath());
		System.out.println(Login_UI.class.getResource("/").getPath());
		System.out.println(Login_UI.class.getClassLoader().getResource("").getPath());
		contentPane.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("\u7528 \u6237 \u540D");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_1.setBounds(95, 135, 91, 28);
		contentPane.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("\u5BC6    \u7801");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_2.setBounds(95, 176, 91, 26);
		contentPane.add(lblNewLabel_2);
		
		username_Field = new JTextField();
		username_Field.setBounds(200, 135, 145, 28);
		contentPane.add(username_Field);
		username_Field.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 176, 145, 28);
		contentPane.add(passwordField);
		if(pul.GetValueByKey("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties", "isselected").equals("true")){
			Login_UI.username_Field.setText(pul.GetValueByKey("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties","username"));
			Login_UI.passwordField.setText(pul.GetValueByKey("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties","password"));
			System.out.println(pul.GetValueByKey("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties","password"));
			chckbxNewCheckBox.setSelected(true);
		}	
		Label label_1 = new Label("\u5DE5\u4F5C\u65E5\u671F");
		label_1.setFont(new Font("宋体", Font.BOLD, 17));
		label_1.setBounds(95, 219, 91, 28);
		contentPane.add(label_1);
		chckbxNewCheckBox.setBackground(Color.WHITE);
		
	    
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxNewCheckBox.isSelected()){
					//修改
					try {
						if(ubz.User_LoginCheck(Login_UI.username_Field.getText().toString(), Login_UI.username_Field.getText().toString())==1){
						   // update("isselcted","true");//设置为记住密码状态
							//System.out.println("---------sas----------");
							try {
								pul.WriteProperties("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties", "isselected", "true");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				else{//取消自动登录
					//update("isselcted","false");//设置为记住密码状态
					try {
						pul.WriteProperties("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties", "isselected", "false");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		chckbxNewCheckBox.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		chckbxNewCheckBox.setForeground(Color.RED);
		chckbxNewCheckBox.setBounds(183, 267, 109, 31);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//date1=simpleDateFormat.parse(strDate);
				
				String username=username_Field.getText().toString();
				String password1=String.valueOf(passwordField.getPassword());
				System.out.println(username);
				System.out.println(password1);
				System.out.println(String.valueOf(passwordField.getPassword())+"211");
				System.out.println(password1);
				//System.out.println(password);
				try {
					if(ubz.finduser(username)==null){//用户不存在
						JOptionPane.showMessageDialog(null,"该账户不存在!","提示",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(ubz.User_LoginCheck(username, password1)==1){//用户名密码正确
						//获得当前用户的解锁时间，把string转换为Date型
						SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						try {
							if(ubz.myuser.get_unlocktime()==null)
								date1=null;
							else
							 date1=sDateFormat.parse(ubz.myuser.get_unlocktime());//获得当前用户自动解锁的时间
							 date2=sDateFormat.parse(ubz.Get_Currenttime1());//获得当前服务器的时间
							 if(date1!=null&&date1.getTime()>date2.getTime()){//当前还没到达用户的解锁时间
							JOptionPane.showMessageDialog(null,"当前账户处于锁定时间!","提示",JOptionPane.ERROR_MESSAGE);
						}
							else {
								//登陆成功时，清除己有的登陆失败次数
								     if(chckbxNewCheckBox.isSelected()==true){
								    	 try {
											pul.WriteProperties("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties", "username", Login_UI.username_Field.getText().toString());
											pul.WriteProperties("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties", "password", String.valueOf(passwordField.getPassword()));
											pul.WriteProperties("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties", "isselected", "true");
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
								    	// update("username",Login_UI.username_Field.getText().toString());
								    	 //System.out.println(get1("username"));
								    	 System.out.println(String.valueOf(passwordField.getPassword())+"111");
								    	 //update("password",String.valueOf(passwordField.getPassword()));
								    	 //update("isselected","true");
								    	// System.out.println(get1("password")); 
								     }
								     else{//未选择记住密码
								    	 //update("isselected","false");
								    	 try {
								    		 pul.WriteProperties("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties", "username", Login_UI.username_Field.getText().toString());
										     pul.WriteProperties("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties", "password", String.valueOf(passwordField.getPassword()));
											 pul.WriteProperties("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties", "isselected", "false");
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
								     }
									ubz.myuser.set_faulttime(0);
									ubz.resetFaulttime(username);
									ubz.modifyUnlocktime(username, null);
									ubz.myuser.set_unlocktime(null);
									ads=ubz.myuser.get_username();
									//保存用户的用户名
									dispose();
							        new Main_UI();	
						}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}//获得当前用户的解锁时间
				       
						
						
					}
					else{//登陆失败次数累计及账户锁定
						int a=ubz.myuser.get_faulttime();
						 ubz.myuser=ubz.finduser(username);
							a=a+1;
							ubz.myuser.set_faulttime(a);
							ubz.addFaulttime(username);//失败次数登记加一
						if(ubz.myuser.get_faulttime()<new ArgmentBiz().get_Num()){
						
							String error;
							if(ubz.myuser.get_faulttime()<=(new ArgmentBiz().get_Num()-1)){
								error="密码或账号错误，你还有"+(new ArgmentBiz().get_Num()-ubz.myuser.get_faulttime())+"次机会!";
							JOptionPane.showMessageDialog(null,error,"提示",JOptionPane.ERROR_MESSAGE);	
							}
						}
						else{//连续n登录失败
								SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							    ubz.myuser=ubz.finduser(username);
							    Calendar c=Calendar.getInstance();
							    c.setTime(date2);
							    //System.out.println(new ArgmentBiz().get_Lockdays());
								c.add(Calendar.DAY_OF_YEAR,new ArgmentBiz().get_Lockdays() );
								ubz.myuser.set_faulttime(0);
								date1=c.getTime();
								ubz.modifyUnlocktime(username, date1);
								ubz.resetFaulttime(username);//清空用户的输错次数
								ubz.myuser.set_unlocktime(ubz.finduser(username).get_unlocktime().toString());	
								try {
									date1=sDateFormat.parse(ubz.myuser.get_unlocktime());
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}//获得当前用户自动解锁的时间
								try {
									date2=sDateFormat.parse(ubz.Get_Currenttime1());
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}//获得当前服务器的时间
								if(date1!=null&&date1.getTime()>date2.getTime()){//当前还没到达用户的解锁时间
									 JOptionPane.showMessageDialog(null,"您已经连续"+new ArgmentBiz().get_Num()+"次密码输入错误,账户已被加锁，请"+ubz.myuser.get_unlocktime()+"后登录","提示",JOptionPane.ERROR_MESSAGE);      //提示账户已被上锁
							}
								
						}
					
						
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(57, 307, 91, 31);
		contentPane.add(btnNewButton);
		JButton button = new JButton("\u53D6\u6D88");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(315, 307, 91, 31);
		contentPane.add(button);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//JComboBox comboBox = new JComboBox();
		List<String> timeList=new ArrayList<String>();
		//int days = ArgmentsDao.getInstance().getDays();//从数据库中读取值
		
		try {
			days = new ArgmentBiz().get_Days();
			for(int i = 0; i <= days; i++)
			{
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE,-i);
				timeList.add(sdf.format(calendar.getTime()));
			}
			comboBox.setBackground(Color.WHITE);
			comboBox.setModel(new DefaultComboBoxModel(timeList.toArray()));   //初始化日期
			comboBox.setBounds(200, 217, 145, 29);
			contentPane.add(comboBox);
			//this.setBackground(Color.gray);
			JLabel lblNewLabel_3 = new JLabel();
			ImageIcon img3=new ImageIcon("username.png");
			lblNewLabel_3.setIcon(img3);
			lblNewLabel_3.setBounds(57, 135, 30, 31);
			contentPane.add(lblNewLabel_3);
			
			JLabel label_2 = new JLabel();
			label_2.setBounds(57, 176, 30, 31);
			ImageIcon img4=new ImageIcon("password.png");
			label_2.setIcon(img4);
			contentPane.add(label_2);
			
			JLabel label_3 = new JLabel();
			ImageIcon img5=new ImageIcon("date.png");
			label_3.setIcon(img5);
			label_3.setBounds(57, 216, 30, 31);
			contentPane.add(label_3);
			this.setLocationRelativeTo(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*if(get1("isselected").equals("true")){
			chckbxNewCheckBox.setSelected(true);
		}
		else if(get1("isselected").equals("false")){
			chckbxNewCheckBox.setSelected(false);
		}*/
		this.setVisible(true);
		/*if(start_time++>=1&&chckbxNewCheckBox.isSelected()==false){
			Login_UI.username_Field.setText(Login_UI.ads);
			Login_UI.passwordField.requestFocus();
			//Login_UI.passwordField.setText("");
		}
		if(chckbxNewCheckBox.isSelected()==true){
			//Login_UI.username_Field.setText(get1("username"));
			//Login_UI.passwordField.setText("password");
		}
		*/
		if(pul.GetValueByKey("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties", "username")!=null){//如果当前配置文件中已经有对应的账号名
			Login_UI.username_Field.setText(pul.GetValueByKey("C:/Users/62628/WORKSPACE/Press_System/bin/test.properties","username"));
			Login_UI.passwordField.requestFocus();
		}

		//passwordField.requestFocus();
	}
}
