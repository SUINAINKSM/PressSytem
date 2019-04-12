package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bll.ABiz;
import bll.BBiz;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TEST2_UI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	public  Object[][] obj;
	public  JLabel label_1 = new JLabel();
	public  static String[] columnNames = {"排序号","编辑室名称"};	
	public  JLabel label = new JLabel("添加");
	public  JButton btnNewButton = new JButton("D:\u5220\u9664");
	public  int uid;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TEST2_UI();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void updateTable(){
          try {
			obj=new BBiz().getBjsnameList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          table.setModel(new DefaultTableModel(obj,columnNames));
          table.repaint();
		  table.updateUI();
	}
	public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
 }
	public TEST2_UI() {
		setTitle("\u3010\u57FA\u672C\u4FE1\u606F\uFF1A\u7F16\u8F91\u5BA4\u540D\u79F0\u3011");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//getContentPane().setBackground(Color.WHITE);
		contentPane.setLayout(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bjsname=textField.getText().toString();
				int selection = JOptionPane.showConfirmDialog(null,"确认删除该编辑室？","注意",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if( selection == JOptionPane.OK_OPTION ){
					try {
						new BBiz().deleteBjsName(bjsname);
						updateTable();
						label_1.setText("共"+new BBiz().getBjsnameListNumber()+"项");
						btnNewButton.setEnabled(false);
						label.setText("添加");;
						textField.setText(null);
						textField_1.setText(null);
						textField_1.requestFocus();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else{
					
				}
			}
		});
		
		
		btnNewButton.setEnabled(false);
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 14));
		btnNewButton.setBounds(287, 327, 92, 27);
		contentPane.add(btnNewButton);
		
		JButton btnR = new JButton("R:\u590D\u4F4D");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				//table.setRowSelectionInterval(-1,-1);
				table.setRowSelectionAllowed(false);
				label.setText("添加");;
				textField.setText(null);
				textField_1.setText(null);
				textField_1.requestFocus();
			}
		});
		btnR.setFont(new Font("宋体", Font.BOLD, 14));
		btnR.setBounds(409, 284, 92, 27);
		contentPane.add(btnR);
		
		JButton button_1 = new JButton("S:\u4FDD\u5B58");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(label.getText().toString().equals("添加")){
					String bjsName=textField.getText().toString();
					int bjsNum=Integer.valueOf(textField_1.getText().toString());//排序号
					int flag=0;
					if(bjsName.equals("")){
						JOptionPane.showMessageDialog(null,"输入编辑室名称为空!","提示",JOptionPane.INFORMATION_MESSAGE);
						textField.setText(null);
						textField_1.setText(null);
						textField_1.requestFocus();
						return;
					}
					if(textField_1.getText().toString().equals("")){
						JOptionPane.showMessageDialog(null,"输入编辑室排序号为空!","提示",JOptionPane.INFORMATION_MESSAGE);
						textField.setText(null);
						textField_1.setText(null);
						textField_1.requestFocus();
						return;
					}
					if(!isNumeric(textField_1.getText().toString())){
						JOptionPane.showMessageDialog(null,"输入编辑室排序号不为数字!","提示",JOptionPane.INFORMATION_MESSAGE);
						textField.setText(null);
						textField_1.setText(null);
						textField_1.requestFocus();
						return;
					}
					if(Integer.valueOf(textField_1.getText().toString())<0){
						JOptionPane.showMessageDialog(null,"输入编辑室排序号为负数!","提示",JOptionPane.INFORMATION_MESSAGE);
						textField.setText(null);
						textField_1.setText(null);
						textField_1.requestFocus();
						return;
					}
					try {
						flag = new BBiz().AddBjsInformation(bjsName, bjsNum);
						if(flag==0){//存在同名称编辑室
							JOptionPane.showMessageDialog(null,"存在同名编辑室!","提示",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						else if(flag==-1){//存在同排序号编辑室
							JOptionPane.showMessageDialog(null,"存在同排序号编辑室!","提示",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						else if(flag==1){//添加成功
							updateTable();
							try {
								int index=0;
								for(int i=0;i<obj.length;i++)
							    for(int j=0;j<2;j++){
									if(obj[i][j].equals(bjsName))
										index=i;
									}
								table.setRowSelectionInterval(index, index);
								textField.setText(null);
								textField_1.setText(null);
								textField_1.requestFocus();
								label_1.setText("共"+new BBiz().getBjsnameListNumber()+"项");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(label.getText().toString().equals("修改")){
					try {
						String bjsName=textField.getText().toString();
						int    bjsNum=Integer.valueOf(textField_1.getText().toString());
					    if(new BBiz().getBjsName(uid).equals(textField.getText().toString())==false){
					    	int flag=new BBiz().updateBjsName(uid, bjsName);
							if(flag==0){
								JOptionPane.showMessageDialog(null,"已有同名编辑室，该编辑室名称修改失败!","错误",JOptionPane.ERROR_MESSAGE);
								textField.requestFocus();
							}
					    }
					    if(new BBiz().getBjsNum(uid)!=bjsNum){
					    	int flag=new BBiz().updateBjsNum(uid, bjsNum);
							if(flag==0){
								JOptionPane.showMessageDialog(null,"已有同排序号编辑室，该编辑室名称修改失败!","错误",JOptionPane.ERROR_MESSAGE);
								textField.requestFocus();
							}
					    }
						updateTable();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				
			}
		});
		button_1.setFont(new Font("宋体", Font.BOLD, 14));
		button_1.setBounds(287, 284, 92, 27);
		contentPane.add(button_1);
		
		JButton btnE = new JButton("E:\u5173\u95ED");
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnE.setFont(new Font("宋体", Font.BOLD, 14));
		btnE.setBounds(409, 327, 92, 27);
		contentPane.add(btnE);
		
		
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		label.setForeground(new Color(178, 34, 34));
		label.setBounds(267, 0, 42, 18);
		contentPane.add(label);
		
		try {
			label_1.setFont(new Font("宋体", Font.PLAIN, 17));
			label_1.setText("共"+new ABiz().getBjsnameListNumber()+"项");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		label_1.setBounds(14, 0, 72, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u540D  \u79F0");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		label_2.setBounds(267, 117, 62, 22);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(343, 115, 125, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 31, 218, 336);
		contentPane.add(scrollPane);
		
		table = new JTable(){
			 public boolean isCellEditable(int row, int column)
	            {
	                return false;
	            }
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table.setRowSelectionAllowed(true);
				label.setText("修改");
				int index=table.getSelectedRow();
				String name=table.getValueAt(index, 1).toString();
				String num =table.getValueAt(index, 0).toString();
				try {
					uid=new BBiz().getBjsID(name.toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textField.setText(name);
				textField_1.setText(num);
				btnNewButton.setEnabled(true);
				
			}
		});
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		table.setDefaultRenderer(Object.class, tcr);
		table.setFont(new Font("宋体", Font.PLAIN, 15));
		try {
			obj=new BBiz().getBjsnameList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setModel(new DefaultTableModel(obj, columnNames));
		scrollPane.setViewportView(table);
		
		JLabel label_3 = new JLabel("\u6392\u5E8F\u53F7");
		label_3.setFont(new Font("宋体", Font.PLAIN, 17));
		label_3.setBounds(267, 68, 51, 27);
		contentPane.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(343, 70, 125, 24);
		contentPane.add(textField_1);
		this.setLocationRelativeTo(null);
		setVisible(true);
		textField_1.requestFocus();
	}
}
