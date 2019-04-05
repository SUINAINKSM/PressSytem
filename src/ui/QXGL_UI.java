package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bll.UserBiz;

import java.awt.Font;

public class QXGL_UI extends JFrame{

	private JPanel contentPane;
	private static JTextField textField=new JTextField();
	private static JTextField textField_1=new JTextField();
	public static int usernumber;//保存用户的数目
	public static int uid;//当前用户的ID
	public static String[] columnNames1 = {"功能模块","已分配基本权限"};	
    public static UserBiz ubz=new UserBiz();
    public static JButton button = new JButton("\u5220\u9664");
    public static JLabel lblNewLabel = new JLabel("\u6CE8\u518C");
    public static DefaultListModel<String> model=new DefaultListModel<>();
    public static JList<String> list;
    public static String[] columnNames2 = {"功能模块","未分配的权限"};
    public static JLabel lblNewLabel_5 = new JLabel("已分项：0");
    private static JTable table;
    private static JTable table_1;
    public static Object[][] arranged_obj;
    public static Object[][] unarranged_obj;
	public static JLabel lblNewLabel_4 = new JLabel("未分项：0");
	public static JButton button_4 = new JButton("<=");
	public static JButton btnNewButton = new JButton("=>");
	public Object[][] queryUnallocatedData(int id){
    	List<HashMap<String,String>> result=ubz.UnallocatedByUserID(id);
        unarranged_obj=new Object[result.size()][columnNames2.length];
        for(int i=0;i<result.size();i++){
            for(int j=0;j<columnNames2.length;j++){
            	for(Map.Entry<String, String> arg:result.get(i).entrySet()){
            		unarranged_obj[i][0]=arg.getValue();
            		unarranged_obj[i][1]=arg.getKey();
            	}
            }
        }
        return unarranged_obj;
    }
    
  //生成已分配权限表格数据
    /**
     * @return
     */
    public Object[][] queryAllocatedData(int id){
    	List<HashMap<String,String>> result=ubz.AllocatedByUserID(id);
    	arranged_obj=new Object[result.size()][columnNames1.length];
        for(int i=0;i<result.size();i++){
            for(int j=0;j<columnNames1.length;j++){
            	for(Map.Entry<String, String> arg:result.get(i).entrySet()){
            		arranged_obj[i][0]=arg.getValue();
            		arranged_obj[i][1]=arg.getKey();
            	}    	
            }
        }
        return arranged_obj;
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QXGL_UI frame = new QXGL_UI();
					new Thread().start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public QXGL_UI() {
		setResizable(false);
		
		setTitle("\u3010\u7528\u6237\u6CE8\u518C\u53CA\u6743\u9650\u7BA1\u7406\u3011");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(14, 13, 90, 397);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel label = new JLabel("\u7528\u6237\u4E00\u89C8\u8868");
		label.setBounds(7, 0, 75, 18);
		label.setForeground(new Color(178, 34, 34));
		panel.add(label);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 31, 75, 336);
		panel.add(scrollPane);
		
		
		ArrayList<String> arr_list=new ArrayList<String>();
		try {
			arr_list=ubz.getUsernameList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		usernumber=arr_list.size();
		for(String a:arr_list){
			model.addElement(a);
		}
		list = new JList(model){
			@Override

            public int locationToIndex(Point location) {

                int index = super.locationToIndex(location);

                if (index != -1 && !getCellBounds(index, index).contains(location)) {

                    return -1;

                }

                else {

                    return index;

                }

            }
		};
		list.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				btnNewButton.setEnabled(true);
				button_4.setEnabled(true);
				
				lblNewLabel.setText("修改");
				button.setEnabled(true);
				
				String username=list.getSelectedValue();
				try {
					uid=ubz.getUserID(username);
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
					//ubz.showUserRights(username);
					//ubz.showUserUNRights(username);
					lblNewLabel_5.setText("已分项:"+ubz.showUserRightsNumber(username));
					lblNewLabel_4.setText("未分项:"+ubz.showUserUNRightsNumber(username));;
					QXGL_UI.unarranged_obj=queryUnallocatedData(uid);
					QXGL_UI.arranged_obj=queryAllocatedData(uid);
					table.setModel(new DefaultTableModel(unarranged_obj,columnNames2));
					table_1.setModel(new DefaultTableModel(arranged_obj,columnNames1));
					table.setVisible(true);
					table_1.setVisible(true);
					 table_1.repaint();//table_1动态刷新
					 table_1.updateUI();
					 table.repaint();
					 table.updateUI();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println(username);
				try {
					textField.setText(username);
					textField_1.setText(ubz.getUserpassword(username));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (list.locationToIndex(e.getPoint()) == -1 && !e.isShiftDown()

                           && !isMenuShortcutKeyDown(e)) {

                       list.clearSelection();

                   }
			}
			 private boolean isMenuShortcutKeyDown(InputEvent event) {

                 return (event.getModifiers() & Toolkit.getDefaultToolkit()

                         .getMenuShortcutKeyMask()) != 0;

             }

		});
	
		scrollPane.setViewportView(list);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(136, 13, 518, 72);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setBounds(0, 0, 43, 18);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D");
		lblNewLabel_1.setBounds(20, 20, 72, 18);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6     \u7801");
		lblNewLabel_2.setBounds(20, 41, 63, 18);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setText("共"+usernumber+"人");
		lblNewLabel_3.setForeground(new Color(178, 34, 34));
		lblNewLabel_3.setBounds(19, 379, 52, 18);
		panel.add(lblNewLabel_3);
		
		textField.setBounds(88, 20, 136, 18);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1.setBounds(88, 41, 136, 18);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_1 = new JButton("\u4FDD\u5B58");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lblNewLabel.getText().toString()=="注册"){
					try {
						  if(textField.getText().toString().equals("")){//用户名为空，提示用户重新注册
							  //System.out.printf(textField.getText().toString()+1);
							  JOptionPane.showMessageDialog(null,"用户名不能为空!","提示",JOptionPane.INFORMATION_MESSAGE);
								textField.setText(null);
								textField_1.setText(null);
								textField.requestFocus();
						  }
						  else if(ubz.Register(textField.getText().toString(), textField_1.getText().toString())==1){
							//JOptionPane.showMessageDialog(null,"用户注册成功!","提示",JOptionPane.INFORMATION_MESSAGE);
							//JOptionPane.showMessageDialog(null, "在对话框内显示的描述性的文字", "标题条文字串", JOptionPane.ERROR_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(null,"已有同用户名用户，该账号注册失败!","错误",JOptionPane.ERROR_MESSAGE);
							textField.setText(null);
							textField_1.setText(null);
							textField.requestFocus();
						}
						ArrayList<String> arr_list=new ArrayList<String>();
						try {
							arr_list=ubz.getUsernameList();
							model.removeAllElements();
							for(String a:arr_list){
								model.addElement(a);
							}
							list.setModel(model);
							/*list.addMouseListener(new MouseAdapter(){
								public void mouseClicked(MouseEvent e)
								{
									lblNewLabel.setText("修改");
									button.setEnabled(true);
									String username=list.getSelectedValue();
									System.out.println(username);
									try {
										textField.setText(username);
										textField_1.setText(ubz.getUserpassword(username));
										
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});*/
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						usernumber=arr_list.size();
						lblNewLabel_3.setText("共"+usernumber+"人");
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(lblNewLabel.getText().toString()=="修改"){
					try {
						String username=textField.getText().toString();
						//int uid=ubz.getUserID(username);
						System.out.println("用户id为"+uid);
						/*if(ubz.setUserName(uid, username)==0){
							JOptionPane.showMessageDialog(null,"已有重名用户名，用户名修改失败!","提示",JOptionPane.INFORMATION_MESSAGE);
						}*/
					    if(ubz.getUserName(uid).equals(textField.getText().toString())==false){
					    	int flag=ubz.updateUserName(uid, username);
				            System.out.printf(username);
							if(flag==0){
								JOptionPane.showMessageDialog(null,"已有同用户名用户，该账号名修改失败!","错误",JOptionPane.ERROR_MESSAGE);
								textField.setText(null);
								textField.requestFocus();
							}
					    }
						
						ArrayList<String> arr_list=new ArrayList<String>();
						try {
							arr_list=ubz.getUsernameList();
							model.removeAllElements();
							for(String a:arr_list){
								model.addElement(a);
							}
							list.setModel(model);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ubz.updateUserPassword(textField.getText().toString(), textField_1.getText().toString());
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button_1.setBounds(310, 22, 63, 27);
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("\u590D\u4F4D");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton.setEnabled(true);
				button_4.setEnabled(true);
				btnNewButton.setEnabled(false);
				   list.clearSelection();
				   textField.setText(null);
				   textField_1.setText(null);
				   textField.requestFocus();
				   lblNewLabel.setText("注册");
				   button.setEnabled(false);
				   button_4.setEnabled(false);
				   lblNewLabel_4.setText("未分项:0");
				   lblNewLabel_5.setText("已分项:0");
				   table.setVisible(false);
				   table_1.setVisible(false);
				   /*int i=0;
				 	  while(i<15){
				 		  int j;
				 		  for(j=0;j<2;j++)
				 			{
				 			  switch(j)
				 			   {
				 				 case 0:
				 					QXGL_UI.arranged_obj[i][j]=null;
				 					break;
				 				 case 1:
				 					QXGL_UI.arranged_obj[i][j]=null;
				 					break;
				 		
				 						
				 					}

				 				}
				 				i++;
				 				
				 	  }
				 	  
				 	   int i1=0;
					 	  while(i1<15){
					 		  int j;
					 		  for(j=0;j<2;j++)
					 			{
					 			  switch(j)
					 			   {
					 				 case 0:
					 					QXGL_UI.unarranged_obj[i1][j]=null;
					 					break;
					 				 case 1:
					 					QXGL_UI.unarranged_obj[i1][j]=null;
					 					break;
					 		
					 						
					 					}

					 				}
					 				i1++;
					 				
					 				
					 	  }*/
					 	 table_1.repaint();//table_1动态刷新
						 table_1.updateUI();
						 table.repaint();
						 table.updateUI(); 
				   
			}
			
		});
		button_2.setBounds(382, 22, 63, 27);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("\u5173\u95ED");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();//退出该界面
			}
		});
		button_3.setBounds(454, 22, 63, 27);
		panel_1.add(button_3);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=textField.getText().toString();
				int selection = JOptionPane.showConfirmDialog(null,"确认删除该用户？","注意",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if( selection == JOptionPane.OK_OPTION ){
					try {
						ubz.deleteUsers(username);
						ArrayList<String> arr_list=new ArrayList<String>();
						try {
							arr_list=ubz.getUsernameList();
							model.removeAllElements();
							for(String a:arr_list){
								model.addElement(a);
							}
							list.setModel(model);
							btnNewButton.setEnabled(true);
							button_4.setEnabled(true);
							   list.clearSelection();
							   textField.setText(null);
							   textField_1.setText(null);
							   textField.requestFocus();
							   lblNewLabel.setText("注册");
							   button.setEnabled(false);
							   button.setEnabled(false);
							   button_4.setEnabled(false);
							   lblNewLabel_4.setText("未分项:0");
							   lblNewLabel_5.setText("已分项:0");
							   /*int i=0;
							 	  while(i<15){
							 		  int j;
							 		  for(j=0;j<2;j++)
							 			{
							 			  switch(j)
							 			   {
							 				 case 0:
							 					QXGL_UI.arranged_obj[i][j]=null;
							 					break;
							 				 case 1:
							 					QXGL_UI.arranged_obj[i][j]=null;
							 					break;
							 		
							 						
							 					}

							 				}
							 				i++;
							 				
							 	  }
							 	  
							 	   int i1=0;
								 	  while(i1<15){
								 		  int j;
								 		  for(j=0;j<2;j++)
								 			{
								 			  switch(j)
								 			   {
								 				 case 0:
								 					QXGL_UI.unarranged_obj[i1][j]=null;
								 					break;
								 				 case 1:
								 					QXGL_UI.unarranged_obj[i1][j]=null;
								 					break;
								 		
								 						
								 					}

								 				}
								 				i1++;
								 				
								 				
								 	  }
								 	  */
								 	 table.setVisible(false);
									 table_1.setVisible(false);
								 	 table_1.repaint();//table_1动态刷新
									 table_1.updateUI();
									 table.repaint();
									 table.updateUI(); 
							/*list.addMouseListener(new MouseAdapter(){
								public void mouseClicked(MouseEvent e)
								{
									lblNewLabel.setText("修改");
									button.setEnabled(true);
									String username=list.getSelectedValue();
									System.out.println(username);
									try {
										textField.setText(username);
										textField_1.setText(ubz.getUserpassword(username));
										
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});*/
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						usernumber=arr_list.size();
						lblNewLabel_3.setText("共"+usernumber+"人");
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
				
			}
		});
		
		
		button.setEnabled(false);
		button.setBounds(238, 22, 63, 27);
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(136, 92, 518, 318);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_1 = new JLabel("\u57FA\u672C\u6743\u9650\u5206\u914D");
		label_1.setForeground(new Color(178, 34, 34));
		label_1.setBounds(0, 0, 111, 18);
		panel_2.add(label_1);
		btnNewButton.setEnabled(false);
		
	
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//用户添加特权
				/*btnNewButton.setEnabled(true);
				button_4.setEnabled(false);
				*/
				//int index = table.getSelectedRow();
				
				try {
					//unarranged_obj[index][0]=null;
					//unarranged_obj[index][1]=null;
					int[] array=new int[9];//随机数存储数组
					array=table.getSelectedRows();
					int num=table.getSelectedRows().length;//选中的行数
					System.out.println(num);
					for(int i=0;i<num;i++){
						/*unarranged_obj[array[i]][0]=null;
						unarranged_obj[array[i]][1]=null;
						*/
						Object RightsName=table.getValueAt(array[i], 1);
						ubz.ArrangeRights(textField.getText().toString(), RightsName.toString());
						/*unarranged_obj[array[i]][0]=null;
						unarranged_obj[array[i]][1]=null;
						*/
					}
					lblNewLabel_4.setText("未分项:"+ubz.showUserUNRightsNumber(textField.getText().toString()));
					lblNewLabel_5.setText("已分项:"+ubz.showUserRightsNumber(textField.getText().toString()));
					QXGL_UI.unarranged_obj=queryUnallocatedData(uid);
					QXGL_UI.arranged_obj=queryAllocatedData(uid);
					table.setModel(new DefaultTableModel(unarranged_obj,columnNames2));
					table_1.setModel(new DefaultTableModel(arranged_obj,columnNames1));
					//ubz.showUserRights(textField.getText().toString());
					//ubz.showUserUNRights(textField.getText().toString());
					table_1.repaint();//table_1动态刷新		
					table_1.updateUI();
					table.repaint();
					table.updateUI();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(193, 81, 71, 27);
		panel_2.add(btnNewButton);
		button_4.setEnabled(false);
		
	
		button_4.addActionListener(new ActionListener() {//用户删除特权
			public void actionPerformed(ActionEvent e) {
				/*button_4.setEnabled(true);
				btnNewButton.setEnabled(false);
				*/
				/*int index = table_1.getSelectedRow();
				Object RightsName=table_1.getValueAt(index, 1);
				arranged_obj[index][0]=null;
				arranged_obj[index][1]=null;*/
				try {
					int[] array=new int[9];//随机数存储数组
					array=table_1.getSelectedRows();
					int num=table_1.getSelectedRows().length;//选中的行数
					for(int i=0;i<num;i++){
						Object RightsName=table_1.getValueAt(array[i], 1);
						ubz.UnArrangeRights(textField.getText().toString(), RightsName.toString());
						/*arranged_obj[array[i]][0]=null;
						arranged_obj[array[i]][1]=null;*/
						
						
					}
					lblNewLabel_4.setText("未分项:"+ubz.showUserUNRightsNumber(textField.getText().toString()));
					lblNewLabel_5.setText("已分项:"+ubz.showUserRightsNumber(textField.getText().toString()));
					/*ubz.showUserRights(textField.getText().toString());
					ubz.showUserUNRights(textField.getText().toString());*/
					QXGL_UI.unarranged_obj=queryUnallocatedData(uid);
					QXGL_UI.arranged_obj=queryAllocatedData(uid);
					table.setModel(new DefaultTableModel(unarranged_obj,columnNames2));
					table_1.setModel(new DefaultTableModel(arranged_obj,columnNames1));
					 table.repaint();
					 table.updateUI();
					 table_1.repaint();//table_1动态刷新
					 table_1.updateUI();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_4.setBounds(193, 144, 71, 27);
		panel_2.add(button_4);
	
		lblNewLabel_4.setForeground(new Color(178, 34, 34));
		lblNewLabel_4.setBounds(48, 300, 112, 18);
		panel_2.add(lblNewLabel_4);
		
		lblNewLabel_5.setForeground(new Color(178, 34, 34));
		lblNewLabel_5.setBounds(360, 300, 84, 18);
		panel_2.add(lblNewLabel_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 24, 169, 262);
		panel_2.add(scrollPane_1);
		
		
		
		table = new JTable(){
			 public boolean isCellEditable(int row, int column)
	            {
	                return false;
	            }
		};
		table.setModel(new DefaultTableModel(unarranged_obj, columnNames2));
		//table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//int index=table.getSelectedRow();
				//System.out.print(index);
				btnNewButton.setEnabled(true);
				button_4.setEnabled(false);
				//btnNewButton.setEnabled(true);
				
			}
		});
		
		//table.setShowVerticalLines(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(156);
		scrollPane_1.setViewportView(table);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(289, 25, 199, 262);
		panel_2.add(scrollPane_2);
		
		//JTable table = new JTable(obj,columnNames);
		table_1 = new JTable(){
			 public boolean isCellEditable(int row, int column)
	            {
	                return false;
	            }
		};
		table_1.setModel(new DefaultTableModel(arranged_obj, columnNames1));
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnNewButton.setEnabled(false);
				button_4.setEnabled(true);
			}
		});
		scrollPane_2.setViewportView(table_1);
		this.setLocationRelativeTo(null);
		table.setVisible(false);
		table_1.setVisible(false);
		this.setVisible(true);
		textField.requestFocus();
	}
}
